package br.com.coursefeedback.notification.lambda;
import br.com.coursefeedback.notification.dto.NotificationMessageDTO;
import br.com.coursefeedback.notification.processor.CriticalFeedbackNotificationProcessor;
import br.com.coursefeedback.notification.service.EmailService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Named;
@Named("critical-feedback")
public class CriticalFeedbackNotificationLambda
        implements RequestHandler<SNSEvent, Void> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final CriticalFeedbackNotificationProcessor processor =
            new CriticalFeedbackNotificationProcessor(
                    new EmailService()
            );

    @Override
    public Void handleRequest(SNSEvent event, Context context) {

        for (SNSEvent.SNSRecord record : event.getRecords()) {

            try {

                String message = record.getSNS().getMessage();

                NotificationMessageDTO notification =
                        objectMapper.readValue(
                                message,
                                NotificationMessageDTO.class
                        );

                processor.process(notification);

            } catch (Exception e) {
                throw new RuntimeException(
                        "Error processing SNS notification.",
                        e
                );
            }
        }

        return null;
    }
}
