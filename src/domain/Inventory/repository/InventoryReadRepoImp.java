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
    public Optional<List<WarehouseDto>> ReadAll() {
        try {
            List<WarehouseDto> List = new ArrayList<>();

            String sql = new StringBuilder()
                    .append("SELECT * FROM WAREHOUSE").toString();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                WarehouseDto dto = WarehouseDto.builder()
                        .ware_id(rs.getString("ware_id"))
                        .client_id(rs.getString("client_id"))
                        .prod_id(rs.getString("prod_id"))
                        .quantity(rs.getInt("quantity"))
                        .last_inbound_day(rs.getDate("last_inbound_day"))
                        .last_outbount_day(rs.getDate("last_outbount_day"))
                        .build();
                List.add(dto);
            }
            pstmt.close();

            return Optional.of(List);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InventoryException(ErrorCode.DB_INVENTORY_READ_ALL_ERROR);


        }


    }

}

