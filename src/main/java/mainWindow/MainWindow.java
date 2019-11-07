package mainWindow;

import mainWindow.baseFrame.*;
import requester.Requester;
import table.AddRequestTable;
import table.BaseTable;
import table.VisibleTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;

import static logging.MyLogger.WRONG_CONNECTION;
import static logging.MyLogger.logger;

public class MainWindow extends BaseFrame {
    private HashMap<String, BaseTable> tables = new HashMap<>();
    private JPanel tablesListPanel = new JPanel();
    private JList<String> tablesList;
    private JPanel mainPanel;
    private Vector<String> names;

    public MainWindow(Requester requester) {
        //set base options
        super(JFrame.EXIT_ON_CLOSE, "Траты");
        var startWidth = (MonitorSizes.WIDTH_OF_MONITOR) / 2;
        var startHeight = 4* (MonitorSizes.HEIGHT_OF_MONITOR) / 5;
        setBounds((MonitorSizes.WIDTH_OF_MONITOR - startWidth)/2,
                (MonitorSizes.HEIGHT_OF_MONITOR - startHeight)/2,
                startWidth, startHeight);
        //create buttons
        createButtons();
        //create tables without addition parameters
        var tablesAndAttributes =  requester.getMeta();
        BaseTable.requester = requester;
        mainPanel = new JPanel(new CardLayout());
        names = createTables(tablesAndAttributes);
        tablesList = new JList<>(names);
        //setup simple tables
        setupSimpleTables();
        //add tables with add/request options
        createAddRequestTables();
        //setup tables with additional options
        //
        setupTablesList();
        addingComponents();
        //pack();
        revalidate();
        setVisible(true);
    }

    private void setupTablesList() {
        tablesList.addListSelectionListener(e-> {
            var selected = tablesList.getSelectedValue();
            tables.get(selected).setValues();
            ((CardLayout)mainPanel
                    .getLayout())
                    .show(mainPanel, selected);
            /*revalidate();
            mainPanel.revalidate();*/
        });
    }

    private Vector<String>  createTables(HashMap<String, ArrayList<String>> tablesAndAttributes) {
        Vector<String> names = new Vector<>();
        tablesAndAttributes.forEach((key, value) -> {
            if(Character.isUpperCase( key.charAt(0))) {
                var table = new VisibleTable(key, value);
                mainPanel.add(table, key);
                tables.put(key, table);
                names.add(key);
            }
        });
        String selectableTable = names.get(0);
        tables.get(selectableTable).setValues();
        return names;
    }
    /**
     * add AddRequestTables to mainPanel and to tables
     * add tables name to names
     * */
    private void createAddRequestTables() {
        String spending = "spending";
        AddRequestTable spendingTable = new AddRequestTable(spending).
                fromGetAllAttributes().
                orderByASC("Date");
        adding(spending, spendingTable);
        spendingTable.setOutputToStatusBarOnSelectQuery(() -> {
            String ret = "Всего потрачено: ";
            String sum = "";
            String query =
                    "select sum(cost) sum from spending;";
            var set = BaseTable.requester.getRecords(query);
            if(set.next()) {
                sum = set.getString("sum");
                return ret + sum;
            }
            return ret;
        });
    }

    private void adding(String nameOfTable, AddRequestTable table) {
        mainPanel.add(table, nameOfTable);
        tables.put(nameOfTable, table);
        names.add(nameOfTable);
    }

    @Override
    protected void createButtons() {
        JMenu fileMenu = makeMenu("Файл", 'F');
        createAction(fileMenu, "Выход", e->System.exit(0),'E',"Выход из приложения");
        addSeparator();
        JMenu usageMenu = makeMenu("Использование", 'U');
        createAction(usageMenu, "Добавить", e -> {},'A',"Добавить запись");
        createAction(usageMenu, "Запрос", e-> {},'R',"Сделать запрос");
    }

    private void setupSimpleTables() {
        String tablesName = "MoneyPerDay";
        var table = tables.get(tablesName);
        table.setOutputToStatusBarOnSelectQuery(() -> {
            String ret = "В среднем тратил за день: ";
            String sumStr = "";
            try {
                String query =
                        "select avg(sum) sum from MoneyPerDay;";
                var set = BaseTable.requester.getRecords(query);
                if(set.next()) {
                    sumStr = set.getString("sum");
                }
                return ret + sumStr;
            } catch (SQLException ex) {
                logger.log(Level.WARNING, WRONG_CONNECTION, ex);
                return ret + sumStr;
            }
        });
    }

       private void addingComponents() {
        tablesListPanel.add(tablesList);
        add(mainPanel, BorderLayout.CENTER);
        add(tablesListPanel, BorderLayout.EAST);
    }
}