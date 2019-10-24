package com.epam.JavaCoreOne.exceprion;

public abstract class RepositoryExceptions extends Exception {
    public RepositoryExceptions() {
        super();
    }

    public RepositoryExceptions(String message) {
        super(message);
    }

    public RepositoryExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryExceptions(Throwable cause) {
        super(cause);
    }

    protected RepositoryExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
