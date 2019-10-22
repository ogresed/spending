package table;

import requester.Requester;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Vector;

public class VisibleTable extends JPanel {
    private Requester requester;
    private MyTable table;
    private JLabel labnel;
    private Vector<Vector<String>> data;

    public VisibleTable (Requester requester, MyTable table) {
        this.requester = requester;
        this.table = table;
        setLayout(new GridLayout(2, 1));

        Vector<String> header = new Vector<>();
        Collections.addAll(header, table.getAttributesNames());
        // Данные для таблицы на основе Vector
        data = new Vector<>();

        /*setResult(requester.getRecords(year, month));
        setNumber(requester.getCount(year, month));

        JTable table = new JTable(data, header);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table));

        add(contents, BorderLayout.CENTER);
        add(countLabel, BorderLayout.SOUTH);*/
    }

    public String getName() {
        return table.getName();
    }
}
