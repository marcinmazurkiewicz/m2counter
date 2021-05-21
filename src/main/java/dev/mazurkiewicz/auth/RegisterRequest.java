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
    @NotBlank
    private final String confirmPassword;
    @NotBlank
    private final String nick;

    @JsonCreator
    public RegisterRequest(String email, String password, String confirmPassword, String nick) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.nick = nick;
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

    public String getNick() {
        return nick;
    }
}
