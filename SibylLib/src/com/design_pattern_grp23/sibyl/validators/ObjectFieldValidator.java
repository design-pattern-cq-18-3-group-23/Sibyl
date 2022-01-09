package com.design_pattern_grp23.sibyl.validators;

import com.design_pattern_grp23.sibyl.IValidationListener;
import com.design_pattern_grp23.sibyl.ValidationResult;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObjectFieldValidator<T> implements IValidator<T>{
    Map<String, CompositeValidator<Object>> fieldValidators;

    public ObjectFieldValidator() {
        fieldValidators = new HashMap<>();
        listeners = new HashSet<>();
    }

    @Override
    public ValidationResult validate(T value) {
        ValidationResult result = new ValidationResult();

        if (value == null) {
            return result;
        }

        for (Field field: value.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldValidators.containsKey(fieldName)) {
                CompositeValidator<Object> compositeValidator = fieldValidators.get(fieldName);
                try {
                    ValidationResult resultValidator = compositeValidator.validate(field.get(value));
                    if (resultValidator.hasError()) {
                        result.addErrors(resultValidator.getErrors());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        notifyListeners(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    public <FieldType> void add(String fieldName, IValidator<FieldType> validator) {
        if (fieldName == null || validator == null) {
            return;
        }
        CompositeValidator<FieldType> compositeValidator;
        if (fieldValidators.containsKey(fieldName)) {
            try {
                compositeValidator = (CompositeValidator<FieldType>) fieldValidators.get(fieldName);
            } catch (ClassCastException ignored) {
                // The validator passed in is not the same as the first one
                return;
            }

        } else {
            // The first time the field is added in
            compositeValidator = new CompositeValidator<>();
            fieldValidators.put(fieldName, (CompositeValidator<Object>) compositeValidator);
        }

        compositeValidator.add(validator);
    }

    @SuppressWarnings("unchecked")
    public <FieldType> void remove(String fieldName, IValidator<FieldType> validator) {
        if (fieldName == null || validator == null) {
            return;
        }
        CompositeValidator<FieldType> compositeValidator;
        if (fieldValidators.containsKey(fieldName)) {
            try {
                compositeValidator = (CompositeValidator<FieldType>) fieldValidators.get(fieldName);
            } catch (ClassCastException ignored) {
                // The validator passed in is not the same as the first one
                return;
            }
            compositeValidator.remove(validator);
        }
    }

    public void addListener(String fieldName, IValidationListener listener) {
        if (fieldName == null || listener == null) {
            return;
        }

        CompositeValidator<?> compositeValidator;
        if (fieldValidators.containsKey(fieldName)) {
            compositeValidator = fieldValidators.get(fieldName);
            compositeValidator.addListener(listener);
        }
    }

    public void removeListener(String fieldName, IValidationListener listener) {
        if (fieldName == null || listener == null) {
            return;
        }

        CompositeValidator<?> compositeValidator;
        if (fieldValidators.containsKey(fieldName)) {
            compositeValidator = fieldValidators.get(fieldName);
            compositeValidator.removeListener(listener);
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
}
