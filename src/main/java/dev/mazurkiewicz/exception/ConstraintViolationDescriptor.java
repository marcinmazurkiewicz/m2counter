package dev.mazurkiewicz.exception;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ConstraintViolationDescriptor {
    private final ConstraintViolation<?> constraintViolation;

    public ConstraintViolationDescriptor(ConstraintViolation<?> constraintViolation) {
        this.constraintViolation = constraintViolation;
    }

    public String getFieldName() {
        Path path = constraintViolation.getPropertyPath();
        String pathString = path.toString().trim();
        String[] pathElements = pathString.split("\\.");
        return pathElements[pathElements.length - 1];
    }

    public ErrorInfo getErrorInfo() {
        return new ErrorInfo(ErrorType.valueOfCode(getConstraintName()), constraintViolation.getMessage());
    }

    public String getConstraintName() {
        return getAnnotationName(constraintViolation.getConstraintDescriptor());
    }

    private String getAnnotationName(ConstraintDescriptor<?> descriptor) {
        Annotation annotation = descriptor.getAnnotation();
        return annotation.annotationType().getSimpleName();
    }

}
