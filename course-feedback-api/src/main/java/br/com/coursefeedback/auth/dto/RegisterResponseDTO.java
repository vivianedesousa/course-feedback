package br.com.coursefeedback.auth.dto;

public class RegisterResponseDTO {
    private String userId;
    private String message;
    public RegisterResponseDTO() {
    }

    public RegisterResponseDTO(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}