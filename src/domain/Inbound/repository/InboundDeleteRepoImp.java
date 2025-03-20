package domain.Inbound.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InboundDto;
import exception.InboundException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InboundDeleteRepoImp implements InboundDeleteRepo{

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<InboundDto> deleteSearch(String a) {
        List<InboundDto> list = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call db_inbound_deletesearch(?)}");
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

    @Override
    public void Delete(String  inboundDto) throws InboundException {

        try{
            connection.setAutoCommit(false);
            String sql = "{ CALL DB_INBOUND_DELETE(?) } ";
            cs = connection.prepareCall(sql);
            cs.setString(1,inboundDto);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }finally {
            try{
                if (cs != null) cs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


    }
}
