package dbConnectWindow;

import javax.swing.*;

public class BNPasswordField extends JPasswordField {
    BNPasswordField(String title) {
        setBorder(BorderFactory.createTitledBorder(title));
    }
}
