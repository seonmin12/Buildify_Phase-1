package domain.Inbound.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InboundDto;
import exception.InboundException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InboundDeleteRepoImp implements InboundDeleteRepo{

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;

    @Override
    public void Delete(InboundDto inboundDto) throws InboundException {

        String sql = "{ CALL DB_INBOUND_DELETE(?) } ";

        try{
            cs = connection.prepareCall(sql);
            cs.setString(1,inboundDto.getInbound_number());

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
