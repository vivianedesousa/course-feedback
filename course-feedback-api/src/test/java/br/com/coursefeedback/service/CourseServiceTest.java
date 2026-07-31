package br.com.coursefeedback.service;

import br.com.coursefeedback.course.domain.Course;
import br.com.coursefeedback.course.dto.CourseResponseDTO;
import br.com.coursefeedback.course.dto.CreateCourseRequestDTO;
import br.com.coursefeedback.course.repository.CourseRepository;
import br.com.coursefeedback.course.service.CourseService;
import br.com.coursefeedback.shared.exception.CourseNotFoundException;
import net.bytebuddy.ClassFileVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {
    private CourseRepository courseRepository;
    private CourseService courseService;

    @BeforeEach
    void setup() {
        courseRepository = Mockito.mock(CourseRepository.class);
        courseService = new CourseService(courseRepository);
    }

    @Test
    void createCourse_success() {

        CreateCourseRequestDTO request = new CreateCourseRequestDTO();
        request.setName("Software Engineering");
        request.setDescription("Bachelor's degree");
        CourseResponseDTO response = courseService.createCourse(request);
        assertEquals("Software Engineering", response.getName());
        assertEquals("Bachelor's degree", response.getDescription());
        assertNotNull(response.getCourseId());
        assertNotNull(response.getCreatedAt());

        Mockito.verify(courseRepository)
                .save(Mockito.any(Course.class));
    }


    @Test
    void getCourseById_found() {
        Course course = new Course();
        course.setCourseId("1");
        course.setName("Software Engineering");
        course.setDescription("Bachelor's degree");
        course.setCreatedAt("2025-07-25T10:00:00");
        Mockito.when(courseRepository.findById("1"))
                .thenReturn(Optional.of(course));

        CourseResponseDTO response = courseService.getCourseById("1");

        assertEquals("1", response.getCourseId());
        assertEquals("Software Engineering", response.getName());
        assertEquals("Bachelor's degree", response.getDescription());
        assertEquals("2025-07-25T10:00:00", response.getCreatedAt());

        Mockito.verify(courseRepository).findById("1");
    }


    @Test
    void getCourseById_notFound() {
        Mockito.when(courseRepository.findById("1"))
                .thenReturn(Optional.empty());

        try {

            courseService.getCourseById("1");
            fail();

        } catch (CourseNotFoundException e) {

            assertEquals("Course was not  found.", e.getMessage());

        }

        Mockito.verify(courseRepository).findById("1");
    }


    @Test
    void getAllCourses_success() {

        Course course1 = new Course();
        course1.setCourseId("1");
        course1.setName("Software Engineering");
        course1.setDescription("Bachelor's degree");
        course1.setCreatedAt("2025-07-25");

        Course course2 = new Course();
        course2.setCourseId("2");
        course2.setName("Mobile Development");
        course2.setDescription("Create apps for mobile phones and tablets.");
        course2.setCreatedAt("2025-07-26");

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        Mockito.when(courseRepository.findAll())
                .thenReturn(courses);

        List<CourseResponseDTO> response = courseService.getAllCourses();

        assertEquals(2, response.size());

        assertEquals("Software Engineering", response.get(0).getName());
        assertEquals("Mobile Development", response.get(1).getName());

        Mockito.verify(courseRepository).findAll();
    }

    @Test
    void getAllCourses_empty() {

        Mockito.when(courseRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<CourseResponseDTO> response = courseService.getAllCourses();

        assertTrue(response.isEmpty());

        Mockito.verify(courseRepository).findAll();
    }
}


