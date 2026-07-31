package br.com.coursefeedback.auth.security;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

import br.com.coursefeedback.auth.domain.UserRole;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    @Inject
    JwtService jwtService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();

        if (path.startsWith("/api/v1/auth/login")
                || path.startsWith("/api/v1/auth/register")) {
            return;
        }

        String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Missing or invalid Authorization header.")
                            .build()
            );
            return;
        }

        String token = authorization.substring(7);

        if (!jwtService.validateToken(token)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid token.")
                            .build()
            );
            return;
        }


        UserRole role = jwtService.extractRole(token);

        if (role == UserRole.ADMIN) {
            return;
        }

        if (role == UserRole.STUDENT
                && path.equals("/api/v1/feedbacks")
                && method.equals("POST")) {
            return;
        }

        requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN)
                        .entity("Access denied.")
                        .build()
        );
    }
}
