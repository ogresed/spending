package table.component;

import javax.swing.*;

public abstract class MyComponent extends JPanel {
    public MyComponent (String name) {
        setBorder(BorderFactory.createTitledBorder(name));
    }
}
