package domain.Inbound.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InboundDto;
import exception.InboundException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class InboundInsertRepoImp implements InboundInsertRepo{

    Connection conn = DBConnection.getConnection();
    CallableStatement cs = null;
    @Override
    public void insert(InboundDto inboundDto) throws InboundException, SQLException {
        try{
            conn.setAutoCommit(false);
            cs = conn.prepareCall("{call DB_INBOUND_INSERT(?,?,?,?,?,?,?)}");
            cs.setString(1,inboundDto.getInbound_number());
            cs.setString(2,inboundDto.getProd_id());
            cs.setString(3,inboundDto.getClient_id());
            cs.setInt(4,inboundDto.getQuantity());
            cs.setInt(5,inboundDto.getInbound_status());
            cs.setDate(6, new java.sql.Date(inboundDto.getReq_inbound_day().getTime()));
            cs.setString(7,inboundDto.getWare_id());

            cs.execute();
            conn.commit();


        }catch (SQLException e){
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }

    }
}
