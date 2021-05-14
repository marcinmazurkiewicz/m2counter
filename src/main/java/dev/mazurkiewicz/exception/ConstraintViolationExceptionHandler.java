package dev.mazurkiewicz.exception;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException e) {
        Map<String, ErrorInfo> errors = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolationDescriptor::new)
                .collect(Collectors.toMap(
                        ConstraintViolationDescriptor::getFieldName,
                        ConstraintViolationDescriptor::getErrorInfo
                        )
                );
        ErrorResponse response = new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(),
                "Some fields are invalid", uriInfo.getPath(), ErrorType.VALIDATION_ERROR, errors);
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}
