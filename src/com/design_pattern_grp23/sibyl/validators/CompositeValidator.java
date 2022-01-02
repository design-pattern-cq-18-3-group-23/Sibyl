package com.design_pattern_grp23.sibyl.validators;

import com.design_pattern_grp23.sibyl.IValidationListener;
import com.design_pattern_grp23.sibyl.ValidationResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompositeValidator<T> implements IValidator<T>{
    protected Set<IValidator<T>> validators;

    public CompositeValidator() {
        validators = new HashSet<>();
        listeners = new HashSet<>();
    }

    @Override
    public ValidationResult validate(T value) {
        ValidationResult result = new ValidationResult();
        List<IValidator<T>> invalidValidators = new ArrayList<>();

        for (IValidator<T> validator : validators) {
            try {
                ValidationResult resultValidator = validator.validate(value);
                if (resultValidator.hasError()) {
                    result.addErrors(resultValidator.getErrors());
                }
            } catch (ClassCastException e) {
                // Marks invalid to try to remove
                invalidValidators.add(validator);
                e.printStackTrace();
            }
        }

        if (!invalidValidators.isEmpty()) {
            remove(invalidValidators);
        }

        notifyListeners(result);
        return result;
    }

    public void add(IValidator<T> validator) {
        validators.add(validator);
    }

    public void remove(IValidator<T> validator) {
        if (validator != null) {
            validators.remove(validator);
        }
    }

    protected Set<IValidationListener> listeners;

    public void addListener(IValidationListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public void removeListener(IValidationListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

    protected void notifyListeners(ValidationResult result) {
        for (IValidationListener listener: listeners) {
            listener.afterValidation(result);
        }
    }

    private void remove(List<IValidator<T>> validatorList) {
        for (IValidator<T> validator: validatorList) {
            validators.remove(validator);
        }
    }
}
