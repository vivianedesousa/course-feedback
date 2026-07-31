package br.com.coursefeedback.course.repository;

import br.com.coursefeedback.course.domain.Course;
import jakarta.enterprise.context.ApplicationScoped; // criar uma instancia dessa classe durante toda apliccao
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CourseRepository {
    private final DynamoDbTable<Course> courseTable;

    public CourseRepository(DynamoDbEnhancedClient enhancedClient) {
        this.courseTable = enhancedClient.table(
                "courses",
                TableSchema.fromBean(Course.class)
        );
    }

    public void save(Course course) {
        courseTable.putItem(course);
    }


    public Optional<Course> findById(String courseId) {
        Key key = Key.builder()
                .partitionValue(courseId)
                .build();
        return Optional.ofNullable(courseTable.getItem(key));
    }


    public List<Course> findAll() {
        return courseTable
                .scan()
                .items()
                .stream()
                .toList();
    }
}

