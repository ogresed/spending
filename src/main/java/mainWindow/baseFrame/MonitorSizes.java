package mainWindow.baseFrame;

import java.awt.*;

public class MonitorSizes {
    public static int WIDTH_OF_MONITOR;
    public static int HEIGHT_OF_MONITOR;

    static  {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        WIDTH_OF_MONITOR = dimension.width;
        HEIGHT_OF_MONITOR = dimension.height;
    }
}
