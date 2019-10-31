package table.component;

import javax.swing.*;

public abstract class Fenced extends JPanel {
    public Fenced(String name) {
        setBorder(BorderFactory.createTitledBorder(name));
    }
}
