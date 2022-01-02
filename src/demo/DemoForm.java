package demo;

import com.design_pattern_grp23.sibyl.*;
import com.design_pattern_grp23.sibyl.rules.IValidationRule;
import com.design_pattern_grp23.sibyl.rules.LengthRule;
import com.design_pattern_grp23.sibyl.validators.ObjectFieldValidator;
import demo.customs.TextLabelErrorListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DemoForm implements ActionListener, IValidationListener {
    private JPanel pnlMain;

    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JTextField txtEmail;
    private JTextField txtId;
    private JTextField txtGPA;

    private final List<JLabel> txtErrInputs;
    private JLabel errTxtLastName;
    private JLabel errTxtFirstName;
    private JLabel errTxtEmail;
    private JLabel errTxtId;
    private JLabel errTxtGPA;

    private JButton btnValidate;
    private JList<Object> listErr;

    ObjectFieldValidator<StudentFormData> validator;

    public DemoForm() {
        txtErrInputs = new ArrayList<>();
        txtErrInputs.add(errTxtFirstName);
        txtErrInputs.add(errTxtLastName);
        txtErrInputs.add(errTxtEmail);
        txtErrInputs.add(errTxtId);
        txtErrInputs.add(errTxtGPA);
        init();
    }

    public JPanel getMainPanel() {
        return pnlMain;
    }

    public JButton getDefaultButton() {
        return btnValidate;
    }

    private void init() {
        // Automatic initialization
        validator = Sibyl.from(StudentFormData.class);

        // Get builder to add custom action
        ObjectFieldValidatorBuilder<StudentFormData> builder = Sibyl.getBuilder(validator);

        // Adding validation dynamically
        LengthRule dynamicRule = new LengthRule();
        dynamicRule.setMax(10);
        builder.addRule("firstname", dynamicRule, "Tên tối đa 10 ký tự");

        // Adding custom validation with lambda
        builder.addRule(
                "firstname",
                (IValidationRule<String>) value -> value.length() >= 3,
                "Tên tối thiểu 3 ký tự"
        );

        // Adding validation listener to validator
        validator.addListener("lastname", new TextLabelErrorListener(errTxtLastName));
        validator.addListener("firstname", new TextLabelErrorListener(errTxtFirstName));

        // Bulk adding listeners with builder
        builder.addListener("email", new TextLabelErrorListener(errTxtEmail))
               .addListener("id", new TextLabelErrorListener(errTxtId))
               .addListener("gpa", new TextLabelErrorListener(errTxtGPA))
               .addListener(this); // Listen to all validation error

        for (JLabel txtErr: txtErrInputs) {
            txtErr.setText(" ");
        }
        btnValidate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnValidate) {
            validator.validate(collectInput());
        }
    }

    StudentFormData collectInput() {
        String lastname = txtLastName.getText();
        String firstname = txtFirstName.getText();
        String email = txtEmail.getText();
        String id = txtId.getText();
        String gpaText = txtGPA.getText();
        double gpa;
        try {
            gpa = Double.parseDouble(gpaText);
        } catch (Exception e) {
            gpa = 0.0;
        }
        return new StudentFormData(firstname, lastname, email, id, gpa);
    }

    @Override
    public void afterValidation(ValidationResult result) {
        if (!result.hasError()) {
            listErr.setListData((new ArrayList<String>()).toArray());
            JOptionPane.showMessageDialog(pnlMain, "Chúc mừng! Không có lỗi nào!");
        }
        List<String> errMessages = new ArrayList<>();
        for (ValidationError error: result.getErrors()) {
            errMessages.add(error.getMessage());
        }
        listErr.setListData(errMessages.toArray());
    }
}
