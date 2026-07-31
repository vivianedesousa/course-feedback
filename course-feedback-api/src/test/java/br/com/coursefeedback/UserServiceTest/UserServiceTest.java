package br.com.coursefeedback.UserServiceTest;

import br.com.coursefeedback.auth.domain.User;
import br.com.coursefeedback.auth.domain.UserRole;
import br.com.coursefeedback.auth.dto.LoginRequestDTO;
import br.com.coursefeedback.auth.repository.UserRepository;
import br.com.coursefeedback.auth.service.UserService;
import br.com.coursefeedback.shared.exception.EmailAlreadyExistsException;
import br.com.coursefeedback.shared.exception.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setup() {

        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserService(userRepository);
    }

    @Test
    void shouldRegisterUser() {
        User user = new User();
        user.setUserId("user-1");
        user.setName("João");
        user.setEmail("joao@email.com");
        user.setPassword("123456");
        user.setRole(UserRole.STUDENT);

        Mockito.when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.empty());

        userService.register(user);

        Mockito.verify(userRepository)
                .findByEmail(user.getEmail());

        Mockito.verify(userRepository)
                .save(user);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        User user = new User();
        user.setEmail("joao@email.com");

        Mockito.when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        try {
            userService.register(user);
            fail("Expected EmailAlreadyExistsException");
        } catch (EmailAlreadyExistsException e) {
            assertNotNull(e);
        }

        Mockito.verify(userRepository)
                .findByEmail(user.getEmail());

        Mockito.verify(userRepository, Mockito.never())
                .save(Mockito.any(User.class));
    }


    @Test
    void shouldLoginSuccessfully() {

        User user = new User();
        user.setUserId("user-1");
        user.setName("João");
        user.setEmail("joao@email.com");
        user.setPassword("123456789");
        user.setRole(UserRole.STUDENT);

        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail("joao@email.com");
        request.setPassword("123456789");

        Mockito.when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        User response = userService.login(request);

        assertNotNull(response);
        assertEquals("user-1", response.getUserId());
        assertEquals("João", response.getName());
        assertEquals("joao@email.com", response.getEmail());
        assertEquals(UserRole.STUDENT, response.getRole());

        Mockito.verify(userRepository)
                .findByEmail(request.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {

        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail("joao@email.com");
        request.setPassword("123456789");

        Mockito.when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        try {

            userService.login(request);
            fail("InvalidCredentialsException should have been thrown.");

        } catch (InvalidCredentialsException e) {

            assertNotNull(e);
        }

        Mockito.verify(userRepository)
                .findByEmail(request.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {

        User user = new User();
        user.setEmail("joao@email.com");
        user.setPassword("123456789");

        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail("joao@email.com");
        request.setPassword("987654321");

        Mockito.when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        try {

            userService.login(request);
            fail("InvalidCredentialsException should have been thrown.");

        } catch (InvalidCredentialsException e) {

            assertNotNull(e);
        }

        Mockito.verify(userRepository)
                .findByEmail(request.getEmail());
    }
}
