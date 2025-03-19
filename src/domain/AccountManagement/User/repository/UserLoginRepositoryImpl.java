package domain.AccountManagement.User.repository;

import config.DBConnection;
import dto.UserDto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserLoginRepositoryImpl implements UserLoginRepository {
    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    @Override
    public UserDto login(String userid, String inputPassword) {
        UserDto userDto = null;

        String sql = "SELECT * FROM user WHERE user_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // 🔥 저장된 "해싱된비밀번호:Salt" 가져오기
                String storedPasswordWithSalt = rs.getString("user_pw");

                // 🔥 비밀번호 + Salt 분리
                String[] parts = storedPasswordWithSalt.split(":");
                if (parts.length != 2) {
                    System.out.println("❌ 비밀번호 저장 형식 오류");
                    return null;
                }

                String storedHashedPassword = parts[0]; // 저장된 암호화된 비밀번호
                String salt = parts[1];                 // 저장된 Salt 값

                // 🔥 사용자가 입력한 비밀번호를 같은 방식으로 해싱
                String encryptedInputPassword = getEncrypt(inputPassword, salt);

                // 🔥 비밀번호 비교
                if (!storedHashedPassword.equals(encryptedInputPassword)) {
                    System.out.println("❌ 비밀번호가 일치하지 않습니다.");
                    return null;
                }

                // ✅ 로그인 성공 → 유저 정보 반환
                userDto = UserDto.builder()
                        .client_id(rs.getString("client_id"))
                        .user_name(rs.getString("user_name"))
                        .user_phone(rs.getString("user_phone"))
                        .user_email(rs.getString("user_email"))
                        .user_adress(rs.getString("user_adress"))
                        .user_enterday(rs.getDate("user_enterday"))
                        .user_status(rs.getInt("user_status"))
                        .user_ware_size(rs.getBigDecimal("user_ware_size"))
                        .user_ware_use(rs.getBigDecimal("user_ware_use"))
                        .build();
            } else {
                System.out.println("❌ 존재하지 않는 사용자입니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDto;
    }

    public String getEncrypt(String pwd, String salt) {
        String result= "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((pwd + salt).getBytes());
            byte[] pwdSalt = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : pwdSalt) {
                sb.append(String.format("%02x", b));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
