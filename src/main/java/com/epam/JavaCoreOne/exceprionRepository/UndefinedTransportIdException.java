package com.epam.JavaCoreOne.exceprionRepository;

public final class UndefinedTransportIdException extends RepositoryExceptions {
    private String message = "";
    private Throwable cause = null;

    public UndefinedTransportIdException() {
        super();
    }

    public UndefinedTransportIdException(String message) {
        super(message);
        this.message = message;
    }

    public UndefinedTransportIdException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public UndefinedTransportIdException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    protected UndefinedTransportIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "UndefinedTransportIdException{" +
                "message='" + message + '\'' +
                ", cause=" + cause +
                '}';
    }
}
