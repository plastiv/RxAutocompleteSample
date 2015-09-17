package com.github.plastiv.rxautocompletesample.stationstimetable.network.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CustomDatetime {
    private String tz;
    private long timestamp;

    public String toFormattedString() {
        // TODO: locale and formatter
        TimeZone timeZone = TimeZone.getTimeZone(tz);
        Date date = new Date(timestamp * 1000);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.US);
        format.setTimeZone(timeZone);
        return format.format(date);
    }

    public long getTimestamp() {
        return timestamp;
    }
}
