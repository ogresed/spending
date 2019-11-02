package mainWindow;

import mainWindow.baseFrame.*;
import requester.Requester;
import table.VisibleTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class MainWindow extends BaseFrame {
    private HashMap<String, VisibleTable> tables = new HashMap<>();
    private JPanel tablesListPanel = new JPanel();
    private JList<String> tablesList;
    private JPanel mainPanel;

    public MainWindow(Requester requester) {
        super(JFrame.EXIT_ON_CLOSE, "Траты");
        var startWidth = (MonitorSizes.WIDTH_OF_MONITOR) / 2;
        var startHeight = 4* (MonitorSizes.HEIGHT_OF_MONITOR) / 5;
        setBounds((MonitorSizes.WIDTH_OF_MONITOR - startWidth)/2,
                (MonitorSizes.HEIGHT_OF_MONITOR - startHeight)/2,
                startWidth, startHeight);
        createButtons();

        var tablesAndAttributes =  requester.getMeta();
        VisibleTable.requester = requester;
        mainPanel = new JPanel(new CardLayout());
        Vector<String> names = createTables(tablesAndAttributes);
        tablesList = new JList<>(names);
        setupTablesList();
        addingComponents();
        //pack();
        setVisible(true);
    }

    private void setupTablesList() {
        tablesList.addListSelectionListener(e-> {
            var selected = tablesList.getSelectedValue();
            tables.get(selected).setValues();
            ((CardLayout)mainPanel
                    .getLayout())
                    .show(mainPanel, selected);
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
        return names;
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

    private void addingComponents() {
        tablesListPanel.add(tablesList);
        add(mainPanel, BorderLayout.CENTER);
        add(tablesListPanel, BorderLayout.EAST);
    }
}
