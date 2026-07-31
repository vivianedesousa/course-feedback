
package br.com.coursefeedback.report.processor;

import br.com.coursefeedback.report.service.WeeklyReportService;

public class WeeklyReportProcessor {

    private final WeeklyReportService weeklyReportService;

    public WeeklyReportProcessor(WeeklyReportService weeklyReportService) {
        this.weeklyReportService = weeklyReportService;
    }

    public void process() {
        weeklyReportService.generate();
    }
}

