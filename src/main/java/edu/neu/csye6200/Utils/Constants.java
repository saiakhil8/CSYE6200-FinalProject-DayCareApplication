package edu.neu.csye6200.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;
import java.util.regex.Pattern;

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

    public static Function<String, Boolean> VALIDATE_EMAIL_ADDRESS = (emailId) -> {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        return pattern.matcher(emailId).matches();
    };

    public static JDialog geLoadingDialog(String message) {
        JDialog downloadingDialog = new JDialog((JFrame) null, "Please Wait..", false);
        downloadingDialog.setLayout(new FlowLayout(FlowLayout.CENTER));
        downloadingDialog.setUndecorated(true);
        Icon myImgIcon = new ImageIcon("./src/main/resources/loading-gif.gif");
        JLabel imageLbl = new JLabel(myImgIcon);
        downloadingDialog.add(imageLbl, BorderLayout.CENTER);
        downloadingDialog.setSize(400, 400);
        downloadingDialog.setVisible(true);
        return downloadingDialog;
    }

    ;

}
