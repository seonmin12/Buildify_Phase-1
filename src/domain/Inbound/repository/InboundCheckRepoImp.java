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
import java.util.Date;
import java.util.List;

public class InboundCheckRepoImp implements InboundCheckRepo {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;

    @Override
    public List<InboundDto> allCheckRead() {
        List<InboundDto> list = new ArrayList<>();
        connection = DBConnection.getConnection();

        try {
            cs = connection.prepareCall("{ CALl DB_inbound_allcheck_read() }");
            ResultSet rs = cs.executeQuery();

            while (rs.next()){
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
    public void allCheckUpdate() {
        try {
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALL DB_inbound_allcheck_update() }");
            cs.executeUpdate();

            System.out.println("인바운드 올체크 업데이트");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }
    }

    @Override
    public void allCheckReturn() {
        try{
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALL DB_inbound_allcheck_return() }");
            cs.executeQuery();
            System.out.println("인바운드 올체크 리");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }

    }

    @Override
    public List<InboundDto> prodCheckRead() {
        List<InboundDto> list = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALl DB_inbound_check_prod_read() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                InboundDto dto = InboundDto.builder()
                        .inbound_number(rs.getString("inbound_number"))
                        .prod_id(rs.getString("prod_id"))
                        .client_id(rs.getString("client_id"))
                        .quantity(rs.getInt("quantity"))
                        .inbound_status(rs.getInt("inbound_status"))
                        .req_inbound_day(rs.getDate("req_inbound_day"))
                        .ware_id(rs.getString("wqre_id"))
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
    public void prodCheckUpdate() {
        try{
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALL DB_inbound_check_prod_update() }");
            cs.executeQuery();
            System.out.println("인바운드 프로드체크 업데이트");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }

    }

    @Override
    public void prodCheckReturn() {
        try{
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALL DB_inbound_check_prod_return() }");
            cs.executeQuery();
            System.out.println("임바운드 프로드체크 리");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }

    }

    @Override
    public List<InboundDto> clientCheckRead(String a) {
        List<InboundDto> list = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALl DB_inbound_check_client_read(?) }");
            cs.setString(1,a);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                InboundDto dto = InboundDto.builder()
                        .inbound_number(rs.getString("inbound_number"))
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
    public void clientCheckUpdate(String a) {
        try {
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALL DB_inbound_check_client_update(?) }");
            cs.setString(1,a);
            cs.executeQuery();
            System.out.println("해당업체 승인완료");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }

    }

    @Override
    public void clientCheckReturn(String a) {
        try {
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALL DB_inbound_check_client_return(?) }");
            cs.setString(1,a);
            cs.executeQuery();
            System.out.println("해당업체 반려완료");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }

    }

    @Override
    public void inbound_number_check_update(String ci) {
        try {
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALL DB_inbound_check_inbound_number_update(?) }");
            cs.setString(1,ci);
            cs.executeQuery();
            System.out.println("인바운드 입고번호 업데이트");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }
    }

    @Override
    public void inbound_number_check_return(String ci) {
        try {
            connection = DBConnection.getConnection();
            cs = connection.prepareCall("{ CALL DB_inbound_check_inbound_number_return(?) }");
            cs.setString(1, ci);
            cs.executeQuery();
            System.out.println("인바운드 입고번호 리턴");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }
    }
}
