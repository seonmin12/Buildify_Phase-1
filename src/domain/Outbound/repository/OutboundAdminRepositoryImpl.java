package domain.Outbound.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.OutboundDto;
import dto.UserDto;
import exception.BuildifyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.Text.*;

/**
 * (관리자용) 출고관리 Repository 구현체입니다.
 */
public class OutboundAdminRepositoryImpl implements OutboundAdminRepository {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;

    PreparedStatement pstmt = null;

    /**
     * (관리자메뉴) 출고 리스트 전체 조회
     * @return List<OutboundDto> 출고 리스트 전체 조회
     */
    @Override
    public List<OutboundDto> searchOutboundList() {
        List<OutboundDto> list = new ArrayList<>();
        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM outbound").toString();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                OutboundDto dto = OutboundDto.builder()
                        .outbound_number(rs.getString("outbound_number"))
                        .prod_id(rs.getString("prod_id"))
                        .client_id(rs.getString("client_id"))
                        .quantity(rs.getInt("quantity"))
                        .outbound_status(rs.getInt("status"))
                        .req_outbound_day(rs.getDate("req_outbound_day"))
                        .ware_id(rs.getString("ware_id"))
                        .build();
                list.add(dto);
            }
            pstmt.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    @Override
    public List<OutboundDto> searchOutboundByUser(String Client_id) {
        List<OutboundDto> list = new ArrayList<>();
        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM outbound WHERE client_id = ?").toString();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,Client_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                OutboundDto dto = OutboundDto.builder()
                        .outbound_number(rs.getString("outbound_number"))
                        .prod_id(rs.getString("prod_id"))
                        .client_id(rs.getString("client_id"))
                        .quantity(rs.getInt("quantity"))
                        .outbound_status(rs.getInt("status"))
                        .req_outbound_day(rs.getDate("req_outbound_day"))
                        .ware_id(rs.getString("ware_id"))
                        .build();
                list.add(dto);
            }
            pstmt.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    /**
     * (관리자메뉴) 출고 전체 승인
     */
    @Override
    public void approveAllList() {
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call OUTBOUND_ALL_APPROVE()}");
            rs = cs.executeQuery();
            connection.commit();

            rs.close();
            cs.close();
            System.out.println(OUTBOUND_APPROVE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    /**
     * (관리자메뉴) 클라이언트 ID 기준 해당 고객 출고 전체 승인
     */
    @Override
    public void approveOneId(String Client_id) {
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call OUTBOUND_ONE_ID_APPROVE(?)}");
            cs.setString(1, Client_id);
            rs = cs.executeQuery();
            connection.commit();

            rs.close();
            cs.close();
            System.out.println(Client_id+ USER_OUTBOUND_APPROVE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     * (관리자메뉴) 출고Number 기준 해당 출고건 승인
     */
    @Override
    public void approveOneNumber(String outbound_number) {
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call OUTBOUND_ONE_APPROVE(?)}");
            cs.setString(1, outbound_number);
            rs = cs.executeQuery();
            connection.commit();

            rs.close();
            cs.close();
            System.out.println(outbound_number+ OUTBOUND_APPROVE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     * (관리자메뉴) 출고 Number 기준 해당 출고 반려
     */
    @Override
    public void returnOneNumber(String outbound_number) {
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call OUTBOUND_ONE_RETURN(?)}");
            cs.setString(1, outbound_number);
            rs = cs.executeQuery();
            connection.commit();

            rs.close();
            cs.close();
            System.out.println(outbound_number+ OUTBOUND_APPROVE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     * Client id가 DB에 있는지 검증하는 메소드
     * @param Client_id
     * @return "TRUE" or "FALSE"
     */
    @Override
    public String clientIdValidCheck(String Client_id) {
        String result = null;
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call CLIENT_ID_VALIDATION(?)}");
            cs.setString(1, Client_id);
            rs = cs.executeQuery();
            connection.commit();
            if (rs.next()){
                result = rs.getString(1);
            }
            rs.close();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }

    /**
     * 출고번호가 DB에 있는지 검증하는 메소드
     * @param number
     * @return "TRUE" or "FALSE"
     */
    @Override
    public String outboundNumberValidCheck(String number) {
        String result = null;
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call OUTBOUND_NUMBER_VALIDATION(?)}");
            cs.setString(1, number);
            rs = cs.executeQuery();
            connection.commit();
            if (rs.next()){
                result = rs.getString(1);
            }
            rs.close();
            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }
}
