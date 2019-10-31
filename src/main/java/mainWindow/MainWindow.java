package mainWindow;


import mainWindow.baseFrame.*;
import requester.Requester;
import table.VisibleTable;
import table.VisibleTable2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainWindow extends BaseFrame {
    private Requester requester;
    private TablesList tablesList;
    private ArrayList<VisibleTable2> tables = new ArrayList<>();

    public MainWindow () {
        super(JFrame.EXIT_ON_CLOSE, "Траты");
        String[] wer = {"123", "234", "456456", "657","4568"};

        add(new VisibleTable2("Str", new ArrayList<String>(Arrays.asList(wer))));
        setVisible(true);
    }

    public MainWindow(Requester requester) {
        super(JFrame.EXIT_ON_CLOSE, "Траты");
        int startWidth = (MonitorSizes.WIDTH_OF_MONITOR) / 2;
        int startHeight = 4* (MonitorSizes.HEIGHT_OF_MONITOR) / 5;
        setBounds((MonitorSizes.WIDTH_OF_MONITOR - startWidth)/2, (MonitorSizes.HEIGHT_OF_MONITOR - startHeight)/2,
                startWidth, startHeight);
        createButtons();

        var tablesAndAttributes =  requester.getMeta();
        createTables(tablesAndAttributes);

        setVisible(true);
    }

    private void createTables(HashMap<String, ArrayList<String>> tablesAndAttributes) {
        tablesAndAttributes.forEach((key, value) -> {
            if(Character.isUpperCase( key.charAt(0))) {
                var table = new VisibleTable2(key, value);
                add(table);
                tables.add(table);
            }
        });
        tables.get(0).setVisible(true);
    }

    @Override
    protected void createButtons() {
        JMenu fileMenu = makeMenu("Файл", 'F');
        createAction(fileMenu, "Выход", e->System.exit(0),'E',"Выход из приложения");
        addSeparator();
        JMenu usageMenu = makeMenu("Использование", 'U');
        createAction(usageMenu, "Добавить", e -> {},'A',"Добавить запись");
        createAction(usageMenu, "Запрос", e-> {},'R',"Сделать запрос");
        createAction(usageMenu, "Таблицы", e -> tablesList.setVisible(true), 'T', "Открыть список таблиц");
    }
}
