package br.com.coursefeedback.report.service;
import br.com.coursefeedback.report.dto.WeeklyReportDTO;
public class WeeklyReportService {
    public void generate() {
        WeeklyReportDTO report = new WeeklyReportDTO();
        report.setReportDate("2026-07-27");
        report.setTotalFeedbacks(150);
        report.setCriticalFeedbacks(12);
        System.out.println("=== WEEKLY REPORT ===");
        System.out.println("Report Date: " + report.getReportDate());
        System.out.println("Total Feedbacks: " + report.getTotalFeedbacks());
        System.out.println("Critical Feedbacks: " + report.getCriticalFeedbacks());

    }   }



