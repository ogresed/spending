package dbConnectWindow.nfield;

import javax.swing.*;

public class NPasswordField extends Fenced implements NField {
    private JPasswordField passwordField;

    public NPasswordField (String title) {
        super(title);
        passwordField = new JPasswordField(14);
        add(passwordField);
    }

    @Override
    public String getText() {
        return new String(passwordField.getPassword());
    }
}
