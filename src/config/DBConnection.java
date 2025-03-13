package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("resources/config.properties")) {
            if (input == null) {
                throw new IOException("설정 파일을 찾을 수 없습니다.");
            }

            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("DB_URL");
            user = properties.getProperty("DB_USER");
            password = properties.getProperty("DB_PASSWORD");

            System.out.println("DB 연결 정보 로드 완료!");
        } catch (IOException e) {
            throw new RuntimeException("설정 파일을 로드하는 중 오류 발생", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스 연결 실패", e);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("데이터베이스 연결 성공!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
