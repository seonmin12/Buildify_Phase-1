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
    ADMIN_USER_MANAGEMENT_MENU("1.승인대기회원 2.회원조회 3.회원정보수정 4.내 정보 조회 및 수정 5.관리자 수정 및 조회(총관리자만가능)"),
    ADMIN_MENU_CHOICE("1.승인 2.이전메뉴"),
    ADMIN_USER_SERACH("1.전체 회원 조회 2.회원 검색"),
    USER_INFO_CHANGER("회원정보수정"),
    LOCAL_ADMIN_MENU("1.창고관리자 조회 2.창고관리자 수정"),
    SELECT_UPDATE("1.연락처  2.이메일  3.주소"),
    ROUND_BAR("====================================================================================================================="),
    SELECT_MY_INFO("1. 내 정보 조회 2. 내 정보 수정"),
    PASSWORD_ERROR("비밀번호가 틀렸습니다. ("),
    ID_ERROR("ID가 올바르지 않습니다."),
    INPUT_ID("ID 입력 : "),
    INPUT_PW("PW 입력: "),
    LOGIN_FAIL_PW_ERROR("로그인 실패: 비밀번호 3회 오류"),
    ADMIN_LOGIN_SUCCESS("관리자님 로그인 성공!"),
    USER_LIST(String.format("%-8s %-6s %-15s %-25s %-18s %-12s %-4s\n",
            "회원ID", "이름", "연락처", "이메일", "주소", "가입일", "상태")),
    PENDING_USER_LIST(String.format("%-10s %-8s %-15s %-25s %-20s %-12s %-10s\n",
             "회원ID", "이름", "연락처", "이메일", "주소", "가입일", "희망계약면적")),
    AVAILABLE_WAREHOUSE("계약 가능한 창고 면적 : "),
    SEARCH_USER_MENU(String.format("%-10s %-10s %-15s %-25s %-20s %-12s %-6s\n",
            "회원ID", "이름", "연락처", "이메일", "주소", "가입일", "상태")),
    NOT_FIND_USER("고객님을 찾을 수 없습니다."),
    ADMIN_LIST_MENU(String.format("%-12s %-10s %-8s %-25s %-12s %-20s %-15s\n",
            "관리자번호", "직급", "이름", "이메일", "입사일", "주소", "연락처")),
    USER_APPROVE_SUCCESS("고객이 승인되었습니다.");

    private final String text;
      Text(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
