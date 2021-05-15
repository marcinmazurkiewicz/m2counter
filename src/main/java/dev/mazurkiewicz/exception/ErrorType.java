package dev.mazurkiewicz.exception;

import java.util.Arrays;
import java.util.List;

public enum ErrorType {
    INVALID_LENGTH(List.of("Size", "Length")),
    EMPTY(List.of("NotEmpty", "NotNull", "NotBlank", "NotNullFile")),
    NOT_MAIL(List.of("Email")),
    NOT_MATCH(List.of("PasswordMatch")),
    NOT_UNIQUE(List.of("UniqueMail")),
    FORBIDDEN(List.of("ForbiddenAccessException")),
    CREDENTIALS_ERROR(List.of("BadCredentialsException")),
    TOKEN_EXPIRED(List.of("TokenExpiredException", "ExpiredJwtException")),
    UNAUTHORIZED(List.of("UnauthorizedAccessException")),
    VALIDATION_ERROR(List.of("ConstraintViolationException")),
    NOT_FOUND(List.of("ResourceNotFoundException")),
    UNKNOWN(List.of());

    private final List<String> codeNames;

    ErrorType(List<String> codeNames) {
        this.codeNames = codeNames;
    }

    public static ErrorType valueOfCode(String codeName) {

        return Arrays.stream(ErrorType.values())
                .filter(errorType -> errorType.codeNames.contains(codeName))
                .findFirst()
                .orElse(UNKNOWN);
    }
}