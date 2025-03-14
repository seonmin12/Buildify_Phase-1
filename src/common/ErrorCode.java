package common;

public enum ErrorCode {
    ERROR_NUM("숫자를 입력하세요."),
    ERROR_INPUT("올바른 형식으로 입력하세요.");


    private final String text;
    ErrorCode(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
