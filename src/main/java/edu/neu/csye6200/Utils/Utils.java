package edu.neu.csye6200.Utils;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * @author SaiAkhil
 */
public class Utils {
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


    public static int parseInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean isDateValid(String text, boolean isDob) {
        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            df.setLenient(false);
            Date current = df.parse(text);
            if (isDob) {
                Date today = new Date();
                return current.getTime() < today.getTime();
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Function<Date, String> GET_DATE_STRING = (date) -> {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    };

    public static Date getDateFromString(String text) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return dateFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Function<String, Integer> GET_AGE_FROM_DOB = (date) -> {
        LocalDate today = LocalDate.now();
        Date bDate = getDateFromString(date);
        Calendar calendar = Calendar.getInstance();
        assert bDate != null;
        calendar.setTime(bDate);
        LocalDate birthday = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        Period p = Period.between(birthday, today);
        return p.getYears();
    };

    public static Function<String, String> GENERATE_PASSWORD = (string) -> {
        if (string == null) return UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(string.trim().charAt(random.nextInt(string.trim()
                    .length())));
        }
        return sb.toString();
    };
}
