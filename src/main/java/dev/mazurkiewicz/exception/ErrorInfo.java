package dev.mazurkiewicz.exception;

public class ErrorInfo {
    private final ErrorType errorType;
    private final String msg;

    public ErrorInfo(ErrorType errorType, String msg) {
        this.errorType = errorType;
        this.msg = msg;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getMsg() {
        return msg;
    }
}
