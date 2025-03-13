package common;

public enum ErrorCode {
    TEST("test");


    private final String text;
    ErrorCode(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
