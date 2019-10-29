package dbConnectWindow;

import com.mysql.cj.jdbc.exceptions.MySQLStatementCancelledException;
import dbConnectWindow.nfield.NPasswordField;
import dbConnectWindow.nfield.NTextField;
import mainWindow.baseFrame.MonitorSizes;
import mainWindow.baseFrame.StatusBar;
import requester.Requester;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DBConnectWindow extends JFrame {
    private static final String WRONG_CONNECTION = "Database access error";
    private static final String PATH_OF_SETTINGS = "settings";
    private File settingFile;
    private NTextField URLPanel = new NTextField("URL");
    private NTextField userPanel = new NTextField("user");
    private NPasswordField passwordPanel = new NPasswordField("password");
    private NTextField encodingPanel = new NTextField("encoding");
    private JPanel connectPanel;
    private StatusBar statusBar;

    public DBConnectWindow() {
        setBaseSettings();
        createComponents();
        loadSettingsIfItExist();
        addComponents();
        revalidate();
    }

    private void loadSettingsIfItExist() {
        settingFile = new File(PATH_OF_SETTINGS);
        if(!settingFile.exists()) {
            try {
                settingFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else {
            loadSettingsFromFile(settingFile);
        }
    }

    private void loadSettingsFromFile(File settingFile) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(settingFile));
            String string = scanner.nextLine();
            String[] parts = string.split(" ");
            URLPanel.setText(parts[0]);
            userPanel.setText(parts[1]);
            encodingPanel.setText(parts[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addComponents() {
        add(URLPanel);
        add(userPanel);
        add(passwordPanel);
        add(encodingPanel);
        add(connectPanel);
        //add(statusBar);
    }

    private void createComponents() {
        URLPanel = new NTextField("URL");
        userPanel = new NTextField("user");
        passwordPanel = new NPasswordField("password");
        encodingPanel = new NTextField("encoding");
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(e -> {
            DataWrapper data = getSettings();
            try {
                Requester requester = new Requester(data.getURL(), data.getProperties());
            } catch (SQLException ex) {
                statusBar.setMessage(WRONG_CONNECTION);
                 return;
            }

            try {
                PrintWriter writer = new PrintWriter(settingFile);
                writer.write(createSettingsString());
                writer.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            setVisible(false);
        });
        connectPanel = new JPanel();
        connectPanel.setLayout(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(connectButton);
        statusBar = new StatusBar(100, 16);
        connectPanel.add(buttonPanel, BorderLayout.NORTH);
        connectPanel.add(statusBar, BorderLayout.SOUTH);
    }

    private String createSettingsString() {
        return URLPanel.getText() + " "+ userPanel.getText() + " " + encodingPanel.getText();
    }

    private DataWrapper getSettings() {
        Properties properties = new Properties();
        properties.setProperty("user", userPanel.getText());
        properties.setProperty("characterEncoding", encodingPanel.getText());
        properties.setProperty("password", (passwordPanel.getText()));
        return new DataWrapper(properties, URLPanel.getText());
    }

    private void setBaseSettings() {
        setLayout(new GridLayout(5, 1));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int startWidth = (MonitorSizes.WIDTH_OF_MONITOR) / 4;
        int startHeight = 3 * (MonitorSizes.HEIGHT_OF_MONITOR) / 5;
        setBounds((MonitorSizes.WIDTH_OF_MONITOR - startWidth)/2, (MonitorSizes.HEIGHT_OF_MONITOR - startHeight)/2,
                startWidth, startHeight);
    }
}
