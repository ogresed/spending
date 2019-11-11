package table.component;

import javax.swing.*;

import static table.component.ComponentType.LIST;

public class CList extends Field {
    private JComboBox<String> box;
    private String[] names;

    public CList (String name, String[] names) {
        super(name);
        TYPE = LIST;
        this.names = names;
        box = new JComboBox<>(names);
        add(box);
    }
    @Override
    public String getValue() {
        return  "'" + names[box.getSelectedIndex()] + "'";
    }

    public int getSelectedIndex() {
        return box.getSelectedIndex();
    }
}
