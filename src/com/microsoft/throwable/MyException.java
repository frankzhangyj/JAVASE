package com.microsoft.throwable;


public class MyException extends RuntimeException {
    public MyException() {
    }

    public MyException(ErrorCode errorCode) {
        super(errorCode.getCode() + " " + errorCode.getMsg());
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
