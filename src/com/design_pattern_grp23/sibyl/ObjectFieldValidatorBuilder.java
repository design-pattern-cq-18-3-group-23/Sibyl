package com.design_pattern_grp23.sibyl;

import com.design_pattern_grp23.sibyl.rules.IValidationRule;
import com.design_pattern_grp23.sibyl.validators.IValidator;
import com.design_pattern_grp23.sibyl.validators.ObjectFieldValidator;
import com.design_pattern_grp23.sibyl.validators.RuleValidator;

public class ObjectFieldValidatorBuilder<T> {
    ObjectFieldValidator<T> validator;

    public ObjectFieldValidatorBuilder() {
    }

    public ObjectFieldValidatorBuilder(ObjectFieldValidator<T> validator) {
        this.validator = validator;
    }

    public <FieldType> ObjectFieldValidatorBuilder<T>
        addRule(String fieldName, IValidationRule<FieldType> rule, String errMessage) {
        IValidator<FieldType> ruleValidator = new RuleValidator<>(rule, errMessage);
        this.validator.add(fieldName, ruleValidator);
        return this;
    }

    public ObjectFieldValidatorBuilder<T>
        addListener(String fieldName, IValidationListener listener) {
        this.validator.addListener(fieldName, listener);
        return this;
    }

    public ObjectFieldValidatorBuilder<T>
        addListener(IValidationListener listener) {
        this.validator.addListener(listener);
        return this;
    }

    public ObjectFieldValidator<T> getValidator() {
        return validator;
    }
}
