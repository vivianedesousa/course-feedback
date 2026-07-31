package br.com.coursefeedback.notification.sns;

import br.com.coursefeedback.notification.dto.NotificationMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@ApplicationScoped
public class SnsPublisher {

    private final SnsClient snsClient;
    private final ObjectMapper objectMapper;

    @ConfigProperty(name = "aws.sns.topic-arn")
    String topicArn;


    public SnsPublisher(
            SnsClient snsClient,
            ObjectMapper objectMapper) {

        this.snsClient = snsClient;
        this.objectMapper = objectMapper;
    }

    public void publish(NotificationMessageDTO message) {

        try {

            String json = objectMapper.writeValueAsString(message);

            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(json)
                    .build();

            snsClient.publish(request);

        } catch (JsonProcessingException e) {

            throw new RuntimeException(
                    "Error publishing notification to Amazon SNS.",
                    e
            );
        }
    }
}