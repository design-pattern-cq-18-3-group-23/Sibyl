package demo;

import com.design_pattern_grp23.sibyl.ObjectFieldValidatorBuilder;
import com.design_pattern_grp23.sibyl.Sibyl;
import com.design_pattern_grp23.sibyl.ValidationError;
import com.design_pattern_grp23.sibyl.ValidationResult;
import com.design_pattern_grp23.sibyl.rules.LengthRule;
import com.design_pattern_grp23.sibyl.validators.ObjectFieldValidator;

public class TestDummy {
    public static void main(String[] args) {
        Dummy dummy = new Dummy("He", "1", "a@mail.com", "a1", 11);
        ObjectFieldValidator<Dummy> validator = Sibyl.from(Dummy.class);
        ObjectFieldValidatorBuilder<Dummy> builder = Sibyl.getBuilder(validator);
        builder.addRule(
            "firstname",
            new LengthRule(3, -1),
            "First name must be at least 3 characters"
        )
        .addListener("firstname", result -> {
            if (result.hasError()) {
                System.out.println(result.getErrors().get(0).getMessage());
            }
        })
        .addListener("lastname", result -> {
            if (result.hasError()) {
                System.out.println(result.getErrors().get(0).getMessage());
            }
        });

        ValidationResult result = validator.validate(dummy);
        if (result.hasError()) {
            for (ValidationError error : result) {
                System.out.println("" + error.getMessage());
            }
        } else {
            System.out.println("Passed validation");
        }
    }
}
