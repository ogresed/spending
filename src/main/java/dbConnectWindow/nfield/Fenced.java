package dbConnectWindow.nfield;

import javax.swing.*;

public abstract class Fenced extends JPanel {
    public Fenced(String title) {
        setBorder(BorderFactory.createTitledBorder(title));
    }
}
