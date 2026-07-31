package br.com.coursefeedback.report.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class GenerateReportRequestDTO {
    @NotBlank(message = "The start date is required.")
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "The start date must be in the format yyyy-MM-dd."
    )
    private String periodStart;

    @NotBlank(message = "The end date is required.")
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "The end date must be in the format yyyy-MM-dd."
    )
    private String periodEnd;

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
}
