package domain.Inventory.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InventoryDto;
import dto.WarehouseDto;
import exception.InventoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



/**
 * {@link InventoryReadRepo} 인터페이스의 구현 클래스.
 * 데이터베이스와 연결하여 재고 데이터를 조회하는 기능을 수행한다.
 * 프로시저 호출을 통해 제품명, 클라이언트 ID, 카테고리 등을 기준으로 재고 정보를 가져온다.
 * 전체 재고 정보도 가져올 수 있다.
 */
public class InventoryReadRepoImp implements InventoryReadRepo {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    /**
     * 데이터베이스에서 모든 재고 정보를 조회하는 메서드.
     * <p>
     * 저장 프로시저 {@code inventory_readAll()}을 호출하여 조회된 결과를
     * {@link InventoryDto} 리스트로 반환한다.
     * </p>
     *
     * @return 재고 정보 리스트. 예외 발생 시 빈 리스트 반환.
     */
    @Override
    public List<InventoryDto> ReadAll()  {
        List<InventoryDto> inventoryDtoList = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            cs = connection.prepareCall("{call inventory_readAll()}");
            rs = cs.executeQuery();

            while(rs.next()){

                InventoryDto inventoryDto = InventoryDto.builder()
                        .prod_id(rs.getString("prod_id"))
                        .prod_name(rs.getString("prod_name"))
                        .client_id(rs.getString("client_id"))
                        .ware_id(rs.getString("ware_id"))
                        .quantity(rs.getInt("quantity"))
                        .last_inbound_day(rs.getDate("last_inbound_day"))
                        .last_outbount_day(rs.getDate("last_outbound_day"))
                        .build();



                inventoryDtoList.add(inventoryDto);


            }


        } catch (SQLException e) {
            e.printStackTrace();

        }


        return inventoryDtoList;


    }


    /**
     * 상품명을 기준으로 재고 정보를 조회하는 메서드.
     *
     * @param productName 조회할 상품명
     * @return 해당 상품명과 일치하는 재고 정보 리스트
     */
    @Override
    public List<InventoryDto> ReadByProductName(String productName)  {
        List<InventoryDto> inventoryDtoList = new ArrayList<>();

        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call inventory_readOne_productName(?)}");
            cs.setString(1, productName);
            rs = cs.executeQuery();


            while(rs.next()){
                InventoryDto inventoryDto = InventoryDto.builder()
                        .prod_id(rs.getString("prod_id"))
                        .prod_name(rs.getString("prod_name"))
                        .client_id(rs.getString("client_id"))
                        .ware_id(rs.getString("ware_id"))
                        .quantity(rs.getInt("quantity"))
                        .last_inbound_day(rs.getDate("last_inbound_day"))
                        .last_outbount_day(rs.getDate("last_outbound_day"))

                        .build();

                inventoryDtoList.add(inventoryDto);

            }
            cs.close();


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return inventoryDtoList;


    }

    /**
     * 클라이언트 ID를 기준으로 재고 정보를 조회하는 메서드.
     *
     * @param clientID 조회할 클라이언트 ID
     * @return 해당 클라이언트 ID에 해당하는 재고 정보 리스트
     */
    @Override
    public List<InventoryDto> ReadByClientID(String clientID) {
        List<InventoryDto> inventoryDtoList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call inventory_read_by_clientID(?)}");
            cs.setString(1,clientID);
            rs = cs.executeQuery();

            while(rs.next()){
                InventoryDto inventoryDto = InventoryDto.builder()
                        .prod_id(rs.getString("prod_id"))
                        .prod_name(rs.getString("prod_name"))
                        .client_id(rs.getString("client_id"))
                        .ware_id(rs.getString("ware_id"))
                        .quantity(rs.getInt("quantity"))
                        .last_inbound_day(rs.getDate("last_inbound_day"))
                        .last_outbount_day(rs.getDate("last_outbound_day"))

                        .build();

               inventoryDtoList.add(inventoryDto);

            }
            cs.close();
            return inventoryDtoList;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return inventoryDtoList;
    }
    /**
     * 상품 카테고리를 기준으로 재고 정보를 조회하는 메서드.
     *
     * @param category 조회할 카테고리명
     * @return 해당 카테고리에 속하는 재고 정보 리스트
     */
    @Override
    public List<InventoryDto> ReadByCategory(String category) {
        List<InventoryDto> list = new ArrayList<>();

        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call inventory_read_by_category(?)}");
            cs.setString(1,category);
            rs = cs.executeQuery();

            while(rs.next()){
                InventoryDto inventoryDto = InventoryDto.builder()
                        .prod_id(rs.getString("prod_id"))
                        .prod_name(rs.getString("prod_name"))
                        .client_id(rs.getString("client_id"))
                        .ware_id(rs.getString("ware_id"))
                        .quantity(rs.getInt("quantity"))
                        .last_inbound_day(rs.getDate("last_inbound_day"))
                        .last_outbount_day(rs.getDate("last_outbound_day"))

                        .build();
                list.add(inventoryDto);

            }
            cs.close();


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }
}

