package com.jsk.anno;

import com.jsk.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})//原註解
@Retention(RetentionPolicy.RUNTIME)//原註解
@Documented//原註解
@Constraint(validatedBy = {StateValidation.class})//指定校驗的規則

public @interface State {

    String message() default "State參數的值,只能是已發佈或草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
