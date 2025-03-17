package domain.Inventory.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InventoryDto;
import exception.InventoryException;

import java.sql.*;
import java.util.Optional;

public class InventoryDeleteRepoImp implements InventoryDeleteRepo {
    Connection connection  = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;


    @Override
    public Optional<InventoryDto> deleteInventory(String prodID, String clientID, String wareID) throws InventoryException {
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call inventory_delete(?,?,?)}");
            cs.setString(1,prodID);
            cs.setString(2,clientID);
            cs.setString(3,wareID);
            int rowsAffected = cs.executeUpdate();

            connection.commit();
            cs.close();

            if(rowsAffected>0){
                return Optional.of(new InventoryDto());
            }else return Optional.empty();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }


    }
}
