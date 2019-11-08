package table.component;

import javax.swing.*;

import static table.component.ComponentType.TEXT;

public class CLabel extends Field {
    JTextArea space;

    public CLabel(String name) {
        super(name);
        TYPE = TEXT;
        space = new JTextArea(1,14);
        add(space);

        space.setLineWrap(true);
        space.setWrapStyleWord(true);
    }
    @Override
    public String getValue() {
        return "'" + space.getText() + "'";
    }
}
