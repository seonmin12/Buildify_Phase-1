package domain.Inbound.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.InboundDto;
import dto.InventoryDto;
import dto.ProductDto;
import exception.InboundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InboundInsertRepoImp implements InboundInsertRepo{

    Connection conn = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<ProductDto> inboundinsertlist() {
       List<ProductDto> list = new ArrayList<>();
       try {
           conn.setAutoCommit(false);
           cs = conn.prepareCall("{ CALL db_inbound_insertlist() }");
           rs = cs.executeQuery();

           while (rs.next()){
               ProductDto productDto = ProductDto.builder()
                       .prod_id(rs.getString("prod_id"))
                       .brand(rs.getString("brand"))
                       .prod_name(rs.getString("prod_name"))
                       .prod_price(rs.getInt("prod_price"))
                       .prod_code(rs.getInt("prod_code"))
                       .prod_category(rs.getString("prod_category"))
                       .prod_size(rs.getBigDecimal("prod_size"))
                       .build();
               list.add(productDto);
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
    public void insert(InboundDto inboundDto) throws InboundException {
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
