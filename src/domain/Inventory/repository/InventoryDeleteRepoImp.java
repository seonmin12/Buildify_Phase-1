package domain.Inventory.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InventoryDto;
import exception.InventoryException;

import java.sql.*;
import java.util.Optional;



/**
 * {@link InventoryDeleteRepo} 인터페이스의 구현 클래스.
 * 데이터베이스에서 지정된 재고 데이터를 삭제하는 기능을 수행한다.
 * 저장 프로시저를 호출하여 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고를 삭제한다.
 */
public class InventoryDeleteRepoImp implements InventoryDeleteRepo {
    Connection connection  = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고 데이터를 삭제한다.
     * 저장 프로시저를 호출하여 삭제하며, 삭제 성공 시 Optional 객체를 반환한다.
     *
     * @param prodID   삭제할 상품의 ID
     * @param clientID 해당 상품의 클라이언트 ID
     * @param wareID   해당 상품의 창고 ID
     * @return 삭제 성공 시 빈 {@link InventoryDto}를 담은 Optional 객체 반환,
     *         삭제 실패 시 Optional.empty() 반환
     * @throws InventoryException 데이터베이스 오류 발생 시 예외 처리
     */
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
