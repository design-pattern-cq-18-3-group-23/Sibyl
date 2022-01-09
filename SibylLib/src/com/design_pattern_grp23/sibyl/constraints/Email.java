package com.design_pattern_grp23.sibyl.constraints;

import com.design_pattern_grp23.sibyl.ValidationConstraint;
import com.design_pattern_grp23.sibyl.rules.EmailRule;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@ValidationConstraint(validatedBy = EmailRule.class)
public @interface Email {
    String message() default "Field have to be email";
}
