package dev.mazurkiewicz.exception;

public class ResourceNotFoundException extends ResponseMappedException {
    public ResourceNotFoundException(String msg) {
        super(404, ErrorType.NOT_FOUND, msg);
    }
}