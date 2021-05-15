package dev.mazurkiewicz.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotBlank
@Email
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueMailValidator.class)
public @interface UniqueMail {
    String message() default "{dev.mazurkiewicz.user.validation.UniqueMail.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
