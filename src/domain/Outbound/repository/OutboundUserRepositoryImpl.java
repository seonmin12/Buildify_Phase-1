package domain.Outbound.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.OutboundDto;
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
                        .outbound_number(rs.getString("key"))
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
}
