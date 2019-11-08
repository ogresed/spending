package table.component;

import javax.swing.*;
import java.awt.*;

public abstract class Field extends JPanel {
    protected ComponentType TYPE;
    public Field(String name) {
        setLayout(new GridLayout(1, 2));
        add(new JLabel(name));
    }

    protected abstract String getValue ();
}
