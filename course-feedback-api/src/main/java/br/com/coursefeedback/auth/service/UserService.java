package br.com.coursefeedback.auth.service;

import br.com.coursefeedback.auth.domain.User;
import br.com.coursefeedback.shared.exception.EmailAlreadyExistsException;
import br.com.coursefeedback.shared.exception.InvalidCredentialsException;
import br.com.coursefeedback.auth.dto.LoginRequestDTO;
import br.com.coursefeedback.auth.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        userRepository.save(user);
    }

    public User login(LoginRequestDTO request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        if (!user.get().getPassword().equals(request.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return user.get();
    }

}
