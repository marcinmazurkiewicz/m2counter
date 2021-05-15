package dev.mazurkiewicz.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.mazurkiewicz.constraint.PasswordMatch;
import dev.mazurkiewicz.constraint.UniqueMail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatch
public class RegisterRequest {
    @UniqueMail
    private final String email;
    @NotBlank
    @Size(min = 8, max = 32)
    private final String password;
    private final String confirmPassword;

    @JsonCreator
    public RegisterRequest(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}