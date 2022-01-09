package com.design_pattern_grp23.sibyl.rules;

import java.lang.annotation.Annotation;

public interface IConstraintInitializable<T extends Annotation> {
    void initialize(T constraint);
}
