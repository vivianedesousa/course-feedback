package br.com.coursefeedback.auth.repository;
import br.com.coursefeedback.auth.domain.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import java.util.Optional;
@ApplicationScoped
public class UserRepository {
    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbTable<User> userTable;
    @Inject
    public UserRepository(DynamoDbEnhancedClient enhancedClient) {
        this.enhancedClient = enhancedClient;
        this.userTable = enhancedClient.table(
                "users",
                TableSchema.fromBean(User.class)
        );
    }
    public void save(User user) {
        userTable.putItem(user);
    }

    public Optional<User> findById(String userId) {
        Key key = Key.builder()
                .partitionValue(userId)
                .build();
        return Optional.ofNullable(userTable.getItem(key));
    }

    public Optional<User> findByEmail(String email) {
        DynamoDbIndex<User> emailIndex = userTable.index("email-index");
        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder()
                        .partitionValue(email)
                        .build()
        );

        for (Page<User> page : emailIndex.query(queryConditional)) {
            for (User user : page.items()) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}


