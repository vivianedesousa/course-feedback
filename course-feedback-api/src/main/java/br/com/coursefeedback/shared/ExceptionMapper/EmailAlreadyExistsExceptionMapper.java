package br.com.coursefeedback.shared.ExceptionMapper;

import br.com.coursefeedback.shared.exception.EmailAlreadyExistsException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class EmailAlreadyExistsExceptionMapper
        implements ExceptionMapper<EmailAlreadyExistsException> {

    @Override
    public Response toResponse(EmailAlreadyExistsException exception) {
        // E-mail já cadastrado → 409 Conflict
        return Response.status(Response.Status.CONFLICT)
                .entity(Map.of("message", exception.getMessage()))
                .build();
    }
}
