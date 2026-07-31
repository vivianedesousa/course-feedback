package br.com.coursefeedback.feedback.repository;

import br.com.coursefeedback.feedback.domain.Feedback;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FeedbackRepository {
    private final DynamoDbTable<Feedback> feedbackTable;

    public FeedbackRepository(DynamoDbEnhancedClient enhancedClient) {

        this.feedbackTable = enhancedClient.table(
                "feedbacks",
                TableSchema.fromBean(Feedback.class)
        );
    }

    public void save(Feedback feedback) {
        feedbackTable.putItem(feedback);
    }

    public List<Feedback> findAll() {

        return feedbackTable
                .scan()
                .items()
                .stream()
                .toList();
    }

    public Optional<Feedback> findById(String feedbackId) {

        Key key = Key.builder()
                .partitionValue(feedbackId)
                .build();
        return Optional.ofNullable(feedbackTable.getItem(key));
    }
}
