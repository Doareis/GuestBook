package com.example.guestbook.validator;

import com.example.guestbook.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<ValidPassword, Object> {

    @Override
    public void initialize(ValidPassword validPassword) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO user = (UserDTO) o;
        return user.getPassword().equals(user.getConfirmationPassword());
    }
}
