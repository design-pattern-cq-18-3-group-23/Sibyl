package demo.customs;

import com.design_pattern_grp23.sibyl.rules.IConstraintInitializable;
import com.design_pattern_grp23.sibyl.rules.IValidationRule;

/**
 * Demo create custom, annotation initializable rules
 * To make this initializable by using annotation,
 * implement the IConstraintInitializable with the corresponding annotation
 *
 * Implementing IValidationRule with the corresponding type is required to make this a validation rule
 */
public class IdRule implements IConstraintInitializable<Id>, IValidationRule<String> {
    int length = -1;
    @Override
    public void initialize(Id constraint) {
        length = constraint.length();
    }

    @Override
    public boolean isValid(String value) {
        return value.length() > length && value.charAt(0) == 'I';
    }
}
