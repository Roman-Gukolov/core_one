package com.epam.JavaCoreOne.exceprionRepository;

public final class NullTransportException extends RepositoryExceptions {
    private String message = "";
    private Throwable cause = null;

    public NullTransportException() {
        super();
    }

    public NullTransportException(String message) {
        super(message);
        this.message = message;
    }

    public NullTransportException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public NullTransportException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    protected NullTransportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "NullTransportException{" +
                "message='" + message + '\'' +
                ", cause=" + cause +
                '}';
    }
}
