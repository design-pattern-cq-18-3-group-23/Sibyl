package com.design_pattern_grp23.sibyl.rules;

import com.design_pattern_grp23.sibyl.constraints.Max;

public class MaxRule implements IValidationRule<Double>, IConstraintInitializable<Max> {
    Double maxValue;
    boolean inclusive;

    @Override
    public void initialize(Max constraint) {
        maxValue = constraint.value();
        inclusive = constraint.inclusive();
    }

    @Override
    public boolean isValid(Double value) {
        if (inclusive) {
            return value <= maxValue;
        }
        return value < maxValue;
    }
}
