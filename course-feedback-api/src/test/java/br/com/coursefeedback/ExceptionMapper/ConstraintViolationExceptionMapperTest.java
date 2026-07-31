package br.com.coursefeedback.ExceptionMapper;

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
    void toResponse_shouldReturnBadRequestResponse() {
        ConstraintViolation<?> violation =
                Mockito.mock(ConstraintViolation.class);
        Mockito.when(violation.getMessage())
                .thenReturn("The course name is required.");
        ConstraintViolationException exception =
                new ConstraintViolationException(Set.of(violation));
        ConstraintViolationExceptionMapper mapper =
                new ConstraintViolationExceptionMapper();
        Response response = mapper.toResponse(exception);
        assertEquals(400, response.getStatus());
        ErrorResponseDTO error =
                (ErrorResponseDTO) response.getEntity();
        assertNotNull(error);
        assertEquals(400, error.getStatus());
        assertEquals("The course name is required.", error.getMessage());
    }
}