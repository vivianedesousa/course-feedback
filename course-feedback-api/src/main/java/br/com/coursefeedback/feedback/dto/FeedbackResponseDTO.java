package br.com.coursefeedback.feedback.dto;

import br.com.coursefeedback.feedback.domain.Urgency;

public class FeedbackResponseDTO {
    private String feedbackId;
    private String userId;
    private String courseId;
    private String description;
    private Integer rating;
    private Urgency urgency;
    private String createdAt;

    public FeedbackResponseDTO() {
    }

    public FeedbackResponseDTO(
            String feedbackId,
            String userId,
            String courseId,
            String description,
            Integer rating,
            Urgency urgency, String createdAt) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.courseId = courseId;
        this.description = description;
        this.rating = rating;
        this.urgency = urgency;
        this.createdAt = createdAt;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

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

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
