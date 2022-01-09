package demo.customs;

import com.design_pattern_grp23.sibyl.IValidationListener;
import com.design_pattern_grp23.sibyl.ValidationResult;

import javax.swing.*;

/**
 * Demo creating a custom validation listener
 */
public class TextLabelErrorListener implements IValidationListener {
    private final JLabel label;

    public TextLabelErrorListener(JLabel label) {
        this.label = label;
    }

    @Override
    public void afterValidation(ValidationResult result) {
        if (result.hasError()) {
            label.setText(result.getErrors().get(0).getMessage());
        }
        else {
            label.setText(" ");
        }
    }
}
