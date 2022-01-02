package app;

public class MyServerException extends RuntimeException {
    public MyServerException() {
    }

    public MyServerException(String message) {
        super(message);
    }

    public MyServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
