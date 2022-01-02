package com.design_pattern_grp23.sibyl;

import com.design_pattern_grp23.sibyl.rules.IValidationRule;
import com.design_pattern_grp23.sibyl.validators.IValidator;
import com.design_pattern_grp23.sibyl.validators.ObjectFieldValidator;
import com.design_pattern_grp23.sibyl.validators.RuleValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Sibyl {
    public static <T> ObjectFieldValidator<T> from(Class<T> clazz) {
        ObjectFieldValidator<T> validator = new ObjectFieldValidator<>();

        for (Field field: clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations) {
                ValidationConstraint constraint =
                        annotation.annotationType().getDeclaredAnnotation(ValidationConstraint.class);
                if (constraint == null) {
                    continue;   // Ignore, not a validation constraint
                }

                Class<?> ruleClass = constraint.validatedBy();

                try {
                    Object ruleObj = ruleClass.getDeclaredConstructor().newInstance();
                    // Init
                    Method init = ruleObj.getClass().getDeclaredMethod("initialize", Annotation.class);
                    init.invoke(ruleObj, annotation);

                    IValidationRule<?> rule = (IValidationRule<?>) ruleObj;
                    Method message = annotation.annotationType().getDeclaredMethod("message");
                    IValidator<?> ruleValidator = new RuleValidator<>(rule, (String) message.invoke(annotation));
                    validator.add(fieldName, ruleValidator);
                } catch (
                    InstantiationException | IllegalAccessException
                    | InvocationTargetException | NoSuchMethodException e
                ) {
                    e.printStackTrace();
                }
            }
        }

        return validator;
    }

    public static <T> ObjectFieldValidatorBuilder<T> getBuilder() {
        return new ObjectFieldValidatorBuilder<>();
    }

    public static <T> ObjectFieldValidatorBuilder<T> getBuilder(ObjectFieldValidator<T> validator) {
        return new ObjectFieldValidatorBuilder<>(validator);
    }
}
