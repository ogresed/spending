package table;

import java.sql.SQLException;

@FunctionalInterface
public interface OutputMessage {
    String output() throws SQLException;
}
