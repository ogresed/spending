package table;

import logging.LoggingConst;
import mainWindow.baseFrame.StatusBar;
import requester.Requester;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
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


        Vector<String> header = new Vector<>(attrs);
        data = new Vector<>();
        JTable table = new JTable(data, header);
        //pane.add(table);


        bar = new StatusBar(100, 16);
        bar.setMessage("123");
        pane = new JScrollPane(table);



        add(new JLabel(nameOfTable), BorderLayout.NORTH);
        add(pane, BorderLayout.CENTER);
        add(bar, BorderLayout.SOUTH);
    }

    private void creating() {
        bar = new StatusBar(100, 16);
        bar.setMessage("123");
        pane = new JScrollPane();
    }

    private void adding () {
        add(new JLabel(nameOfTable), BorderLayout.NORTH);
        add(pane, BorderLayout.CENTER);
        add(bar, BorderLayout.SOUTH);
    }

    public void setValues(ResultSet set) {
        try {
            while (set.next()) {
                data = new Vector<>();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, WRONG_CONNECTION, e);
        }
    }
}
