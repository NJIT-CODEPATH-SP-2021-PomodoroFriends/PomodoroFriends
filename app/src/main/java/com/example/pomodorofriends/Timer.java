package com.example.pomodorofriends;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Timer")
public class Timer extends ParseObject {
    public static final String KEY_CAPTION = "caption";
    public static final String KEY_PERIOD = "period";
    public static final String KEY_ACTIVITY = "activityTimer";
    public static final String KEY_BREAK = "breakTimer";
    public static final String KEY_USER = "author";
    public static final String KEY_CREATED_AT = "createdAt";

    public String getCaption() {
        return getString(KEY_CAPTION);
    }
    public void setCaption(String caption) { put(KEY_CAPTION, caption); }

    public int getPeriod() {
        return getInt(KEY_PERIOD);
    }
    public void setPeriod(int period) {
        put(KEY_PERIOD, period);
    }

    public int getActivityTimer() {
        return getInt(KEY_ACTIVITY);
    }
    public void setActivityTimer(int activityTimer) {
        put(KEY_ACTIVITY, activityTimer);
    }

    public int getBreakTimer() {
        return getInt(KEY_BREAK);
    }
    public void setBreakTimer(int breakTimer) {
        put(KEY_BREAK, breakTimer);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public static String format(long seconds){
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, secs );
        return time;
    }
}
