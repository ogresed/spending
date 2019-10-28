package dbConnectWindow;

import javax.swing.*;

public class BNTextField extends JTextField {
    BNTextField (String title) {
        setBorder(BorderFactory.createTitledBorder(title));
    }
}
