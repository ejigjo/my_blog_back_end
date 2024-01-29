package com.jsk.validation;

import com.jsk.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    //false代表校驗不通過
    @Override
    public boolean isValid(String valid, ConstraintValidatorContext constraintValidatorContext) {
        if (valid == null){
            return false;
        }
        if (valid.equals("已發佈")||valid.equals("草稿")) {
            return true;
        }

        return false;
    }
}
