package com.design_pattern_grp23.sibyl.rules;

import com.design_pattern_grp23.sibyl.constraints.RegEx;

import java.util.regex.Pattern;

public class RegExRule extends AbstractRegExRule implements IConstraintInitializable<RegEx>{
    Pattern pattern;

    public RegExRule() {
        pattern = Pattern.compile("");
    }

    @Override
    public void initialize(RegEx constraint) {
        pattern = Pattern.compile(constraint.regexp());
    }

    @Override
    protected Pattern getPattern() {
        return pattern;
    }
}
