package logging;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MyLogger {
    public static final String WRONG_CONNECTION = "failed connect to db";
    private static final String LOGGING_FILE_NAME = "control/log.properties";

    public static Logger logger;

    static {
        try(FileInputStream inputStream = new FileInputStream(LOGGING_FILE_NAME)) {
            LogManager.getLogManager().readConfiguration(inputStream);
            logger = Logger.getLogger(MyLogger.class.getName());
        } catch (IOException e) {
            System.err.println("Impossible to open logging config file");
            System.exit(0);
        }
    }
}
