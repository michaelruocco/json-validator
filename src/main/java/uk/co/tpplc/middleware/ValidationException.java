package uk.co.tpplc.middleware;

public class ValidationException extends RuntimeException {

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message) {
        super(message);
    }

}
