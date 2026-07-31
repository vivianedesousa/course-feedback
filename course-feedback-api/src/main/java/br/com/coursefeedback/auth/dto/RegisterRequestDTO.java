package br.com.coursefeedback.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email.")
    private String email;
    // seguindo as recomendações atuais (como as do NIST SP 800-63B), o padrão mais aceito é:
    @NotBlank(message = "The password is required.")
    @Size(
            min = 8,
            max = 64,
            message = "The password must contain between 8 and 64 characters."
    )

    private String password;

    public RegisterRequestDTO() {
    }

    public RegisterRequestDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}