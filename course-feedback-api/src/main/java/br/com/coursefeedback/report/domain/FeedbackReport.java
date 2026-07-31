package br.com.coursefeedback.report.domain;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class FeedbackReport {
    private String reportId;

    private String periodStart;

    private String periodEnd;

    private Integer totalFeedbacks;

    private Integer lowFeedbacks;

    private Integer mediumFeedbacks;

    private Integer highFeedbacks;

    private Integer criticalFeedbacks;

    private Double averageRating;

    private String generatedAt;

    private String generatedBy;

    private String s3Key;

    @DynamoDbPartitionKey
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

    public Integer getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(Integer totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }

    public Integer getLowFeedbacks() {
        return lowFeedbacks;
    }

    public void setLowFeedbacks(Integer lowFeedbacks) {
        this.lowFeedbacks = lowFeedbacks;
    }

    public Integer getMediumFeedbacks() {
        return mediumFeedbacks;
    }

    public void setMediumFeedbacks(Integer mediumFeedbacks) {
        this.mediumFeedbacks = mediumFeedbacks;
    }

    public Integer getHighFeedbacks() {
        return highFeedbacks;
    }

    public void setHighFeedbacks(Integer highFeedbacks) {
        this.highFeedbacks = highFeedbacks;
    }

    public Integer getCriticalFeedbacks() {
        return criticalFeedbacks;
    }

    public void setCriticalFeedbacks(Integer criticalFeedbacks) {
        this.criticalFeedbacks = criticalFeedbacks;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
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
}
