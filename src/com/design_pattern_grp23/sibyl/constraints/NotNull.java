package com.design_pattern_grp23.sibyl.constraints;

import com.design_pattern_grp23.sibyl.ValidationConstraint;
import com.design_pattern_grp23.sibyl.rules.NotNullRule;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@ValidationConstraint(validatedBy = NotNullRule.class)
public @interface NotNull {
    public String message() default "Field must not be null";
}
