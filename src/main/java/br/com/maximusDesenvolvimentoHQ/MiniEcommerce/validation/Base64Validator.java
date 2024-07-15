package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Base64;

public class Base64Validator implements ConstraintValidator<ValidBase64,String> {

    @Override
    public void initialize(ValidBase64 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()){
            return true;
        }
        try {
            Base64.getDecoder().decode(value);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}
