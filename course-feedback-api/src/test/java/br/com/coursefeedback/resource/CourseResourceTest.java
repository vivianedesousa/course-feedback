package br.com.coursefeedback.resource;

import br.com.coursefeedback.course.dto.CourseResponseDTO;
import br.com.coursefeedback.course.dto.CreateCourseRequestDTO;
import br.com.coursefeedback.course.resource.CourseResource;
import br.com.coursefeedback.course.service.CourseService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseResourceTest {
    private CourseService courseService;
    private CourseResource courseResource;

    @BeforeEach
    void setup() throws Exception {
        courseService =
                Mockito.mock(CourseService.class);
        courseResource =
                new CourseResource();
        Field field =
                CourseResource.class.getDeclaredField("courseService");
        field.setAccessible(true);
        field.set(courseResource, courseService);
    }

    @Test
    void createCourse_success() {
        CreateCourseRequestDTO request =
                new CreateCourseRequestDTO(
                        "Java",
                        "Curso de Java"
                );
        CourseResponseDTO responseDTO =
                new CourseResponseDTO(
                        "course-1",
                        "Java",
                        "Curso de Java",
                        "2025-07-25T10:00:00"
                );
        Mockito.when(courseService.createCourse(request))
                .thenReturn(responseDTO);
        Response response =
                courseResource.createCourse(request);
        assertEquals(201, response.getStatus());
        assertNotNull(response.getEntity());
        Mockito.verify(courseService)
                .createCourse(request);
    }

    @Test
    void getCourseById_success() {

        CourseResponseDTO responseDTO =
                new CourseResponseDTO(
                        "course-1",
                        "Java",
                        "Curso de Java",
                        "2025-07-25T10:00:00"
                );

        Mockito.when(courseService.getCourseById("course-1"))
                .thenReturn(responseDTO);

        Response response =
                courseResource.getCourseById("course-1");

        assertEquals(200, response.getStatus());

        assertNotNull(response.getEntity());

        Mockito.verify(courseService)
                .getCourseById("course-1");
    }

    @Test
    void getAllCourses_success() {

        List<CourseResponseDTO> courses = List.of(
                new CourseResponseDTO(
                        "course-1",
                        "Java",
                        "Curso de Java",
                        "2025-07-25T10:00:00"
                )
        );

        Mockito.when(courseService.getAllCourses())
                .thenReturn(courses);

        Response response = courseResource.getAllCourses();

        assertEquals(200, response.getStatus());

        @SuppressWarnings("unchecked")
        List<CourseResponseDTO> result =
                (List<CourseResponseDTO>) response.getEntity();

        assertEquals(1, result.size());

        Mockito.verify(courseService)
                .getAllCourses();
    }
}