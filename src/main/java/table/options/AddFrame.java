package table.options;

import mainWindow.baseFrame.MonitorSizes;
import table.component.Field;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddFrame extends JFrame {
    private JPanel fieldPanel;
    private JButton button;
    private ArrayList<Field> attrList;

    public AddFrame() {
        String title = "Добавить";
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        add(fieldPanel, BorderLayout.CENTER);

        attrList = new ArrayList<>();

        button = new JButton(title);
        var buttonPanel = new JPanel();
        buttonPanel.add(button);
        add(buttonPanel, BorderLayout.SOUTH);
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
        attrList.add(field);
    }

    private String createAttributesList() {
        var builder = new StringBuilder("values (").append(attrList.get(0));
        var size = attrList.size();
        for (int i = 1; i< size; i++) {
            builder.append("'").append(attrList.get(i));
        }
        builder.append(")");
        return builder.toString();
    }

    public String createSeletRequest() {
        return  null;
    }
}
