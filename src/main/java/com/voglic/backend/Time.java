package com.voglic.backend;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Time {
    /**
     * returns the Time and Date
     * 
     * @return Date Time
     */
    public static String get() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * returns the today's date
     * 
     * @return Date
     */
    public static String getDate() {
        String[] result = get().split(" ");
        return result[0];
    }

    /**
     * returns the current Time
     * 
     * @return Time
     */
    public static String getTime() {
        String[] result = get().split(" ");
        return result[1];
    }

    public static int getTimeInt() {
        return Integer.parseInt(getTime().split(":")[0] + getTime().split(":")[1]);
    }
}
