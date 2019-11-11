package dbConnectWindow;

import dbConnectWindow.nfield.NPasswordField;
import dbConnectWindow.nfield.NTextField;
import mainWindow.MainWindow;
import mainWindow.baseFrame.MonitorSizes;
import mainWindow.baseFrame.StatusBar;
import requester.Requester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;

import static logging.MyLogger.WRONG_CONNECTION;
import static logging.MyLogger.logger;

public class DBConnectWindow extends JFrame {
    private static final String PATH_OF_SETTINGS = "users/user_settings";
    private HashSet<String> urlSet = new HashSet<>();
    private HashSet<String> userSet = new HashSet<>();
    private HashSet<String> encodingSet = new HashSet<>();
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

    private void setBaseSettings() {
        //setLayout(new GridLayout(5, 1));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int startWidth = (MonitorSizes.WIDTH_OF_MONITOR) / 4;
        int startHeight = 3 * (MonitorSizes.HEIGHT_OF_MONITOR) / 5;
        setBounds((MonitorSizes.WIDTH_OF_MONITOR - startWidth)/2,
                (MonitorSizes.HEIGHT_OF_MONITOR - startHeight)/2,
                startWidth, startHeight);
    }

    private void createComponents() {
        URLPanel = new NTextField("URL");
        userPanel = new NTextField("user");
        passwordPanel = new NPasswordField("password");
        encodingPanel = new NTextField("encoding");
        JButton connectButton = new JButton("Connect");
        connectButton.doClick();

        connectButton.addActionListener(e -> toClickOnConnectButton());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(connectButton);
        statusBar = new StatusBar(100, 16);

        passwordPanel.addButtonTypedListener( e -> {
            if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                toClickOnConnectButton();
            }
        });

        connectPanel = new JPanel();
        connectPanel.setLayout(new GridLayout(2, 1));
        connectPanel.add(buttonPanel, BorderLayout.NORTH);
        connectPanel.add(statusBar, BorderLayout.SOUTH);
    }

    private void toClickOnConnectButton() {
        var data = getSettings();
        Requester requester;
        try {
            requester = new Requester(data.getSecondObject(), data.getFirstObject());
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "Database access error", ex);
            statusBar.setMessage(WRONG_CONNECTION);
            return;
        }
        String newRecord = createSettingsStringIfItIsNew();
        if(newRecord != null) {
            try {
                var writer = new FileWriter(settingFile, true);
                var bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(newRecord);
                bufferWriter.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "an I/O error occurred", e);
            }
        }
        new MainWindow(requester);
        setVisible(false);
    }

    private void loadSettingsIfItExist() {
        settingFile = new File(PATH_OF_SETTINGS);
        if(!settingFile.exists()) {
            try {
                boolean created = settingFile.createNewFile();
                if (!created) {
                    logger.log(Level.WARNING, "failed to create a file " +
                            "with will store a records of settings of users");
                }
            } catch (IOException e) {
                logger.log(Level.WARNING, "an I/O error occurred", e);
            }
        } else {
            loadSettingsFromFile(settingFile);
        }
    }

    private void loadSettingsFromFile(File settingFile) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(settingFile));
            urlSet = new HashSet<>();
            userSet = new HashSet<>();
            encodingSet = new HashSet<>();
            while (scanner.hasNext()) {
                String record = scanner.nextLine();
                String[] separatedRecord = record.split("-");
                String connectingInfo = separatedRecord[0];
                String[] separatedConnInfo = connectingInfo.split(" ");
                urlSet.add(separatedConnInfo[0]);
                userSet.add(separatedConnInfo[1]);
                encodingSet.add(separatedConnInfo[2]);
            }
            URLPanel.addItems(urlSet);
            userPanel.addItems(userSet);
            encodingPanel.addItems(encodingSet);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File with users settings not found", e);
        }
    }

    private DataWrapper<Properties, String> getSettings() {
        Properties properties = new Properties();
        properties.setProperty("user", userPanel.getText());
        properties.setProperty("characterEncoding", encodingPanel.getText());
        properties.setProperty("password", (passwordPanel.getText()));
        return new DataWrapper<>(properties, URLPanel.getText());
    }

    private String createSettingsStringIfItIsNew() {
        String url = URLPanel.getText() ;
        String user = userPanel.getText();
        String encoding = encodingPanel.getText();
        if(urlSet.add(url) || userSet.add(user)) {
            return url + " " + user + " " + encoding;
        } else
            return null;
    }

    private void addComponents() {
        add(URLPanel);
        add(userPanel);
        add(passwordPanel);
        add(encodingPanel);
        add(connectPanel);
        add(connectPanel);
    }
}
