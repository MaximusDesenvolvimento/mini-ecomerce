package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = Base64Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBase64 {
    String message() default "String da base64 inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
