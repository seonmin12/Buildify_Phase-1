package domain.Inbound.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.ClientUpdateDto;
import dto.InboundDto;
import dto.UserDto;
import exception.InboundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InboundSearchRepoImp implements InboundSearchRepo {
    //다시올리기
    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;


    /**
     * 입고요청진행 업체리스트 조회
     * @return
     */
    @Override
    public List<ClientUpdateDto> clientsearch() {

        List<ClientUpdateDto> list = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call db_inbound_search_clientup()}");
            rs = cs.executeQuery();

            while (rs.next()) {
                ClientUpdateDto dto = ClientUpdateDto.builder()
                        .client_id(rs.getString("client_id"))
                        .user_id(rs.getString("user_name"))
                        .build();
                list.add(dto);
            }
            rs.close();
            cs.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }

    }

    /**
     * 회원 입고요청 리스트 조회
     * @param a
     * @return
     */
    @Override
    public List<InboundDto> userSearch(String a) {
        List<InboundDto> list = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call DB_inbound_usersearch(?)}");
            cs.setString(1, a);
            rs = cs.executeQuery();

            while (rs.next()) {
                InboundDto dto = InboundDto.builder()
                        .inbound_number(rs.getString("inbound_id"))
                        .prod_id(rs.getString("prod_id"))
                        .client_id(rs.getString("client_id"))
                        .quantity(rs.getInt("quantity"))
                        .inbound_status(rs.getInt("inbound_status"))
                        .req_inbound_day(rs.getDate("req_inbound_day"))
                        .ware_id(rs.getString("ware_id"))
                        .build();
                list.add(dto);
            }
            rs.close();
            cs.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }
    }

    /**
     * 관리자 입고요청 리스트 업체별 조회
     * @param inbound_number
     * @return
     */
    @Override
    public List<InboundDto> SearchOne(String inbound_number) {
        List<InboundDto> list = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call DB_inbound_searchone(?)}");
            cs.setString(1, inbound_number);
            rs = cs.executeQuery();

            while (rs.next()) {
                InboundDto dto = InboundDto.builder()
                        .inbound_number(rs.getString("inbound_id"))
                        .prod_id(rs.getString("prod_id"))
                        .client_id(rs.getString("client_id"))
                        .quantity(rs.getInt("quantity"))
                        .inbound_status(rs.getInt("inbound_status"))
                        .req_inbound_day(rs.getDate("req_inbound_day"))
                        .ware_id(rs.getString("ware_id"))
                        .build();
                list.add(dto);
            }
                rs.close();
                cs.close();
                return list;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }
    }

    /**
     * 관리자 입고요청 리스트 전체조회
     * @return
     */
    @Override
    public Optional<List<InboundDto>> SearchAll() {
        List<InboundDto> list = new ArrayList<>();
        connection = DBConnection.getConnection();

        try {
            String sql = new StringBuilder()
                    .append(" select * from inbound ").toString();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                InboundDto dto = InboundDto.builder()
                        .inbound_number(rs.getString("inbound_id"))
                        .prod_id(rs.getString("prod_id"))
                        .client_id(rs.getString("client_id"))
                        .quantity(rs.getInt("quantity"))
                        .inbound_status(rs.getInt("inbound_status"))
                        .req_inbound_day(rs.getDate("req_inbound_day"))
                        .ware_id(rs.getString("ware_id"))
                        .build();
                list.add(dto);
            }
            pstmt.close();
            return Optional.of(list);
        } catch (SQLException e) {
//           throw new RuntimeException(e);
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);

        }

    }
}