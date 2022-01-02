package com.design_pattern_grp23.sibyl;

import com.design_pattern_grp23.sibyl.rules.IConstraintInitializable;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Target({ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidationConstraint {
    Class<? extends IConstraintInitializable<?>> validatedBy();
}
