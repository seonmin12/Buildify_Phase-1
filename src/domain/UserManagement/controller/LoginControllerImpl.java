package domain.UserManagement.controller;

import common.ValidCheck;
import domain.UserManagement.service.LoginService;
import dto.AdminDto;

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
        System.out.println("ID 입력: ");
        String id = validCheck.inputStringRegex(validCheck.ID_REGEX);

        adminDto = loginService.adminlogin(id);

        if (adminDto == null) {
            System.out.println("ID가 올바르지 않습니다.");
//            System.exit(0);
            return null;
        }

        // 비밀번호 최대 3회 입력 가능
        while (count < 3) {
            System.out.println("PW 입력: ");
            String pw = validCheck.inputStringRegex(validCheck.PW_REGEX);

            // 비밀번호 일치하면 로그인 성공
            if (pw.equals(adminDto.getAdminPw())) {
                System.out.println(adminDto.getAdminName() +"관리자님 로그인 성공!");
                return adminDto;
            }

            count++;
            System.out.println("비밀번호가 틀렸습니다. (" + count + "/3)");

            // 3회 오류 시 로그인 실패 메시지 출력 후 종료
            if (count == 3) {
                System.out.println("로그인 실패: 비밀번호 3회 오류");
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
