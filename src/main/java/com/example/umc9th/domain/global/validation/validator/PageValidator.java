package com.example.umc9th.domain.global.validation.validator;

import com.example.umc9th.domain.global.validation.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {
    @Override
    public boolean isValid(
            Integer value,
            ConstraintValidatorContext context
    ) {
        return value != null && value > 0;
    }
}
