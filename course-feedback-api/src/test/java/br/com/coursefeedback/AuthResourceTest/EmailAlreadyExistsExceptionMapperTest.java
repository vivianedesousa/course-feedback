package br.com.coursefeedback.AuthResourceTest;

import br.com.coursefeedback.shared.ExceptionMapper.EmailAlreadyExistsExceptionMapper;
import br.com.coursefeedback.shared.exception.EmailAlreadyExistsException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmailAlreadyExistsExceptionMapperTest {
    @Test
    void toResponse_shouldReturnConflict() {
        EmailAlreadyExistsException exception =
                new EmailAlreadyExistsException();
        EmailAlreadyExistsExceptionMapper mapper =
                new EmailAlreadyExistsExceptionMapper();
        Response response = mapper.toResponse(exception);
        assertEquals(409, response.getStatus());
        Map<String, String> body =
                (Map<String, String>) response.getEntity();

        assertNotNull(body);
        assertEquals("Email already registered.",
                body.get("message"));
    }
}