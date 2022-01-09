package com.design_pattern_grp23.sibyl.rules;

import com.design_pattern_grp23.sibyl.constraints.Length;

public class LengthRule implements IValidationRule<String>, IConstraintInitializable<Length> {
    int min;
    int max;

    public LengthRule() {
        min = -1;
        max = -1;
    }

    public LengthRule(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public void initialize(Length constraint) {
        min = constraint.min();
        max = constraint.max();
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return false;
        }
        if (min != -1) {
            if (value.length() < min) {
                return false;
            }
        }
        if (max != -1) {
            if (value.length() > max) {
                return false;
            }
        }
        return true;
    }
}
