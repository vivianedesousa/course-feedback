package br.com.coursefeedback.report.dto;

import br.com.coursefeedback.feedback.domain.Urgency;

public class FeedbackSummaryResponseDTO {
    private String courseName;// novo
    private String description;
    private Urgency urgency;
    private String submittedAt;

    public FeedbackSummaryResponseDTO() {
    }

    public FeedbackSummaryResponseDTO(
            String courseName,
            String description,
            Urgency urgency,
            String submittedAt) {
        this.courseName = courseName;
        this.description = description;
        this.urgency = urgency;
        this.submittedAt = submittedAt;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }
}
