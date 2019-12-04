package com.epam.JavaCoreOne.exceprion;

public final class EmptyTransportException extends RepositoryExceptions {
    private String message = "";
    private Throwable cause = null;

    public EmptyTransportException() {
        super();
    }

    public EmptyTransportException(String message) {
        super(message);
        this.message = message;
    }

    public EmptyTransportException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public EmptyTransportException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    protected EmptyTransportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "EmptyTransportException{" +
                "message=" + message +
                ", cause=" + cause +
                "}";
    }
}
