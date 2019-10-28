package dbConnectWindow;

import mainWindow.baseFrame.MonitorSizes;
import requester.Requester;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Properties;

public class DBConnectWindow extends JFrame {
    private JTextField URLField;
    private JTextField userField;
    private JPasswordField passwordField;
    private JTextField encodingField;
    private JButton connectButton;

    public DBConnectWindow() {
        setBaseSettings();
        //create components
        URLField = new JTextField();
        userField = new JTextField();
        encodingField = new JTextField();
        passwordField = new JPasswordField();
        connectButton = new JButton("Connect");
        connectButton.addActionListener(e -> {
            //get dw
        });

        revalidate();
    }

    private DataWrapper createDataWrapper() {
        Properties properties = new Properties();
        properties.setProperty("user", userField.getText());
        properties.setProperty("characterEncoding", encodingField.getText());
        properties.setProperty("password", new String(passwordField.getPassword()));
        return new DataWrapper(properties, URLField.getText());
    }

    private void setBaseSettings() {
        setLayout(new GridLayout(4, 1));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int startWidth = (MonitorSizes.WIDTH_OF_MONITOR) / 2;
        int startHeight = 2 * (MonitorSizes.HEIGHT_OF_MONITOR) / 5;
        setBounds((MonitorSizes.WIDTH_OF_MONITOR - startWidth)/2, (MonitorSizes.HEIGHT_OF_MONITOR - startHeight)/2,
                startWidth, startHeight);
    }
}
