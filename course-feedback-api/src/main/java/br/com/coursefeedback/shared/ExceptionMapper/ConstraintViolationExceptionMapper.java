package br.com.coursefeedback.shared.ExceptionMapper;

import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        String message = exception.getConstraintViolations()
                .iterator()
                .next()
                .getMessage();

        ErrorResponseDTO error = new ErrorResponseDTO(
                Response.Status.BAD_REQUEST.getStatusCode(),
                message
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }
}

