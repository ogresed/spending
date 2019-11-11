package table.component;

import javax.swing.*;

import static table.component.ComponentType.TEXT;

public class CTextField extends Field {
    private static final int DEFAULT_NUMBER_OF_ROWS = 3;
    JTextArea space;

    CTextField(String name, int rows) {
        super(name);
        setOptions(rows);
    }

    public CTextField(String name) {
        super(name);
        setOptions(DEFAULT_NUMBER_OF_ROWS);
    }

    private void setOptions(int rows) {

        int numberOfColumns = 14;
        space = new JTextArea(rows, numberOfColumns);
        TYPE = TEXT;
        add(space);

        space.setLineWrap(true);
        space.setWrapStyleWord(true);
    }

    @Override
    public String getValue() {
        return "'" + space.getText() + "'";
    }
}
