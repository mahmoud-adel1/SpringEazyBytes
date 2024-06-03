package com.eazybytes.eazyschool.validation;

import com.eazybytes.eazyschool.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;
    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = Arrays.asList("12345","password","qwerty");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (!weakPasswords.contains(value));
    }
}
