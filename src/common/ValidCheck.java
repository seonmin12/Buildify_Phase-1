package common;

import java.math.BigDecimal;
import java.util.Scanner;

import static common.ErrorCode.*;

// 정규표현식 Class
public class ValidCheck {

    private static Scanner scanner = new Scanner(System.in);

    //숫자 입력
    public final String NUMBER_REGEX = "^[0-9]*$";
    //이메일
    public final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    //ID 영어+문자 12글자 제한
    public final String ID_REGEX = "^[A-Za-z\\d]{4,12}";
    //PW 양어+숫자+특수문자 조합 8글자 이상
    public final String PW_REGEX ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    // 유저 이메일 형식 정규식
    public final String SIGN_UP_EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    // 유저 비밀번호 (영문 + 숫자 + 특수문자 포함, 8~15자) 정규식
    public final String SIGN_UP_PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$";
    // 유저 이름 (한글/영문, 2~30자)
    public final String SIGN_UP_NAME_REGEX = "^[가-힣a-zA-Z]{2,30}$";
    // 연락처 (국내번호 010-xxxx-xxxx 또는 02-xxx-xxxx, 해외번호도 고려)
    public final String SIGN_UP_PHONE_REGEX = "^(\\+\\d{1,3}[- ]?)?(\\d{2,4}[- ]?)?\\d{3,4}[- ]?\\d{4}$";
    // 주소 (한글, 영문, 숫자, 기본 특수문자 포함)
    public final String SIGN_UP_ADDRESS_REGEX = "^[가-힣A-Za-z0-9\\s,.-]{5,100}$";
    // 사업자 등록번호 (한국 기준: 000-00-00000)
    public final String SIGN_UP_BUSINESS_NUMBER_REGEX = "^\\d{3}-\\d{2}-\\d{5}$";

    // 브랜드명 (VARCHAR(20), 한글/영문 공백 포함, 2~20자)
    public final String PRODUCT_BRAND_REGEX = "^[가-힣a-zA-Z\\s]{2,20}$";

    // 상품명 (VARCHAR(30), 한글/영문/숫자/공백 포함, 2~30자)
    public final String PRODUCT_NAME_REGEX = "^[가-힣a-zA-Z0-9\\s]{2,30}$";

    // 상품 가격 (INT, 숫자만 허용, 1~10자리)
    public final String PRODUCT_PRICE_REGEX = "^[0-9]{1,10}$";

    // 상품 코드 (INT, 숫자만 허용, 1~10자리)
    public final String PRODUCT_CODE_REGEX = "^[0-9]{1,10}$";

    // 카테고리 (VARCHAR(20), 한글/영문 2~20자)
    public final String PRODUCT_CATEGORY_REGEX = "^[가-힣a-zA-Z]{2,20}$";

    // 사이즈 (DECIMAL(10,2), 정수 또는 소수점 이하 2자리까지 허용)
    public final String PRODUCT_SIZE_REGEX = "^[0-9]{1,8}(\\.[0-9]{1,2})?$";

    public final String AMOUNT_REGEX = "^[1-999]*$";





    /**
     * 정규표현식 검증하여 입력하는 메소드
     * @param regex
     * @return String
     */
    public String inputStringRegex(String regex){
        String str;
        do {
            str = scanner.nextLine();

            if (str.isEmpty()) { // 빈 입력(Enter) 방지
                System.out.println("값을 입력하세요.");
                continue; // 다시 입력 요청
            }

            if (!str.matches(regex)){
                System.out.println(ERROR_INPUT.getText());
            }
        }while (!str.matches(regex) || str.isEmpty());
            return str;
    }


    /**
     * 숫자 입력하는 메소드
     * @return input
     */
    public int inputNumRegex(){
        String str;
        int input = 0;
        do {
            str = scanner.nextLine();

            if (str.isEmpty()) { // 빈 입력(Enter) 방지
                System.out.println("값을 입력하세요.");
                continue; // 다시 입력 요청
            }

            if (str.matches(NUMBER_REGEX)) {
                input = Integer.parseInt(str);
                break;
            }
            System.out.println(ERROR_NUM.getText());
        }while (!str.matches(NUMBER_REGEX) || str.isEmpty() );
        return input;
    }



    /**
     * 정규표현식 검증하여 정수 입력하는 메소드
     * @param regex 정규식 패턴
     * @return 검증된 정수값
     */
    public int inputIntRegex(String regex) {
        String str;
        int input = 0;
        do {
            str = scanner.nextLine().trim();

            if (str.isEmpty()) { // 빈 입력(Enter) 방지
                System.out.println("값을 입력하세요.");
                continue; // 다시 입력 요청
            }

            if (str.matches(regex)) {
                try {
                    input = Integer.parseInt(str);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(ERROR_NUM.getText());
                }
            }
            System.out.println(ERROR_NUM.getText());
        } while (!str.matches(regex) || str.isEmpty());
        return input;
    }

    /**
     * 정규표현식 검증하여 소수 포함 숫자 입력하는 메소드 (DECIMAL 타입 지원)
     * @param regex 정규식 패턴
     * @return 검증된 BigDecimal 값
     */
    public BigDecimal inputDecimalRegex(String regex) {
        String str;
        BigDecimal input = null;
        do {
            str = scanner.nextLine().trim();

            if (str.isEmpty()) { // 빈 입력(Enter) 방지
                System.out.println("값을 입력하세요.");
                continue; // 다시 입력 요청
            }

            if (str.matches(regex)) {
                try {
                    input = new BigDecimal(str);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(ERROR_NUM.getText());
                }
            }
            System.out.println(ERROR_NUM.getText());
        } while (!str.matches(regex) || str.isEmpty());
        return input;
    }

    // 입력 값 제한 없는 입력 메소드
    public String inputAnyString() {
        String str;
        do {
            str = scanner.nextLine().trim(); // 🔥 입력값 앞뒤 공백 제거

            if (str.isEmpty()) { // 빈 입력 방지
                System.out.println("값을 입력하세요.");
                continue;
            }

            return str; // 정상 입력 시 반환

        } while (true); // 무한 루프, 정상 입력 시 반환됨
    }

}
