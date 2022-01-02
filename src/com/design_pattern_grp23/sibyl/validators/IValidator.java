package com.design_pattern_grp23.sibyl.validators;

import com.design_pattern_grp23.sibyl.ValidationResult;

public interface IValidator<T> {
    ValidationResult validate(T value);
}
