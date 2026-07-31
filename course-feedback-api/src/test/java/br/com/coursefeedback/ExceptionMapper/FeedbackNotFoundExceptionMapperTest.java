package br.com.coursefeedback.ExceptionMapper;

import br.com.coursefeedback.shared.ExceptionMapper.FeedbackNotFoundExceptionMapper;
import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
import br.com.coursefeedback.shared.exception.FeedbackNotFoundException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackNotFoundExceptionMapperTest {

    @Test
    void toResponse_shouldReturnNotFoundResponse() {

        FeedbackNotFoundException exception =
                new FeedbackNotFoundException("Feedback was not found.");

        FeedbackNotFoundExceptionMapper mapper =
                new FeedbackNotFoundExceptionMapper();

        Response response = mapper.toResponse(exception);

        assertEquals(404, response.getStatus());

        ErrorResponseDTO error =
                (ErrorResponseDTO) response.getEntity();

        assertNotNull(error);
        assertEquals(404, error.getStatus());
        assertEquals("Feedback was not found.", error.getMessage());
    }
}