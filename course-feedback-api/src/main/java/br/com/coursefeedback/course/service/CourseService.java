package br.com.coursefeedback.course.service;

import br.com.coursefeedback.course.domain.Course;
import br.com.coursefeedback.course.dto.CourseResponseDTO;
import br.com.coursefeedback.course.dto.CreateCourseRequestDTO;
import br.com.coursefeedback.course.repository.CourseRepository;
import br.com.coursefeedback.shared.exception.CourseNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponseDTO createCourse(CreateCourseRequestDTO CreateCourseRequestDTO) {

        Course course = new Course();
        course.setCourseId(UUID.randomUUID().toString());

        course.setName(CreateCourseRequestDTO.getName());
        course.setDescription(CreateCourseRequestDTO.getDescription());

        course.setCreatedAt(LocalDateTime.now().withNano(0).toString());
        courseRepository.save(course);
        // Monta o objeto de resposta da API.
        return new CourseResponseDTO(
                course.getCourseId(),
                course.getName(),
                course.getDescription(),
                course.getCreatedAt()
        );
    }

    public CourseResponseDTO getCourseById(String courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new CourseNotFoundException("Course was not  found.");
        }

        Course course = courseOptional.get();
        return new CourseResponseDTO(
                course.getCourseId(),
                course.getName(),
                course.getDescription(),
                course.getCreatedAt()
        );
    }

    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponseDTO> responseDTOs = new ArrayList<>();
        for (Course course : courses) {
            CourseResponseDTO responseDTO = new CourseResponseDTO(
                    course.getCourseId(),
                    course.getName(),
                    course.getCreatedAt(),
                    course.getDescription()
            );

            responseDTOs.add(responseDTO);
        }
        return responseDTOs;
    }
}

