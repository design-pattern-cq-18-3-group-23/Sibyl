package com.design_pattern_grp23.sibyl.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractRegExRule implements IValidationRule<String> {
    @Override
    public boolean isValid(String value) {
        Pattern pattern = getPattern();
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    protected abstract Pattern getPattern();
}
