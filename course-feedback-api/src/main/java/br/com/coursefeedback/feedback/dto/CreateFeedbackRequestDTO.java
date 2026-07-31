package br.com.coursefeedback.feedback.dto;

import jakarta.validation.constraints.*;

public class CreateFeedbackRequestDTO {
    @Size(max = 100, message = "The user id must have a maximum of 100 characters.")
    @NotBlank(message = "The user id is required.") // temporario remove para autenticcao cognito
    private String userId;

    @NotBlank(message = "The course id is required.")
    @Size(max = 100, message = "The course id must have a maximum of 100 characters.")
    private String courseId;
    @NotBlank(message = "The description is required.")
    @Size(max = 500, message = "The description must have a maximum of 500 characters.")
    private String description;

    @NotNull(message = "The rating is required.")
    @Min(value = 0, message = "The rating must be between 0 and 10.")
    @Max(value = 10, message = "The rating must be between 0 and 10.")
    private Integer rating;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
