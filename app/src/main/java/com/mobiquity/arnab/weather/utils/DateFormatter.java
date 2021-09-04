package com.mobiquity.arnab.weather.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateFormatter {

    public static String getFormattedDate(long millisecond) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return simpleDateFormat.format(millisecond) + " - " + simpleTimeFormat.format(millisecond);
    }
}
