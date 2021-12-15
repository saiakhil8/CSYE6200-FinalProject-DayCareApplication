package edu.neu.csye6200.Utils;

/**
 * @author SaiAkhil
 */
public class Constants {

    public static String APP_NAME = "Daycare Application";

    public static int APP_PREFERRED_WIDTH = 1024;
    public static int APP_PREFERRED_HEIGHT = 2048;

    public static int APP_DEFAULT_BACKGROUND_TYPE = 0;
    public static String APP_BACKGROUND = "#0391F6";

    public static final int EVENT_NEXT_SCREEN = 7001;
    public static final int EVENT_LOGOUT = 7099;

    public static final int SESSION_INVALID = 5003;
    public static final int SESSION_ADMIN = 5012;
    public static final int SESSION_TEACHER = 5021;
    public static final int SESSION_PARENT = 5030;
    public static final int SESSION_FAILED_AUTH = 5004;

    private static final String APP_DEFAULT_RESOURCES_PATH = "./src/main/resources/";
    private static final String APP_ICON_DEFAULT_PATH = APP_DEFAULT_RESOURCES_PATH + "icons/";

    /**
     * Generate and return default icon path
     *
     * @param name of the icon required
     * @return complete path of the icon that can be used to generate ImageIcon
     */
    public static String getIconPathFromName(String name) {
        return APP_ICON_DEFAULT_PATH + name;
    }

    /**
     * Generate and return default image path
     *
     * @param name of the image required
     * @return complete path of the image that can be used to generate BG
     */
    public static String getImageFromName(String name) {
        return APP_DEFAULT_RESOURCES_PATH + name;
    }

}
