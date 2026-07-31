package br.com.coursefeedback.shared.ExceptionMapper;

import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
import br.com.coursefeedback.shared.exception.CourseNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CourseNotFoundExceptionMapper implements ExceptionMapper<CourseNotFoundException> {

    @Override
    public Response toResponse(CourseNotFoundException exception) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                Response.Status.NOT_FOUND.getStatusCode(),
                exception.getMessage()
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }


}

