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
import java.util.Set;

@ApplicationScoped
public class UserService {
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserMapper mapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        User user = mapper.mapRequestToEntity(userRequest);
        Role userRole = roleRepository.findRole(RoleKind.USER.name())
                .orElseGet(() -> roleRepository.createNewRole(RoleKind.USER.name()));
        user.setRoles(Set.of(userRole));
        userRepository.saveUser(user);
        return mapper.mapEntityToResponse(user);
    }

    public UserResponse login(UserRequest userRequest) {
        User user = userRepository.findUser(userRequest.getEmail())
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
