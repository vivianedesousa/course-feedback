package br.com.coursefeedback.report.repository;

import br.com.coursefeedback.report.domain.FeedbackReport;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FeedbackReportRepository {
    private final DynamoDbTable<FeedbackReport> reportTable;

    public FeedbackReportRepository(DynamoDbEnhancedClient enhancedClient) {
        this.reportTable = enhancedClient.table(
                "reports",
                TableSchema.fromBean(FeedbackReport.class)
        );
    }

    public void save(FeedbackReport report) {

        reportTable.putItem(report);
    }

    public List<FeedbackReport> findAll() {

        return reportTable
                .scan()
                .items()
                .stream()
                .toList();
    }

    public Optional<FeedbackReport> findById(String reportId) {
        Key key = Key.builder()
                .partitionValue(reportId)
                .build();
        return Optional.ofNullable(reportTable.getItem(key));
    }

}
