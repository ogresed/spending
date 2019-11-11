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

public abstract class BaseTable extends JPanel {
    public static Requester requester;
    private StatusBar bar;
    private JScrollPane pane;
    String nameOfTable;
    private ArrayList<String> attrs;
    private Vector<Vector<String>> data;
    private OutputMessage outputMessage;
    String query;

    BaseTable (String nameOfTable) {
        attrs = requester.getMeta().get(nameOfTable);
        baseConstructor(nameOfTable);
    }

    BaseTable (String nameOfTable, ArrayList<String> attrs) {
        this.attrs = attrs;
        baseConstructor(nameOfTable);
    }

    private void baseConstructor(String nameOfTable) {
        this.nameOfTable = nameOfTable;
        setLayout(new BorderLayout());

        Vector<String> header = new Vector<>(attrs);
        data = new Vector<>();
        //setValues();
        JTable table = new JTable(data, header);

        pane = new JScrollPane(table);
        bar = new StatusBar(100, 16);

        adding();
    }

    private void adding () {
        add(new JLabel(nameOfTable), BorderLayout.NORTH);
        add(pane, BorderLayout.CENTER);
        add(bar, BorderLayout.SOUTH);
    }
/**
 * очень важно вызвать set.next, чтобы не вызвать ошибку
 * этим передвигается курсор в полученом картеже
 *
 * very important call set.next to don't have  an exception
 * using this way the cartage's cursor is moved
 * * */
    public void setValues() {
        if(data.size() == 0) {
            var set = requester.getRecords(createStringQuery());
            try {
                while (set.next()) {
                    var column = new Vector<String>();
                    for (String attr : attrs) {
                        var val = set.getString(attr);
                        column.add(val);
                    }
                    data.add(column);
                }
                if(outputMessage != null) {
                    bar.setMessage(outputMessage.output());
                }
            } catch (SQLException e) {
                logger.log(Level.WARNING, WRONG_CONNECTION, e);
            }

            pane.revalidate();
            repaint();
        }
    }

    public String getQuery() {
        return query;
    }

    public void setOutputToStatusBarOnSelectQuery(OutputMessage outputMessage) {
        this.outputMessage = outputMessage;
    }

    public String getNameOfTable() {
        return nameOfTable;
    }

    public abstract String createStringQuery();

    public abstract JFrame getSelectedWindow();
}
