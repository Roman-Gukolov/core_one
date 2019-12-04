package com.epam.JavaCoreOne.exceprion;

/**
 * Класс для обработки исключений
 */
public final class IncorrectInputDataException extends RepositoryExceptions {
    private String message = "";
    private Throwable cause = null;

    public IncorrectInputDataException() {
        super();
    }

    public IncorrectInputDataException(String message) {
        super(message);
        this.message = message;
    }

    public IncorrectInputDataException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public IncorrectInputDataException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    protected IncorrectInputDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "IncorrectInputDataException{" +
                "message=" + message +
                ", cause=" + cause +
                "}";
    }
}
