package com.elearning.server.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

   
    public boolean isValid(final String password) {
        return (validatePassword(password));
    }

    private boolean validatePassword(final String password) {
        if (password.length() < 8 || password.length() > 32) {
            return false;
        }
        else {
            return true;
        }
    }
}