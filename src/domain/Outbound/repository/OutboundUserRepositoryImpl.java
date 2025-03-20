package domain.Outbound.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.OutboundDto;
import dto.ReqOutboundDto;
import exception.OutboundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (고객용) 출고관리 Repository 구현체입니다.
 */
public class OutboundUserRepositoryImpl implements OutboundUserRepository {
    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    @Override
    public List<OutboundDto> outboundUserRead(String clientID) throws OutboundException {
        try {
            Connection connection = DBConnection.getConnection();
            List<OutboundDto> outboundDtos = new ArrayList<>();

            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call outbound_user_read(?)}");
            cs.setString(1,clientID);
            rs = cs.executeQuery();

            while(rs.next()){
                OutboundDto outboundDto = OutboundDto.builder()
                        .outbound_id(rs.getString("outbound_id"))
                        .prod_id(rs.getString("prod_id"))
                        .client_id(rs.getString("client_id"))
                        .quantity(rs.getInt("quantity"))
                        .outbound_status(rs.getInt("status"))
                        .req_outbound_day(rs.getDate("req_outbound_day"))
                        .ware_id(rs.getString("ware_id"))

                        .build();

                outboundDtos.add(outboundDto);

            }
            cs.close();
            return outboundDtos;

        } catch (SQLException e) {
           e.printStackTrace();
           throw new OutboundException(ErrorCode.ERROR_INPUT);
        }


    }

    @Override
    public int deleteOutboundUser(String outboundNumber, String clientID) throws OutboundException {
        int result = 0;

        try {
            Connection connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            cs = connection.prepareCall("{call delete_outbound_request(?,?,?)}");
            cs.setString(1,outboundNumber);
            cs.setString(2,clientID);
            cs.registerOutParameter(3,Types.INTEGER);

            cs.execute();
            result = cs.getInt(3);

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OutboundException(ErrorCode.ERROR_INPUT);
        }

        return result;

    }

    @Override
    public List<OutboundDto> getOutboundUserRequest(String clientID) throws OutboundException {
        List<OutboundDto> outboundDtos = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            cs = connection.prepareCall("{call outbound_user_pending_read(?)}");
            cs.setString(1,clientID);
            rs = cs.executeQuery();

            while(rs.next()){
                OutboundDto dto = OutboundDto.builder()
                        .outbound_id(rs.getString("outbound_id"))
                        .prod_id(rs.getString("prod_id"))
                        .client_id(rs.getString("client_id"))
                        .quantity(rs.getInt("quantity"))
                        .outbound_status(rs.getInt("status"))
                        .req_outbound_day(rs.getDate("req_outbound_day"))
                        .ware_id(rs.getString("ware_id"))

                        .build();

                outboundDtos.add(dto);

            }

            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OutboundException(ErrorCode.ERROR_INPUT);
        }
        return outboundDtos;
    }

    @Override
    public List<ReqOutboundDto> requestOutbound(String clientID) throws OutboundException {
       List<ReqOutboundDto> outboundList = new ArrayList<>();

        String sql = "SELECT " +
                "p.prod_id, p.brand, p.prod_name, p.prod_price, p.prod_code, " +
                "p.prod_category, p.prod_size, s.quantity, s.client_id, s.ware_id " +
                "FROM inventory s " +
                "JOIN product p ON s.prod_id = p.prod_id " +
                "WHERE s.client_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, clientID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ReqOutboundDto dto = new ReqOutboundDto(
                        rs.getString("prod_id"),
                        rs.getString("brand"),
                        rs.getString("prod_name"),
                        rs.getInt("prod_price"),
                        rs.getInt("prod_code"),
                        rs.getString("prod_category"),
                        rs.getBigDecimal("prod_size"),
                        rs.getInt("quantity"),
                        rs.getString("client_id"),
                        rs.getString("ware_id")
                );
                outboundList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  outboundList;
    }

    @Override
    public boolean insertOutbound(OutboundDto outboundDto) {
        boolean isSuccess = false;
        String sql = "INSERT INTO outbound VALUES (?, ?, ?, ?, ?, NOW(), ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, outboundDto.getOutbound_id());
            pstmt.setString(2, outboundDto.getProd_id());
            pstmt.setString(3, outboundDto.getClient_id());
            pstmt.setInt(4, outboundDto.getQuantity());
            pstmt.setInt(5, outboundDto.getOutbound_status());
            pstmt.setString(6, outboundDto.getWare_id());

            int rowsAffected = pstmt.executeUpdate(); // ✅ execute() 대신 executeUpdate() 사용 (명확한 반환값 처리)
            if (rowsAffected > 0) {
                isSuccess = true;
                System.out.println("출고 요청이 성공적으로 등록되었습니다.");
            } else {
                System.out.println("출고 요청 등록에 실패했습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // ✅ 예외 출력 (혹은 로깅)
            throw new RuntimeException("출고 데이터 삽입 중 오류 발생", e);
        }

        return isSuccess;
    }

}
