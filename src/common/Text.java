package common;

public enum Text {
    ACCESS_ADMIN("관리자만 접근 가능한 메뉴입니다."),
    ACCESS_SUPER_ADMIN("총 관리자만 접근 가능한 메뉴입니다."),
    SUPER_ADMIN("총관리자"),
    LOCAL_ADMIN("창고관리자"),
    ACCESS_USER_INPUT("승인할 회원 ID를 입력하세요."),
    SEARCH_USER_INPUT("검색할 회원 Number를 입력해주세요."),
    SEARCH_ADMIN_INPUT("검색할 관리자 Number를 입력해주세요."),
    INPUT_CHOICE("입력 : "),
    INPUT_NEW_VALUE("변경할 정보를 입력하세요."),
    CHOICE_INPUT_INFO("변경할 정보를 선택하세요."),
    CHANGE_SUCCESS("님의 정보가 변경되었습니다."),
    USER_CHANGE_FAIL("회원 정보 변경 실패: 존재하지 않는 회원 ID"),
    ADMIN_CHANGE_FAIL("관리자 정보 변경 실패: 존재하지 않는 관리자 ID"),
    PROGRAM_START_1("+--------------------------------------+"),
    PROGRAM_START_2("|      Warehouse Management System     |"),
    PROGRAM_START_3("|                                      |"),
    PROGRAM_START_4("|           Buildify System            |"),
    PROGRAM_START_5("|                                      |"),
    PROGRAM_START_6("+--------------------------------------+"),
    EXIT("종료"),
    START_LOGIN("로그인을 시작합니다."),
    LOGOUT("로그아웃"),
    LOGIN_MENU("1.관리자 로그인 2. 회원 로그인 3.프로그램종료"),
    ADMIN_MENU("1.회원관리 2.입고관리 3.출고관리 4.재고관리 5.로그아웃"),
    ADMIN_USER_MANAGEMENT_MENU("1.승인대기회원 2.회원조회 3.회원정보수정 4.내 정보 수정 5.관리자 수정 및 조회(총관리자만가능)"),
    ADMIN_MENU_CHOICE("1.승인 2.이전메뉴"),
    ADMIN_USER_SERACH("1.전체 회원 조회 2.회원 검색"),
    USER_INFO_CHANGER("회원정보수정"),
    LOCAL_ADMIN_MENU("1.창고관리자 조회 2.창고관리자 수정");

    private final String text;
      Text(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
