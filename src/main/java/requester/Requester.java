package requester;

import logging.MyLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import static logging.MyLogger.WRONG_CONNECTION;
import static logging.MyLogger.logger;

public class Requester {
    private static Statement stmt;
    private Connection con;
    private String URL;
    private Properties properties;

    public Requester (String URL, Properties properties) throws SQLException {
        this.URL = URL;
        this.properties = properties;

        con = DriverManager.getConnection(URL, properties);

        stmt = con.createStatement();
        stmt.executeQuery("describe spending;");
    }
/**
 * return a map where
 * name of table is key and
 * list of attributes is values
 * */
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
            logger.log(Level.WARNING, WRONG_CONNECTION, e);
            return null;
        }
        return retMap;
    }

    public void insert(String insertQuery) {
        try {
            stmt.executeUpdate(insertQuery);
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage(), "Database access error");
        }
    }

    public ResultSet getRecords (String query) {
        try {
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage(), "Database access error");
        }
        return null;
    }
}
