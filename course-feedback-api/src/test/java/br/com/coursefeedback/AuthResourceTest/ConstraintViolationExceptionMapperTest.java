package br.com.coursefeedback.AuthResourceTest;

import br.com.coursefeedback.shared.ExceptionMapper.ConstraintViolationExceptionMapper;
import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintViolationExceptionMapperTest {

    @Test
    void toResponse_shouldReturnBadRequest() {

        ConstraintViolation<?> violation =
                Mockito.mock(ConstraintViolation.class);

        Mockito.when(violation.getMessage())
                .thenReturn("Password must have at least 8 characters.");

        ConstraintViolationException exception =
                new ConstraintViolationException(Set.of(violation));

        ConstraintViolationExceptionMapper mapper =
                new ConstraintViolationExceptionMapper();

        Response response = mapper.toResponse(exception);

        assertEquals(400, response.getStatus());

        ErrorResponseDTO body =
                (ErrorResponseDTO) response.getEntity();

        assertNotNull(body);
        assertEquals(400, body.getStatus());
        assertEquals(
                "Password must have at least 8 characters.",
                body.getMessage()
        );
    }
}