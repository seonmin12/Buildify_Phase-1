package exception;

import common.ErrorCode;

public class OutboundException extends RuntimeException {
    private final ErrorCode error;

    public OutboundException(ErrorCode error) {
        super(error.getText());
        this.error = error;
    }
}
