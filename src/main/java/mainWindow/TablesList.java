package mainWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

class TablesList extends JList<String> {

    TablesList() {
        addListSelectionListener(this::valueChanged);
    }

    private void valueChanged(ListSelectionEvent e) {

    }
}
