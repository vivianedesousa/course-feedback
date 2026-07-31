package br.com.coursefeedback.ExceptionMapper;

import br.com.coursefeedback.shared.ExceptionMapper.CourseNotFoundExceptionMapper;
import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
import br.com.coursefeedback.shared.exception.CourseNotFoundException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseNotFoundExceptionMapperTest {

    @Test
    void toResponse_shouldReturnNotFoundResponse() {

        CourseNotFoundException exception =
                new CourseNotFoundException("Course was not found.");

        CourseNotFoundExceptionMapper mapper =
                new CourseNotFoundExceptionMapper();

        Response response = mapper.toResponse(exception);

        assertEquals(404, response.getStatus());

        ErrorResponseDTO error =
                (ErrorResponseDTO) response.getEntity();

        assertNotNull(error);
        assertEquals(404, error.getStatus());
        assertEquals("Course was not found.", error.getMessage());
    }
}
