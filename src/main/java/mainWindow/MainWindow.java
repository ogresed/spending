package mainWindow;

import mainWindow.baseFrame.*;
import requester.Requester;
import table.AddRequestTable;
import table.BaseTable;
import table.VisibleTable;
import table.component.CLabel;
import table.component.CList;
import table.component.NumberCLabel;
import table.component.datePanel.DatePanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;

import static logging.MyLogger.WRONG_CONNECTION;
import static logging.MyLogger.logger;
import static table.component.datePanel.DatePanel.Mode.SET_CURRENT_DATE;
import static table.component.datePanel.DatePanel.Option.All;

public class MainWindow extends BaseFrame {
    private HashMap<String, BaseTable> tables = new HashMap<>();
    private JPanel tablesListPanel = new JPanel();
    private JList<String> tablesList;
    private JPanel mainPanel;
    private Vector<String> names;
    private JFrame selectWindow;

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
            var table = tables.get(selected);
            table.setValues();
            ((CardLayout)mainPanel
                    .getLayout())
                    .show(mainPanel, selected);
            selectWindow = table.getSelectedWindow();
        });
    }

    private Vector<String>  createTables(HashMap<String, ArrayList<String>> tablesAndAttributes) {
        var names = new Vector<String>();
        tablesAndAttributes.forEach((key, value) -> {
            if(Character.isUpperCase( key.charAt(0))) {
                var table = new VisibleTable(key, value);
                mainPanel.add(table, key);
                tables.put(key, table);
                names.add(key);
            }
        });
        var selectableTable = names.get(0);
        tables.get(selectableTable).setValues();
        return names;
    }
    /**
     * add AddRequestTables to mainPanel and to tables
     * add tables name to names
     * */
    private void createAddRequestTables() {
        var spending = "spending";
        var spendingTable = new AddRequestTable(spending).
                fromGetAllAttributes().
                orderByASC("Date");
        adding(spending, spendingTable);
        spendingTable.setOutputToStatusBarOnSelectQuery(() -> {
            var ret = "Всего потрачено: ";
            var sum = "";
            var query =
                    "select sum(cost) sum from spending;";
            var set = BaseTable.requester.getRecords(query);
            if(set.next()) {
                sum = set.getString("sum");
                return ret + sum;
            }
            return ret;
        });
        spendingTable.addSelectableAttribute(new CLabel("Описание"));
        spendingTable.addSelectableAttribute(new NumberCLabel("Стоимость"));
        String[] types = {
                "еда", "вещи", "развлечения", "другое", "проезд", "услуги", "продукты"
        };
        spendingTable.addSelectableAttribute(new CList("Тип", types));
        spendingTable.addSelectableAttribute(new DatePanel("Дата", All, SET_CURRENT_DATE));
    }

    private void adding(String nameOfTable, AddRequestTable table) {
        mainPanel.add(table, nameOfTable);
        tables.put(nameOfTable, table);
        names.add(nameOfTable);
    }

    @Override
    protected void createButtons() {
        var fileMenu = makeMenu("Файл", 'F');
        createAction(fileMenu, "Выход", e->System.exit(0),'E',"Выход из приложения");
        addSeparator();
        var usageMenu = makeMenu("Использование", 'U');
        createAction(usageMenu, "Добавить", e -> onSelect(),'A',"Добавить запись");
        createAction(usageMenu, "Запрос", e-> {},'R',"Сделать запрос");
    }

    private void onSelect() {
        try {
            selectWindow.setVisible(true);
        } catch (NullPointerException ignore) {}
    }

    private void setupSimpleTables() {
        var tablesName = "MoneyPerDay";
        var table = tables.get(tablesName);
        table.setOutputToStatusBarOnSelectQuery(() -> {
            var ret = "В среднем тратил за день: ";
            var sumStr = "";
            try {
                var query =
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