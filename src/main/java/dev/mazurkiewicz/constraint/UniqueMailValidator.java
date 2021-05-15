package dev.mazurkiewicz.constraint;

import dev.mazurkiewicz.user.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@ApplicationScoped
public class UniqueMailValidator implements ConstraintValidator<UniqueMail, String> {

    private final UserRepository userRepository;

    public UniqueMailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context) {
        return userRepository.findUserByEmail(mail.toLowerCase()).isEmpty();
    }
}