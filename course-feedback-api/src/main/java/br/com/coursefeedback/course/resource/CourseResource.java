package br.com.coursefeedback.course.resource;

import br.com.coursefeedback.course.dto.CreateCourseRequestDTO;
import br.com.coursefeedback.course.dto.CourseResponseDTO;
import br.com.coursefeedback.course.service.CourseService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Tag(
        name = "Courses",
        description = "Endpoints responsible for managing courses, including course creation and retrieval."
)
public class CourseResource {
    @Inject
    CourseService courseService;

    @POST
    public Response createCourse(@Valid CreateCourseRequestDTO requestDTO) {

        CourseResponseDTO response = courseService.createCourse(requestDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{courseId}")
    @Operation(
            summary = "Get course by ID",
            description = "Retrieves the details of a specific course using its unique identifier."
    )
    public Response getCourseById(@PathParam("courseId") String courseId) {
        CourseResponseDTO response = courseService.getCourseById(courseId);
        return Response
                .ok(response)
                .build();
    }


    @GET
    @Operation(
            summary = "List all courses",
            description = "Returns a list containing all registered courses."
    )
    public Response getAllCourses() {
        List<CourseResponseDTO> response = courseService.getAllCourses();
        return Response
                .ok(response)
                .build();
    }
}
