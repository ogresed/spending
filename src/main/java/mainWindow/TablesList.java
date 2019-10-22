package mainWindow;

import table.VisibleTable;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class TablesList extends JFrame {
    private VisibleTable[] tables;

    public TablesList(VisibleTable[] tables) {
        super();
        this.tables = tables;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int startWidth = (dimension.width) / 4;
        int startHeight = (dimension.height) / 2;
        setBounds((dimension.width - startWidth)/2, (dimension.height - startHeight)/2, startWidth, startHeight);

        JButton button = new JButton("Открыть");
        Vector<String> vector = new Vector<>();
        for(VisibleTable table : tables) {
            vector.add(table.getName());
        }
        JList<String> list = new JList<>(vector);
        add(list);
        add(button);

        button.addActionListener(e -> {
            setVisible(false);
            tables[list.getSelectedIndex()].setVisible(true);
        });
    }
}
