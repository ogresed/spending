package table.component;

import javax.swing.*;

public class CList extends Fenced {
    private JComboBox<String> box;
    private String[] names;

    public CList (String name, String[] names) {
        super(name);
        this.names = names;
        box = new JComboBox<>(names);
    }

    public String getSelectedName() {
        return  names[box.getSelectedIndex()];
    }

    public int getSelectedIndex() {
        return box.getSelectedIndex();
    }
}
