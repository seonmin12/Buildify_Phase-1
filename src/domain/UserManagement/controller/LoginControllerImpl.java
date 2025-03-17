package domain.UserManagement.controller;

import common.ValidCheck;
import domain.UserManagement.service.LoginService;
import dto.AdminDto;

import static common.Text.*;

public class LoginControllerImpl implements LoginController {
    private final ValidCheck validCheck;
    private final LoginService loginService;
    private static AdminDto adminDto;

    public LoginControllerImpl(ValidCheck validCheck, LoginService loginService) {
        this.validCheck = validCheck;
        this.loginService = loginService;
    }

    /**
     * 관리자 로그인 기능
     * @return adminDto
     */
    @Override
    public AdminDto login() {
        int count = 0;
        System.out.println(INPUT_ID.getText());
        String id = validCheck.inputStringRegex(validCheck.ID_REGEX);

        adminDto = loginService.adminlogin(id);

        if (adminDto == null) {
            System.out.println(ID_ERROR.getText());
//            System.exit(0);
            return null;
        }

        // 비밀번호 최대 3회 입력 가능
        while (count < 3) {
            System.out.println(INPUT_PW.getText());
            String pw = validCheck.inputStringRegex(validCheck.PW_REGEX);

            // 비밀번호 일치하면 로그인 성공
            if (pw.equals(adminDto.getAdminPw())) {
                System.out.println(adminDto.getAdminName() +ADMIN_LOGIN_SUCCESS.getText());
                return adminDto;
            }

            count++;
            System.out.println(PASSWORD_ERROR.getText() + count + "/3)");

            // 3회 오류 시 로그인 실패 메시지 출력 후 종료
            if (count == 3) {
                System.out.println(LOGIN_FAIL_PW_ERROR.getText());
                System.exit(0);
            }
        }

        return null;
    }

    /**
     * 로그인 되어있는 관리자 객체 호출
     * @return adminDto
     */
    @Override
    public AdminDto getAdminLoginStatus() {
        return adminDto;
    }
}
