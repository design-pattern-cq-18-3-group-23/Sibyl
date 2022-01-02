package com.design_pattern_grp23.sibyl.rules;

import com.design_pattern_grp23.sibyl.constraints.Min;

public class MinRule implements IValidationRule<Double>, IConstraintInitializable<Min>{
    Double minValue;
    boolean inclusive;
    @Override
    public void initialize(Min constraint) {
        minValue = constraint.value();
        inclusive = constraint.inclusive();
    }

    @Override
    public boolean isValid(Double value) {
        if (inclusive) {
            return value >= minValue;
        }
        return value > minValue;
    }
}
