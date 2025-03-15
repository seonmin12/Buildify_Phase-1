package domain.Inventory.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.WarehouseDto;
import exception.InventoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryReadRepoImp implements InventoryReadRepo {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    @Override
    public Optional<List<WarehouseDto>> ReadAll() throws InventoryException {
        List<WarehouseDto> warehouseDtoList = new ArrayList<>();

        try {
            connection.setAutoCommit(false);

            cs = connection.prepareCall("{call inventory_readAll()}");
            rs = cs.executeQuery();

            while(rs.next()){

                WarehouseDto warehouseDto = WarehouseDto.builder()
                        .prod_id(rs.getString("prod_id"))
                        .prod_name(rs.getString("prod_name"))
                        .client_id(rs.getString("client_id"))
                        .ware_id(rs.getString("ware_id"))
                        .quantity(rs.getInt("quantity"))
                        .last_inbound_day(rs.getDate("last_inbound_day"))
                        .last_outbount_day(rs.getDate("last_outbount_day"))
                        .build();

                warehouseDtoList.add(warehouseDto);


            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new InventoryException(ErrorCode.DB_INVENTORY_READ_ALL_ERROR);
        }

        return Optional.of(warehouseDtoList);


    }
}

