import dbConnectWindow.DBConnectWindow;
import requester.Requester;

import java.util.Properties;

public class Main {
    /*private static final String URL = "jdbc:mysql://localhost/Spending";
    private static final String user = "sergey";
    private static final String password = "12345";
    private static final String encoding = "cp1251";*/
    public static void main(String[] args) {
       /* var properties = new Properties();
        properties.setProperty("user",user);
        properties.setProperty("password",password);
        properties.setProperty("characterEncoding",encoding);
        var requester = new Requester(URL, properties);*/
        //new MainWindow(requester);
        //new TableLoader("description/info");
        new DBConnectWindow();
    }
}
