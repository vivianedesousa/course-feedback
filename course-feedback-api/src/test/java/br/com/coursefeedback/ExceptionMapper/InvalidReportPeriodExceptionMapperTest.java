package br.com.coursefeedback.ExceptionMapper;

import br.com.coursefeedback.shared.ExceptionMapper.InvalidReportPeriodExceptionMapper;
import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
import br.com.coursefeedback.shared.exception.InvalidReportPeriodException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidReportPeriodExceptionMapperTest {
    @Test
    void toResponse_shouldReturnBadRequestResponse() {
        InvalidReportPeriodException exception =
                new InvalidReportPeriodException("Invalid report period.");
        InvalidReportPeriodExceptionMapper mapper =
                new InvalidReportPeriodExceptionMapper();
        Response response = mapper.toResponse(exception);
        assertEquals(400, response.getStatus());
        ErrorResponseDTO error =
                (ErrorResponseDTO) response.getEntity();
        assertNotNull(error);
        assertEquals(400, error.getStatus());
        assertEquals("Invalid report period.", error.getMessage());
    }
}
