package demo.customs;

import com.design_pattern_grp23.sibyl.ValidationConstraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Demo create custom, annotation initializable rules
 */
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@ValidationConstraint(validatedBy = IdRule.class)
public @interface Id {
    /**
     * message() is required to show error message
     */
    String message() default "Id does not pass validation";

    /**
     * Extra properties can be declare to use in the corresponding validation rule
     */
    int length();
}
