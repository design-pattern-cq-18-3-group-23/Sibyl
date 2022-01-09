package com.design_pattern_grp23.sibyl.rules;

import com.design_pattern_grp23.sibyl.constraints.NotEmpty;

public class NotEmptyRule implements IConstraintInitializable<NotEmpty>, IValidationRule<String> {
    @Override
    public void initialize(NotEmpty constraint) {
    }

    @Override
    public boolean isValid(String value) {
        return value != null && value.length() != 0;
    }
}
