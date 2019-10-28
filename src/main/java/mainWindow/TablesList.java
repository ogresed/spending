package mainWindow;

import mainWindow.baseFrame.MonitorSizes;
import table.VisibleTable;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class TablesList extends JFrame {
    private VisibleTable[] tables;

    public TablesList(VisibleTable[] tables) {
        super();
        this.tables = tables;
        int startWidth = (MonitorSizes.WIDTH_OF_MONITOR) / 4;
        int startHeight = (MonitorSizes.HEIGHT_OF_MONITOR) / 2;
        setBounds((MonitorSizes.WIDTH_OF_MONITOR - startWidth)/2, (MonitorSizes.HEIGHT_OF_MONITOR - startHeight)/2,
                startWidth, startHeight);

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
