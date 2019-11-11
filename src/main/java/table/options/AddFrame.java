package table.options;

import mainWindow.baseFrame.MonitorSizes;
import table.AddRequestTable;
import table.component.Field;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddFrame extends JFrame {
    private JPanel fieldPanel;
    private JButton button;
    private ArrayList<Field> attrList;
    private static final String title = "Добавить";
    private AddRequestTable parentTable;

    public AddFrame(AddRequestTable table) {
        parentTable = table;
        setBaseSettings();

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        add(fieldPanel, BorderLayout.CENTER);

        attrList = new ArrayList<>();

        button = new JButton(title);
        button.addActionListener(e -> onClickButton());
        var buttonPanel = new JPanel();
        buttonPanel.add(button);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void onClickButton() {
        String insertQuery = createStringQuery();
        parentTable.select(insertQuery);
        setVisible(false);
    }

    private String createStringQuery() {
        return "insert into " + parentTable.getNameOfTable() + " values (" +
                createAttributesList() + ";";
    }

    private void setBaseSettings() {
        setResizable(false);
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public void centralize() {
        pack();
        var width = getWidth();
        var height = getHeight();
        setLocation((MonitorSizes.WIDTH_OF_MONITOR - width)/2,
                (MonitorSizes.HEIGHT_OF_MONITOR - height)/2);
    }

    public void addField(Field field) {
        fieldPanel.add(field);
        fieldPanel.add(new JLabel("   "));
        attrList.add(field);
    }

    private String createAttributesList() {
        var builder = new StringBuilder().append(attrList.get(0).getValue());
        var size = attrList.size();
        for (int i = 1; i< size; i++) {
            builder.append(",").append(attrList.get(i).getValue());
        }
        builder.append(")");
        return builder.toString();
    }

    public String createSeletRequest() {
        return  null;
    }
}
