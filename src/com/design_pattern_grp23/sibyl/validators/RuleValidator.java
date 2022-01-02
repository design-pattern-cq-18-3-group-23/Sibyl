package com.design_pattern_grp23.sibyl.validators;

import com.design_pattern_grp23.sibyl.ValidationError;
import com.design_pattern_grp23.sibyl.ValidationResult;
import com.design_pattern_grp23.sibyl.rules.IValidationRule;

public class RuleValidator<T> implements IValidator<T> {
    protected IValidationRule<T> rule;
    protected String message;

    public RuleValidator(IValidationRule<T> rule, String message) {
        this.rule = rule;
        this.message = message;
    }

    @Override
    public ValidationResult validate(T value) {
        ValidationResult result = new ValidationResult();

        if (!rule.isValid(value)) {
            result.addError(new ValidationError(message));
        }

        return result;
    }
}
