package dbConnectWindow.nfield;

import javax.swing.*;

class Field extends JPanel {
    Field(String name) {
        setBorder(BorderFactory.createTitledBorder(name));
    }
}
