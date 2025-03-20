package exception;

import common.ErrorCode;

public class InventoryException extends RuntimeException {
    private final ErrorCode error;

    public InventoryException(ErrorCode error) {
        super(error.getText());
        this.error = error;
    }
}
