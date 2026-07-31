//package br.com.coursefeedback.shared.ExceptionMapper;
//import br.com.coursefeedback.shared.dto.ErrorResponseDTO;
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.ext.ExceptionMapper;
//import jakarta.ws.rs.ext.Provider;

// deixem comentado porque ele estava bloqueando o swagger
//    @Provider
//    public class ThrowableExceptionMapper
//            implements ExceptionMapper<Throwable> {
//        @Override
//        public Response toResponse(Throwable exception) {
//               exception.printStackTrace();
//            ErrorResponseDTO error = new ErrorResponseDTO(
//                    Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
//                    "An internal server error occurred."
//            );
//
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity(error)
//                    .build();
//        }
//}
//
