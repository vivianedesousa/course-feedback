package br.com.coursefeedback.course.dto;

public class CourseResponseDTO {
    private String courseId;
    private String name;
    private String description;
    private String createdAt;

    public CourseResponseDTO(String courseId, String name, String description, String createdAt) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
