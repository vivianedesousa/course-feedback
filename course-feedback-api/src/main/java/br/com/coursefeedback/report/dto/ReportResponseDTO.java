package br.com.coursefeedback.report.dto;
import java.util.List;
public class ReportResponseDTO {
        private String reportId;
        private String periodStart;
        private String periodEnd;
        private int totalFeedbacks;
        private int lowFeedbacks;
        private int mediumFeedbacks;
        private int highFeedbacks;
        private int criticalFeedbacks;
        private double averageRating;
        private String generatedAt;
        private String generatedBy;
        private String s3Key;
        private List<DailyFeedbackSummaryResponseDTO> dailySummary;
        private List<FeedbackSummaryResponseDTO> feedbacks;;
        public ReportResponseDTO() {
        }

        public ReportResponseDTO(
                String reportId,
                String periodStart,
                String periodEnd,
                int totalFeedbacks,
                int lowFeedbacks,
                int mediumFeedbacks,
                int highFeedbacks,
                int criticalFeedbacks,
                double averageRating,
                String generatedAt,
                String generatedBy,
                String s3Key,
                List<DailyFeedbackSummaryResponseDTO> dailySummary,
                List<FeedbackSummaryResponseDTO> feedbacks) {

            this.reportId = reportId;
            this.periodStart = periodStart;
            this.periodEnd = periodEnd;
            this.totalFeedbacks = totalFeedbacks;
            this.lowFeedbacks = lowFeedbacks;
            this.mediumFeedbacks = mediumFeedbacks;
            this.highFeedbacks = highFeedbacks;
            this.criticalFeedbacks = criticalFeedbacks;
            this.averageRating = averageRating;
            this.generatedAt = generatedAt;
            this.generatedBy = generatedBy;
            this.s3Key = s3Key;
            this.feedbacks = feedbacks;
            this.dailySummary = dailySummary;
        }

        public String getReportId() {
            return reportId;
        }

        public void setReportId(String reportId) {
            this.reportId = reportId;
        }

        public String getPeriodStart() {
            return periodStart;
        }

        public void setPeriodStart(String periodStart) {
            this.periodStart = periodStart;
        }

        public String getPeriodEnd() {
            return periodEnd;
        }

        public void setPeriodEnd(String periodEnd) {
            this.periodEnd = periodEnd;
        }

        public int getTotalFeedbacks() {
            return totalFeedbacks;
        }

        public void setTotalFeedbacks(int totalFeedbacks) {
            this.totalFeedbacks = totalFeedbacks;
        }

        public int getLowFeedbacks() {
            return lowFeedbacks;
        }

        public void setLowFeedbacks(int lowFeedbacks) {
            this.lowFeedbacks = lowFeedbacks;
        }

        public int getMediumFeedbacks() {
            return mediumFeedbacks;
        }

        public void setMediumFeedbacks(int mediumFeedbacks) {
            this.mediumFeedbacks = mediumFeedbacks;
        }

        public int getHighFeedbacks() {
            return highFeedbacks;
        }

        public void setHighFeedbacks(int highFeedbacks) {
            this.highFeedbacks = highFeedbacks;
        }

        public int getCriticalFeedbacks() {
            return criticalFeedbacks;
        }

        public void setCriticalFeedbacks(int criticalFeedbacks) {
            this.criticalFeedbacks = criticalFeedbacks;
        }

        public double getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(double averageRating) {
            this.averageRating = averageRating;
        }

        public String getGeneratedAt() {
            return generatedAt;
        }

        public void setGeneratedAt(String generatedAt) {
            this.generatedAt = generatedAt;
        }

        public String getGeneratedBy() {
            return generatedBy;
        }

        public void setGeneratedBy(String generatedBy) {
            this.generatedBy = generatedBy;
        }

        public String getS3Key() {
            return s3Key;
        }

        public void setS3Key(String s3Key) {
            this.s3Key = s3Key;
        }

        public List<DailyFeedbackSummaryResponseDTO> getDailySummary() {
            return dailySummary;
        }

        public void setDailySummary(List<DailyFeedbackSummaryResponseDTO> dailySummary) {
            this.dailySummary = dailySummary;
        }

        public List<FeedbackSummaryResponseDTO>  getFeedbacks() {
            return feedbacks;
        }

        public void setFeedbacks(List<FeedbackSummaryResponseDTO> feedbacks) {
            this.feedbacks = feedbacks;
        }
    }



