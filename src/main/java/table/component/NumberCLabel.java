package table.component;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberCLabel extends CLabel {
    public NumberCLabel(String name) {
        super(name);
        space.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char entered = e.getKeyChar();
                if(entered > KeyEvent.VK_9 || entered < KeyEvent.VK_0) {
                    e.consume();
                }
            }
        });
    }

    @Override
    public String getValue() {
        return space.getText();
    }
}
