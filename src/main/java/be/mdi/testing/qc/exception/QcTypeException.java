package be.mdi.testing.qc.exception;

public class QcTypeException extends Exception {

    public QcTypeException(String message) {
        super(message);
    }

    public QcTypeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public QcTypeException(Throwable throwable) {
        super(throwable);
    }
}
