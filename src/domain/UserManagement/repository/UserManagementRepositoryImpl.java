package domain.UserManagement.repository;

import common.ErrorCode;
import config.DBConnection;
import dto.AdminDto;
import dto.UserDto;
import exception.BuildifyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.Text.*;

public class UserManagementRepositoryImpl implements UserManagementRepository {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;

    PreparedStatement pstmt = null;

    /**
     * DB에서 전체 고객 리스트 반환 하는 메소드입니다.
     * @return List<UserDto>
     */
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

    /**
     * DB에서 전체 창고 관리자 리스트 반환 하는 메소드입니다.
     * @return List<AdminDto>
     */
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

    /**
     * DB에서 승인 대기중인 고객 리스트 반환하는 메소드입니다.
     * @return List<UserDto>
     */
    @Override
    public List<UserDto> pendingApprovalUsers() throws BuildifyException {
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
                        .user_ware_size(rs.getBigDecimal("user_ware_size"))
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

    /**
     * 고객 창고 사용 승인하는 메소드입니다.
     * @param Client_id
     */
    @Override
    public void approveUser(String Client_id) throws BuildifyException {
        String sql = new StringBuilder()
                .append("UPDATE user SET user_status = 1 WHERE client_id = ? ").toString();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Client_id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Client_id + USER_APPROVE_SUCCESS.getText());
            } else {
                System.out.println(USER_CHANGE_FAIL.getText());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    /**
     * 고객 1명 검색하여 객체로 반환하는 메소드입니다.
     * @param Client_id
     * @return UserDto
     */
    @Override
    public UserDto searchUser(String Client_id) {
        UserDto dto = null;

        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call DB_USER_READONE(?)}");
            cs.setString(1, Client_id);
            rs = cs.executeQuery();

            if (rs.next()) {
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

    /**
     * 업데이트할 정보를 선택하여 고객정보를 업데이트하는 메소드입니다.
     * @param Client_id
     * @param choice
     * @param newValue
     */
    @Override
    public void updateUser(String Client_id, Integer choice, String newValue) {
        String sql = "";

        switch (choice) {
            case 1:
                sql = new StringBuilder()
                        .append("UPDATE user SET user_phone = ? WHERE client_id = ? ").toString();
                break;
            case 2:
                sql = new StringBuilder()
                        .append("UPDATE user SET user_email = ? WHERE client_id = ? ").toString();
                break;
            case 3:
                sql = new StringBuilder()
                        .append("UPDATE user SET user_adress = ? WHERE client_id = ? ").toString();
                break;
        }


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newValue);
            pstmt.setString(2, Client_id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Client_id + CHANGE_SUCCESS.getText());
            } else {
                System.out.println(USER_CHANGE_FAIL.getText());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    /**
     * 관리자 본인 정보 업데이트하는 메소드입니다.
     * @param Admin_id
     * @param choice
     * @param newValue
     */
    @Override
    public void updateSelfAdmin(String Admin_id, Integer choice, String newValue) {
        String sql = "";

        switch (choice) {
            case 1:
                sql = new StringBuilder()
                        .append("UPDATE admin SET admin_phone = ? WHERE admin_number = ? ").toString();
                break;
            case 2:
                sql = new StringBuilder()
                        .append("UPDATE admin SET admin_email = ? WHERE admin_number = ? ").toString();
                break;
            case 3:
                sql = new StringBuilder()
                        .append("UPDATE admin SET admin_adress = ? WHERE admin_number = ? ").toString();
                break;
        }


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(true);
            pstmt.setString(1, newValue);
            pstmt.setString(2, Admin_id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Admin_id + CHANGE_SUCCESS.getText());
//                conn.commit();
            } else {
                System.out.println(ADMIN_CHANGE_FAIL.getText());
//                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    /**
     * 현재 임대중인 창고 사용량을 보여주는 메소드입니다.
     * @return int UseWareSize
     */
    @Override
    public int getUseWareSize() {
        int size = 0;
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call USE_AVAILABLE_WARE()}");
            rs = cs.executeQuery();
            if (rs.next()) {
                size = rs.getInt(1);
            }
            cs.close();
            return size;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return size;
    }

    /**
     * 관리자 number로 관리자 1명 검색하는 메소드입니다.
     * @param Admin_id
     * @return AdminDto
     */
    @Override
    public AdminDto searchAdmin(String Admin_id) {
        AdminDto dto = null;

        try {
            connection.setAutoCommit(true);
            cs = connection.prepareCall("{call DB_ADMIN_NUMBER_READONE(?)}");
            cs.setString(1, Admin_id);
            rs = cs.executeQuery();

            while (rs.next()) {
                dto = AdminDto.builder()
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
            }
            cs.close();

            return dto;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

}
