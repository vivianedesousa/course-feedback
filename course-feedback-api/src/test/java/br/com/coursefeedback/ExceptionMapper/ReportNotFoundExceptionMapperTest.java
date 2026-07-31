package br.com.coursefeedback.ExceptionMapper;

import br.com.coursefeedback.shared.ExceptionMapper.ReportNotFoundExceptionMapper;
import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
import br.com.coursefeedback.shared.exception.ReportNotFoundException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportNotFoundExceptionMapperTest {

    @Test
    void toResponse_shouldReturnNotFoundResponse() {

        ReportNotFoundException exception =
                new ReportNotFoundException("Report was not found.");

        ReportNotFoundExceptionMapper mapper =
                new ReportNotFoundExceptionMapper();

        Response response = mapper.toResponse(exception);

        assertEquals(404, response.getStatus());

        ErrorResponseDTO error =
                (ErrorResponseDTO) response.getEntity();

        assertNotNull(error);
        assertEquals(404, error.getStatus());
        assertEquals("Report was not found.", error.getMessage());
    }
}
