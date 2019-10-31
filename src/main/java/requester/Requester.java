package requester;

import logging.LoggingConst;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Requester implements LoggingConst {
    private static Statement stmt;
    private Connection con;
    private String URL;
    private Properties properties;
    private static Logger logger;

    static {
        try(FileInputStream inputStream = new FileInputStream(LOGGING_FILE_NAME)) {
            LogManager.getLogManager().readConfiguration(inputStream);
            logger = Logger.getLogger(Requester.class.getName());
        } catch (IOException e) {
            System.err.println("Impossible to open logging config file");
            System.exit(0);
        }
    }

    public Requester (String URL, Properties properties) throws SQLException {
        this.URL = URL;
        this.properties = properties;

        con = DriverManager.getConnection(URL, properties);

        stmt = con.createStatement();
        stmt.executeQuery("describe spending;");
    }

    public HashMap<String, ArrayList<String>> getMeta() {
        var retMap = new HashMap<String, ArrayList<String>>();
        try {
            var metaData = con.getMetaData();
            var tmp = URL.split("/");
            var nameOfDB = tmp[tmp.length - 1];
            var tables = metaData.getTables(nameOfDB, null, "%", null);
            while (tables.next()) {
                var nameOfTable = tables.getString("TABLE_NAME");
                var columns =  metaData.getColumns(null, null,
                        nameOfTable, null);
                var arrayListTmp = new ArrayList<String>();
                while (columns.next()) {
                    arrayListTmp.add(columns.getString("COLUMN_NAME"));
                }
                retMap.put(nameOfTable, arrayListTmp);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, LoggingConst.WRONG_CONNECTION, e);
            return null;
        }
        return retMap;
    }

    private void insert(String insertQuery) {
        try {
            stmt.executeUpdate(insertQuery);
        } catch (SQLException e) {
            System.out.println("Database access error");
        }
    }

    public ResultSet getRecords (String query) {
        try {
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Database `access error");
        }
        return null;
    }
}
