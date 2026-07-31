package br.com.coursefeedback.shared.exception;

/// crair  Handlermapper
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid email or password.");
    }
}
