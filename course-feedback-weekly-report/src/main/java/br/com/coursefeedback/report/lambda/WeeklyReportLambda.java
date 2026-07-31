package br.com.coursefeedback.report.lambda;
import br.com.coursefeedback.report.processor.WeeklyReportProcessor;
import br.com.coursefeedback.report.service.WeeklyReportService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
public class WeeklyReportLambda implements RequestHandler<Object, Void> {
    private final WeeklyReportProcessor processor =
            new WeeklyReportProcessor(
                    new WeeklyReportService()
            );
    @Override
    public Void handleRequest(Object event, Context context) {
        processor.process();
        return null;
    }
}