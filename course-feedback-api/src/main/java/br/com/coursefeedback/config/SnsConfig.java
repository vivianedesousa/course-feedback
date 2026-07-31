package br.com.coursefeedback.config;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
    @ApplicationScoped
    public class SnsConfig {
        @ConfigProperty(name = "aws.region")
        String region;
        @Produces
        public SnsClient snsClient() {
            return SnsClient.builder()
                    .region(Region.of(region))
                    .build();
        }
 }


