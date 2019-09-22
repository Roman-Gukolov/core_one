package com.epam.JavaCoreOne.exceprionRepository;

/**
 * Класс для обработки исключений
 */
public final class IncorrectInputData extends RepositoryExceptions {
    private String message = "";
    private Throwable cause = null;

    public IncorrectInputData() {
        super();
    }

    public IncorrectInputData(String message) {
        super(message);
        this.message = message;
    }

    public IncorrectInputData(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public IncorrectInputData(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    protected IncorrectInputData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "IncorrectInputData{" +
                "message='" + message + '\'' +
                ", cause=" + cause +
                '}';
    }
}
