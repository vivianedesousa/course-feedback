package br.com.coursefeedback.AuthResourceTest;

import br.com.coursefeedback.shared.ExceptionMapper.InvalidCredentialsExceptionMapper;
import br.com.coursefeedback.shared.exception.InvalidCredentialsException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidCredentialsExceptionMapperTest {
    @Test
    void toResponse_shouldReturnUnauthorized() {

        InvalidCredentialsException exception =
                new InvalidCredentialsException();

        InvalidCredentialsExceptionMapper mapper =
                new InvalidCredentialsExceptionMapper();

        Response response = mapper.toResponse(exception);

        assertEquals(401, response.getStatus());

        Map<String, String> body =
                (Map<String, String>) response.getEntity();

        assertNotNull(body);
        assertEquals(
                "Invalid email or password.",
                body.get("message")
        );
    }

}
