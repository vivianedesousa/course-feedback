package br.com.coursefeedback.report.dto;

public class WeeklyReportDTO {
    private String reportDate;
    private Integer totalFeedbacks;
    private Integer criticalFeedbacks;
    public WeeklyReportDTO() {
    }
    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    public Integer getTotalFeedbacks() {
        return totalFeedbacks;
    }
    public void setTotalFeedbacks(Integer totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }
    public Integer getCriticalFeedbacks() {
        return criticalFeedbacks;
    }
    public void setCriticalFeedbacks(Integer criticalFeedbacks) {
        this.criticalFeedbacks = criticalFeedbacks;
    }
}