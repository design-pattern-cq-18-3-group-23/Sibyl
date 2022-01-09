package com.design_pattern_grp23.sibyl.rules;

public interface IValidationRule<T> {
    boolean isValid(T value);
}
