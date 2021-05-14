package dev.mazurkiewicz.exception;

import java.util.Map;

public class ErrorResponse {
    private final int status;
    private final String message;
    private final String path;
    private final ErrorType error;
    private final Map<String, ErrorInfo> fieldsErrorInfo;

    public ErrorResponse(int status, String message, String path, ErrorType error) {
        this(status, message, path, error, null);
    }

    public ErrorResponse(int status, String message, String path, ErrorType error, Map<String, ErrorInfo> fieldsErrorInfo) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.error = error;
        this.fieldsErrorInfo = fieldsErrorInfo;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public ErrorType getError() {
        return error;
    }

    public Map<String, ErrorInfo> getFieldsErrorInfo() {
        return fieldsErrorInfo;
    }
}
