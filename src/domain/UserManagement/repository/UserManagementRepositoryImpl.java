package domain.UserManagement.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.AdminDto;
import dto.UserDto;
import exception.BuildifyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagementRepositoryImpl implements UserManagementRepository{

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;

    PreparedStatement pstmt = null;

    @Override
    public List<UserDto> listAllUsers() throws BuildifyException {
        List<UserDto> list = new ArrayList<>();

        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM user").toString();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserDto dto = UserDto.builder()
                        .client_id(rs.getString("client_id"))
                        .user_name(rs.getString("user_name"))
                        .user_phone(rs.getString("user_phone"))
                        .user_email(rs.getString("user_email"))
                        .user_adress(rs.getString("user_adress"))
                        .user_enterday(rs.getDate("user_enterday"))
                        .user_status(rs.getInt("user_status"))
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
    public List<AdminDto> listAllLocalAdmin() {
        List<AdminDto> list = new ArrayList<>();

        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM admin WHERE admin_role = '창고관리자' ").toString();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AdminDto dto = AdminDto.builder()
                        .adminNumber(rs.getString("admin_number"))
                        .adminRole(rs.getString("admin_role"))
                        .adminName(rs.getString("admin_name"))
                        .adminEmail(rs.getString("admin_email"))
                        .adminEnterday(rs.getDate("admin_enterday"))
                        .adminAddress(rs.getString("admin_adress"))
                        .adminPhone(rs.getString("admin_phone"))
                        .adminId(rs.getString("admin_id"))
                        .adminPw(rs.getString("admin_pw"))
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
    public List<UserDto> pendingApprovalUsers() throws BuildifyException{
        List<UserDto> pendinglist = new ArrayList<>();

        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM user WHERE user_status = 0 ").toString();

            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserDto dto = UserDto.builder()
                        .client_id(rs.getString("client_id"))
                        .user_name(rs.getString("user_name"))
                        .user_phone(rs.getString("user_phone"))
                        .user_email(rs.getString("user_email"))
                        .user_adress(rs.getString("user_adress"))
                        .user_enterday(rs.getDate("user_enterday"))
                        .user_status(rs.getInt("user_status"))
                        .build();
                pendinglist.add(dto);
            }
            pstmt.close();

            return pendinglist;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    @Override
    public void approveUser(String Client_id) throws BuildifyException{
        String sql = new StringBuilder()
                .append("UPDATE user SET user_status = 1 WHERE client_id = ? ").toString();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Client_id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Client_id + " 회원이 승인되었습니다.");
            } else {
                System.out.println("회원 승인 실패: 존재하지 않는 회원 ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    @Override
    public UserDto searchUser(String Client_id) {
        UserDto dto = null;

        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call DB_USER_READONE(?)}");
            cs.setString(1,Client_id);
            rs = cs.executeQuery();

            if (rs.next()){
                dto = UserDto.builder()
                        .client_id(rs.getString("client_id"))
                        .user_name(rs.getString("user_name"))
                        .user_phone(rs.getString("user_phone"))
                        .user_email(rs.getString("user_email"))
                        .user_adress(rs.getString("user_adress"))
                        .user_enterday(rs.getDate("user_enterday"))
                        .user_status(rs.getInt("user_status"))
                        .build();
            }
            cs.close();

            return dto;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    @Override
    public void updateUser(String Client_id,Integer choice,String newValue) {
        String sql = "";

        switch (choice){
            case 1:
                sql =  new StringBuilder()
                        .append("UPDATE user SET user_phone = ? WHERE client_id = ? ").toString();
                break;
            case 2:
                sql =  new StringBuilder()
                        .append("UPDATE user SET user_email = ? WHERE client_id = ? ").toString();
                break;
            case 3:
                sql =  new StringBuilder()
                        .append("UPDATE user SET user_adress = ? WHERE client_id = ? ").toString();
                break;
        }


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newValue);
            pstmt.setString(2, Client_id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Client_id + " 회원님의 정보가 변경되었습니다.");
            } else {
                System.out.println("회원 정보 변경 실패: 존재하지 않는 회원 ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    @Override
    public void updateSelfAdmin(String Admin_id, Integer choice, String newValue) {
        String sql = "";

        switch (choice){
            case 1:
                sql =  new StringBuilder()
                        .append("UPDATE admin SET admin_phone = ? WHERE admin_number = ? ").toString();
                break;
            case 2:
                sql =  new StringBuilder()
                        .append("UPDATE admin SET admin_email = ? WHERE admin_number = ? ").toString();
                break;
            case 3:
                sql =  new StringBuilder()
                        .append("UPDATE admin SET admin_adress = ? WHERE admin_number = ? ").toString();
                break;
        }


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newValue);
            pstmt.setString(2, Admin_id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Admin_id + " 관리자님의 정보가 변경되었습니다.");
            } else {
                System.out.println("관리자 정보 변경 실패: 존재하지 않는 관리자 ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }
}