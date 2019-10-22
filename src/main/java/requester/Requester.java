package requester;

import java.sql.*;
import java.util.Properties;

public class Requester {
    private static Statement stmt;
    public Requester (String URL, Properties properties) {
        try {
            Connection con = DriverManager.getConnection(URL, properties);
            stmt = con.createStatement();
            stmt.executeQuery("describe spending;");

            System.out.println();
        } catch (SQLException e) {
            System.out.println("Database access error");
        }
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
            System.out.println("Database access error");
        }
        return null;
    }
}
