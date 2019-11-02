package table;

import mainWindow.baseFrame.StatusBar;
import requester.Requester;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import static logging.MyLogger.WRONG_CONNECTION;
import static logging.MyLogger.logger;

public class VisibleTable extends JPanel {
    public static Requester requester;
    public StatusBar bar;
    private JScrollPane pane;
    private String nameOfTable;
    private ArrayList<String> attrs;
    private Vector<Vector<String>> data;
    private String query;

    public VisibleTable(String nameOfTable, ArrayList<String> attrs) {
        this.nameOfTable = nameOfTable;
        this.attrs = attrs;
        setLayout(new BorderLayout());

        Vector<String> header = new Vector<>(attrs);
        data = new Vector<>();
        //load data
        query = "select * from "+ nameOfTable + ";";
        setValues();
        JTable table = new JTable(data, header);

        bar = new StatusBar(100, 16);
        pane = new JScrollPane(table);

        adding();
    }

    private void adding () {
        add(new JLabel(nameOfTable), BorderLayout.NORTH);
        add(pane, BorderLayout.CENTER);
        add(bar, BorderLayout.SOUTH);
    }

    public void setValues() {
        if(data.size() == 0) {
            var set = requester.getRecords(query);
            try {
                while (set.next()) {
                    var column = new Vector<String>();
                    for (int i = 0; i < attrs.size(); i++) {
                        var val = set.getString(attrs.get(i));
                        column.add(val);
                    }
                    data.add(column);
                }
            } catch (SQLException e) {
                logger.log(Level.WARNING, WRONG_CONNECTION, e);
            }
        }
    }

    public String getNameOfTable() {
        return nameOfTable;
    }
}
