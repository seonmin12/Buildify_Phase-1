package common;

public enum Text {
    TEST("test");


    private final String text;
      Text(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
