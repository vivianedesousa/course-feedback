//package br.com.coursefeedback.ExceptionMapper;
//import br.com.coursefeedback.shared.ExceptionMapper.ThrowableExceptionMapper;
//import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
//import jakarta.ws.rs.core.Response;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//class ThrowableExceptionMapperTest {
//    @Test
//    void toResponse_shouldReturnInternalServerError() {
//        Throwable exception =
//                new RuntimeException("Unexpected error");
//        ThrowableExceptionMapper mapper =
//                new ThrowableExceptionMapper();
//        Response response = mapper.toResponse(exception);
//        assertEquals(500, response.getStatus());
//        ErrorResponseDTO error =
//                (ErrorResponseDTO) response.getEntity();
//        assertNotNull(error);
//        assertEquals(500, error.getStatus());
//        assertEquals("An internal server error occurred.",
//                error.getMessage());
//    }
//}
