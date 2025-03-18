package domain.AccountManagement.Admin.repository;

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
            rs.close();
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
            rs.close();
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
        try {
            connection.setAutoCommit(true);
            cs = connection.prepareCall("{call DB_USER_APPROVE(?)}");
            cs.setString(1, Client_id);
            rs = cs.executeQuery();

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
            connection.setAutoCommit(true);
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
            rs.close();
            cs.close();

            return dto;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    /**
     * 고객의 연락처를 업데이트하는 메소드 입니다.
     * @param Client_id
     * @param newValue
     */
    @Override
    public void updateUserPhone(String Client_id,String newValue){
        try {
            connection.setAutoCommit(true);
            cs = connection.prepareCall("{call DB_USER_UPDATE_PHONE(?,?)}");
            cs.setInt(1,Integer.parseInt(newValue));
            cs.setString(2, Client_id);
            rs = cs.executeQuery();

            rs.close();
            cs.close();
            System.out.println(Client_id + CHANGE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }
    /**
     * 고객의 이메일을 업데이트하는 메소드 입니다.
     * @param Client_id
     * @param newValue
     */
    @Override
    public void updateUserEmail(String Client_id,String newValue){
        try {
            connection.setAutoCommit(true);
            cs = connection.prepareCall("{call DB_USER_UPDATE_EMAIL(?,?)}");
            cs.setString(1,newValue);
            cs.setString(2, Client_id);
            rs = cs.executeQuery();

            rs.close();
            cs.close();
            System.out.println(Client_id + CHANGE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }
    /**
     * 고객의 주소를 업데이트하는 메소드 입니다.
     * @param Client_id
     * @param newValue
     */
    @Override
    public void updateUserAddress(String Client_id,String newValue){
        try {
            connection.setAutoCommit(true);
            cs = connection.prepareCall("{call DB_USER_UPDATE_ADDRESS(?,?)}");
            cs.setString(1,newValue);
            cs.setString(2, Client_id);
            rs = cs.executeQuery();

            rs.close();
            cs.close();
            System.out.println(Client_id + CHANGE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    /**
     * 관리자 본인의 연락처를 수정하는 메소드입니다.
     * @param Admin_number
     * @param newValue
     */
    @Override
    public void updateSelfAdminPhone(String Admin_number, String newValue) {
        try {
            connection.setAutoCommit(true);
            cs = connection.prepareCall("{call DB_ADMIN_UPDATE_PHONE(?,?)}");
            cs.setInt(1,Integer.parseInt(newValue));
            cs.setString(2, Admin_number);
            rs = cs.executeQuery();

            rs.close();
            cs.close();
            System.out.println(Admin_number + CHANGE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    /**
     * 관리자 본인의 이메일 수정하는 메소드입니다.
     * @param Admin_number
     * @param newValue
     */
    @Override
    public void updateSelfAdminEmail(String Admin_number, String newValue) {
        try {
            connection.setAutoCommit(true);
            cs = connection.prepareCall("{call DB_ADMIN_UPDATE_Email(?,?)}");
            cs.setString(1,newValue);
            cs.setString(2, Admin_number);
            rs = cs.executeQuery();

            rs.close();
            cs.close();
            System.out.println(Admin_number + CHANGE_SUCCESS.getText());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

    /**
     * 관리자 본인의 주소를 수정하는 메소드입니다.
     * @param Admin_number
     * @param newValue
     */
    @Override
    public void updateSelfAdminAddress(String Admin_number, String newValue) {
        try {
            connection.setAutoCommit(true);
            cs = connection.prepareCall("{call DB_ADMIN_UPDATE_address(?,?)}");
            cs.setString(1,newValue);
            cs.setString(2, Admin_number);
            rs = cs.executeQuery();

            rs.close();
            cs.close();
            System.out.println(Admin_number + CHANGE_SUCCESS.getText());

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
            rs.close();
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
            rs.close();
            cs.close();

            return dto;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BuildifyException(ErrorCode.DB_READ_ALL_ERROR);
        }
    }

}
