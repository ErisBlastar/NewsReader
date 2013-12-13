package com.smapp.NewsReader.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by samarth on 13/12/13.
 */
public class DateConverterUtil {

    public static long dateConverter(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        long timestamp = 0;
        try {
            timestamp = dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        return timestamp;
    }

    public static String timeAgo(int time) {
        Unit[] units = new Unit[]
                {
                        new Unit("s", 60, 1),
                        new Unit("m", 3600, 60),
                        new Unit("h", 86400, 3600),
                        new Unit("d", 604800, 86400),
                        new Unit("w", 2629743, 604800),
                        new Unit("mo", 31556926, 2629743),
                        new Unit("y", 0, 31556926)
                };

        long currentTime = System.currentTimeMillis();
        int difference = (int) ((currentTime / 1000) - (time));

        if (difference < 5) {
            return "now";
        }

        int i = 0;
        Unit unit = null;
        while ((unit = units[i++]) != null) {
            if (difference < unit.limit || unit.limit == 0) {
                int newDiff = (int) Math.floor(difference / unit.inSeconds);
                return newDiff + "" + unit.name;
            }
        }

        return "";
    }

    static class Unit {
        public String name;
        public int limit;
        public int inSeconds;

        public Unit(String name, int limit, int inSeconds) {
            this.name = name;
            this.limit = limit;
            this.inSeconds = inSeconds;
        }
    }
}
