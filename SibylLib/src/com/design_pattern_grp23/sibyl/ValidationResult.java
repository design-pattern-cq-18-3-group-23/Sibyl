package com.design_pattern_grp23.sibyl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValidationResult implements Iterable<ValidationError> {
    List<ValidationError> errors;

    public ValidationResult() {
        errors = new ArrayList<>();
    }

    public void addError(ValidationError error) {
        if (error != null) {
            errors.add(error);
        }
    }

    public void addErrors(List<ValidationError> errors) {
        if (errors != null) {
            this.errors.addAll(errors);
        }
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public boolean hasError() {
        return !errors.isEmpty();
    }

    @Override
    public Iterator<ValidationError> iterator() {
        return errors.iterator();
    }
}
