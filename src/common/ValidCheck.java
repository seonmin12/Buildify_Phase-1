package common;

import java.util.Scanner;

import static common.ErrorCode.*;

// 정규표현식 Class
public class ValidCheck {

    private static Scanner scanner = new Scanner(System.in);

    public final String NUMBER_REGEX = "^[0-9]*$";
    public final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";


    /**
     * 정규표현식 검증하여 입력하는 메소드
     * @param regex
     * @return String
     */
    public String inputStringRegex(String regex){
        String str;
        do {
            str = scanner.nextLine();
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
            if (str.matches(NUMBER_REGEX)) {
                input = Integer.parseInt(str);
                break;
            }
            System.out.println(ERROR_NUM.getText());
        }while (!str.matches(NUMBER_REGEX) || str.isEmpty());
        return input;
    }
}
