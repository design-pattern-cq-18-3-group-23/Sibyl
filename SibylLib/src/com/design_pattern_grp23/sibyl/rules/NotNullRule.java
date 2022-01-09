package com.design_pattern_grp23.sibyl.rules;

import com.design_pattern_grp23.sibyl.constraints.NotNull;

import java.util.Objects;

public class NotNullRule implements IValidationRule<Object>, IConstraintInitializable<NotNull> {

    @Override
    public void initialize(NotNull constraint) {
    }

    @Override
    public boolean isValid(Object value) {
        return Objects.nonNull(value);
    }
}
