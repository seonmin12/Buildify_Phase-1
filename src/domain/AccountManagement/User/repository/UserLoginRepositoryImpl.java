package domain.AccountManagement.User.repository;

import config.DBConnection;
import dto.UserDto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * {@link UserLoginRepository}의 구현체.
 *
 * <p>사용자의 로그인 인증을 수행하며, 저장된 해시된 비밀번호와 비교하여 로그인 여부를 결정합니다.</p>
 *
 * <p>비밀번호는 SHA-256 해시 알고리즘과 Salt 값을 사용하여 암호화되어 저장됩니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class UserLoginRepositoryImpl implements UserLoginRepository {
    private final Connection connection = DBConnection.getConnection();

    /**
     * 사용자 로그인 인증을 수행합니다.
     *
     * <p>입력된 사용자 ID를 조회하고, 저장된 해시된 비밀번호 및 Salt 값을 이용하여
     * 사용자가 입력한 비밀번호를 검증합니다.</p>
     *
     * @param userid 로그인할 사용자 ID
     * @param inputPassword 사용자가 입력한 비밀번호
     * @return 로그인된 사용자의 {@link UserDto} 객체, 인증 실패 시 {@code null} 반환
     */
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
                        .business_number(rs.getString("business_number"))
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

    /**
     * 주어진 비밀번호에 Salt 값을 적용하여 SHA-256 해시 알고리즘으로 암호화합니다.
     *
     * <p>비밀번호와 Salt 값을 결합하여 해싱한 후, 16진수 문자열로 변환하여 반환합니다.</p>
     *
     * @param pwd 사용자가 입력한 원본 비밀번호
     * @param salt 비밀번호 암호화를 위한 Salt 값
     * @return SHA-256으로 암호화된 비밀번호
     * @throws RuntimeException 암호화 알고리즘을 찾을 수 없는 경우 예외 발생
     */
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
