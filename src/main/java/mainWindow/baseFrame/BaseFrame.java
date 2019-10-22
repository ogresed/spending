package mainWindow.baseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * usage:
 * override method createButtons using methods createAction and makeMenu
 * */

public abstract class BaseFrame extends JFrame {
    private JMenuBar menuBar;
    private JToolBar toolBar;

    protected BaseFrame(int option, String name) {
        //set base options
        setDefaultCloseOperation(option);
        setTitle(name);
        //create menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //create toolbar
        toolBar = new JToolBar();
        add(toolBar, BorderLayout.PAGE_START);
        toolBar.setRollover(true);
    }

    protected void createButtons() {

    }

    protected void createAction (
            JMenu menu,
            String nameOfIcon,
            ActionListener listener,
            char mnemonic,
            String description
    ) {
        makeMenuItem(menu, nameOfIcon, listener, mnemonic, description);
        makeButton(nameOfIcon, listener,mnemonic, description);
    }

    private void makeMenuItem (
            JMenu menu,
            String name,
            ActionListener listener,
            char mnemonic,
            String description
    ) {
        name = getMainName(name);
        JMenuItem item = menu.add(new JMenuItem(name));
        item.addActionListener(listener);
        item.setMnemonic(mnemonic);
        item.setToolTipText(description);
    }

    protected JMenu makeMenu (
            String name,
            char mnemonic
    ) {
        JMenu menu = new JMenu(name);
        menu.setMnemonic(mnemonic);
        menuBar.add(menu);
        return  menu;
    }

    private void makeButton (String nameOfIcon,
                             ActionListener listener,
                             char mnemonic,
                             String description
    ) {
        Icon icon = new ImageIcon(nameOfIcon);
        JButton button;
        if(icon.getIconWidth() == -1) {
            button = new JButton(getMainName(nameOfIcon));
        } else {
            button = new JButton(icon);
        }
        toolBar.add(button);
        button.addActionListener(listener);
        button.setMnemonic(mnemonic);
        button.setToolTipText(description + "(Alt + " + mnemonic + ")");
    }

    private String getMainName(String string) {
        String[] tmp = string.split("/");
        string = tmp[tmp.length - 1];
        tmp = string.split("[.]");
        return tmp[0];
    }

    protected void addSeparator () {
        toolBar.addSeparator();
    }
}
