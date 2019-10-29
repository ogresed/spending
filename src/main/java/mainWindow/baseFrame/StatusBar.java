package mainWindow.baseFrame;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JLabel {
    /** Creates a new instance of StatusBar
     * 100, 16
     * */
    public StatusBar(int width, int height) {
        super.setPreferredSize(new Dimension(width, height));
    }

    public void setMessage(String text) {
        setText(text);
    }
}
