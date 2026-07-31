package br.com.coursefeedback.report.dto;

public class DailyFeedbackSummaryResponseDTO {
    private String date;
    private int totalFeedbacks;

    public DailyFeedbackSummaryResponseDTO() {
    }

    public DailyFeedbackSummaryResponseDTO(
            String date,
            int totalFeedbacks) {

        this.date = date;
        this.totalFeedbacks = totalFeedbacks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(int totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }
}

