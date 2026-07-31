package br.com.coursefeedback.auth.resource;

import br.com.coursefeedback.auth.domain.User;
import br.com.coursefeedback.auth.security.JwtService;
import br.com.coursefeedback.auth.domain.UserRole;
import br.com.coursefeedback.auth.dto.*;
import br.com.coursefeedback.auth.service.UserService;
import br.com.coursefeedback.auth.dto.RegisterRequestDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.ws.rs.Consumes;
import jakarta.validation.Valid;

@Path("/api/v1/auth/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(
        name = "Authentication",
        description = "Endpoints responsible for user registration and authentication using JWT."
)
public class AuthResource {

    private final UserService userService;
    private final JwtService jwtService;

    @Inject
    public AuthResource(UserService userService,
                        JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @POST
    @Path("/register")
    @Operation(
            summary = "Register a new student",
            description = "Creates a new user account with the STUDENT role and stores it in the database."
    )
    public Response register(@Valid RegisterRequestDTO request) {

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        //  user.setRole(request.getRole());
        user.setRole(UserRole.STUDENT);
        user.setCreatedAt(LocalDateTime.now().withNano(0).toString());
        userService.register(user);
        // somente para test  quando para  aws muda para token
        RegisterResponseDTO response = new RegisterResponseDTO(
                user.getUserId(),
                "User successfully registered."
        );
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();

    }

    @POST
    @Path("/login")
    @Operation(
            summary = "Authenticate user",
            description = "Validates the user's credentials and returns a JWT token for accessing protected endpoints."
    )
    public Response login(@Valid LoginRequestDTO request) {
        User user = userService.login(request);
        String token = jwtService.generateToken(user);
        return Response.ok(new LoginResponseDTO(token)).build();
    }
}

