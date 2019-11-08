package table.unresolved;

import table.component.Field;

import javax.swing.*;
import java.awt.*;

public class AddingWindow extends JFrame {

    public AddingWindow(String name,  Field[] components) {
        setTitle(name);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int startWidth = (dimension.width) / 3;
        int startHeight = 3* (dimension.height) / 5;
        setBounds((dimension.width - startWidth)/2, (dimension.height - startHeight)/2, startWidth, startHeight);

        JScrollPane pane = new JScrollPane();
        add(pane);
        pane.setLayout(new GridLayout(components.length + 1, 1));
        for(Field component : components) {
            pane.add(component);
        }

        JButton button = new JButton("Добавить");
        pane.add(button);
        button.addActionListener(e -> {

        });
    }
}
