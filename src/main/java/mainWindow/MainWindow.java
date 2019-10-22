package mainWindow;

import mainWindow.baseFrame.BaseFrame;
import requester.Requester;
import table.MyTable;
import table.VisibleTable;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends BaseFrame {
    private Requester requester;
    private TablesList tablesList;

    public MainWindow(Requester requester) {
        super(JFrame.EXIT_ON_CLOSE, "Траты");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int startWidth = (dimension.width) / 2;
        int startHeight = 4* (dimension.height) / 5;
        setBounds((dimension.width - startWidth)/2, (dimension.height - startHeight)/2, startWidth, startHeight);
        createButtons();

        String[] d = {"2","1","3","5"};
        var table = new MyTable("spending", d);
        var visibles = new VisibleTable(requester, table);
        VisibleTable[] v = new VisibleTable[1];
        v[0] = visibles;
        tablesList = new TablesList(v);

        setVisible(true);
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
