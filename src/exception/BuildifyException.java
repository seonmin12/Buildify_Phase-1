package exception;

import common.ErrorCode;

public class BuildifyException extends RuntimeException{
    private final ErrorCode error;

    public BuildifyException(ErrorCode error) {
        super(error.getText());
        this.error = error;
    }
}
