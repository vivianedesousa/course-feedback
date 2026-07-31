package br.com.coursefeedback.shared.exception;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException(String messsage) {
        super(messsage);
    }
}
