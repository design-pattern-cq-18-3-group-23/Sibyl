package demo;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame AppFrame = new JFrame("Demo");
        DemoForm demoForm = new DemoForm();
        AppFrame.setContentPane(demoForm.getMainPanel());
        AppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AppFrame.getRootPane().setDefaultButton(demoForm.getDefaultButton());

        AppFrame.pack();
        AppFrame.setVisible(true);
    }
}