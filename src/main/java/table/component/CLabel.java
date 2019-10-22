package table.component;

import javax.swing.*;

public class CLabel extends MyComponent {
    private JTextArea space;

    public CLabel(String name) {
        super(name);
        space = new JTextArea();
    }

    public String getString() {
        return space.getText();
    }
}
