package br.com.coursefeedback.auth.config;

import br.com.coursefeedback.auth.domain.User;
import br.com.coursefeedback.auth.domain.UserRole;
import br.com.coursefeedback.auth.repository.UserRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class AdminInitializer {
    @Inject
    UserRepository userRepository;

    void onStart(@Observes StartupEvent event) {
        if (userRepository.findByEmail("admin@coursefeedback.com").isPresent()) {
            return;
        }
        User admin = new User();
        admin.setUserId(UUID.randomUUID().toString());
        admin.setUserId("admin001");
        admin.setName("Viviane Lima");
        admin.setEmail("admin@coursefeedback.com");
        admin.setPassword("123456789");
        admin.setRole(UserRole.ADMIN);
        admin.setCreatedAt(LocalDateTime.now().withNano(0).toString());

        userRepository.save(admin);

        System.out.println("Default administrator created");
    }
}
