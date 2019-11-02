package table;

import logging.LoggingConst;
import mainWindow.baseFrame.StatusBar;
import requester.Requester;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class VisibleTable2 extends JPanel implements LoggingConst {
    public static Requester requester;
    public StatusBar bar;
    private JScrollPane pane;
    private String nameOfTable;
    private ArrayList<String> attrs;
    private Vector<Vector<String>> data;
    private static Logger logger;
    private String query;

    static {
        try(FileInputStream inputStream = new FileInputStream(LOGGING_FILE_NAME)) {
            LogManager.getLogManager().readConfiguration(inputStream);
            logger = Logger.getLogger(VisibleTable2.class.getName());
        } catch (IOException e) {
            System.err.println("Impossible to open logging config file");
            System.exit(0);
        }
    }

    public VisibleTable2(String nameOfTable, ArrayList<String> attrs) {
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
