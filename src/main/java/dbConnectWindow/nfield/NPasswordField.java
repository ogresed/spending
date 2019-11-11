package dbConnectWindow.nfield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NPasswordField extends Field implements NField {
    private JPasswordField passwordField;

    public NPasswordField (String title) {
        super(title);
        setLayout(new GridLayout(1,1));
        passwordField = new JPasswordField();
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
