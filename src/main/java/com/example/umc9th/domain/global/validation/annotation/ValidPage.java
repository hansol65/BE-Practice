package com.example.umc9th.domain.global.validation.annotation;

import com.example.umc9th.domain.global.validation.validator.PageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target({
        ElementType.PARAMETER,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {

    String message() default "페이지는 1 이상이어야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}