package table.component;

import javax.swing.*;

public abstract class Field extends JPanel {
    protected ComponentType TYPE;
    public Field(String name) {
        //setLayout(new GridLayout(1, 2));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(new JLabel(name + "    "));
    }

    public abstract String getValue();
}
