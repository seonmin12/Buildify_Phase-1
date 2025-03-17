package domain.Inventory.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InventoryDto;
import exception.InventoryException;

import java.sql.*;
import java.util.Optional;

public class InventoryUpdateRepoImp implements InventoryUpdateRepo {
    Connection connection  = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;


    @Override
    public Optional<InventoryDto> updateQuantity(String prodID, String clientID, String wareID, int newQuantity) throws InventoryException {
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call inventory_update_quantity(?,?,?,?)}");
            cs.setString(1,prodID);
            cs.setString(2,clientID);
            cs.setString(3,wareID);
            cs.setInt(4,newQuantity);
            rs = cs.executeQuery();

            if(rs.next()){
                InventoryDto inventoryDto = InventoryDto.builder()
                        .prod_id(rs.getString("prod_id"))
                        .prod_name(rs.getString("prod_name"))
                        .client_id(rs.getString("client_id"))
                        .ware_id(rs.getString("ware_id"))
                        .quantity(rs.getInt("quantity"))
                        .last_inbound_day(rs.getDate("last_inbound_date"))
                        .last_outbount_day(rs.getDate("last_outbound_date"))

                        .build();

                        connection.commit();

                        cs.close();

                        return Optional.of(inventoryDto);

            }else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }



    }
}
