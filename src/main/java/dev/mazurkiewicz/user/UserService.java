package dev.mazurkiewicz.user;

import dev.mazurkiewicz.exception.ResourceNotFoundException;
import io.quarkus.security.UnauthorizedException;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

import javax.enterprise.context.ApplicationScoped;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@ApplicationScoped
public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;

    public UserService(UserMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        User user = mapper.mapRequestToEntity(userRequest);
        repository.saveUser(user);
        return mapper.mapEntityToResponse(user);
    }

    public UserResponse login(UserRequest userRequest) {
        User user = repository.findUser(userRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", userRequest.getEmail())));
        if (verifyPassword(userRequest.getPassword(), user.getPassword())) {
            return mapper.mapEntityToResponse(user);
        } else {
            throw new UnauthorizedException();
        }
    }

    private boolean verifyPassword(String originalPwd, String encryptedPwd) {
        boolean result = false;
        try {
            Password rawPassword = ModularCrypt.decode(encryptedPwd);
            PasswordFactory factory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT);
            BCryptPassword restored = (BCryptPassword) factory.translate(rawPassword);
            result = factory.verify(restored, originalPwd.toCharArray());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }
}
