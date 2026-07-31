package br.com.coursefeedback.shared.ExceptionMapper;

import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
import br.com.coursefeedback.shared.exception.InvalidReportPeriodException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidReportPeriodExceptionMapper
        implements ExceptionMapper<InvalidReportPeriodException> {

    @Override
    public Response toResponse(InvalidReportPeriodException exception) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                Response.Status.BAD_REQUEST.getStatusCode(),
                exception.getMessage()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }
}