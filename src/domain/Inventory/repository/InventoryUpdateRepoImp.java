package domain.Inventory.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InventoryDto;
import exception.InventoryException;

import java.sql.*;
import java.util.Optional;


/**
 * {@link InventoryUpdateRepo} 인터페이스의 구현 클래스.
 * 데이터베이스의 재고 수량을 업데이트하는 기능을 수행한다.
 * 저장 프로시저를 호출하여 해당 재고의 수량을 변경하고, 변경된 정보를 반환한다.
 */
public class InventoryUpdateRepoImp implements InventoryUpdateRepo {
    Connection connection  = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    /**
     * 지정된 제품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고의 수량을 업데이트한다.
     * 저장 프로시저를 통해 수량을 변경하고, 변경된 재고 정보를 반환한다.
     *
     * @param prodID      수량을 변경할 상품 ID
     * @param clientID    해당 제품의 클라이언트 ID
     * @param wareID      해당 제품의 창고 ID
     * @param newQuantity 변경할 수량 값
     * @return 업데이트된 재고 정보를 담은 Optional 객체.
     *         업데이트 실패 또는 결과 없음 시 Optional.empty() 반환
     * @throws InventoryException SQL 예외 발생 시 커스텀 예외 발생
     */
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
