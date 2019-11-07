package dbConnectWindow.nfield;

import table.component.Fenced;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    public void addButtonTypedListener(ButtonTypedAction action) {

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                action.pressedKeyAction(e);
            }
        });
    }
}
