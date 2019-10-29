package dbConnectWindow.nfield;

import javax.swing.*;

public class NTextField extends Fenced implements NField {
    private JTextField textField;

    public NTextField(String title) {
        super(title);
        textField = new JTextField(14);
        add(textField);
    }
    @Override
    public String getText() {
        return textField.getText();
    }

    public void setText(String str) {
        textField.setText(str);
    }
}
