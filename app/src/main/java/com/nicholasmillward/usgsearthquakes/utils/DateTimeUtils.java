package com.nicholasmillward.usgsearthquakes.utils;

import android.text.format.DateUtils;

/**
 * Created by nmillward on 6/22/18.
 */

public class DateTimeUtils {

    public static String timestampToRelativeTime(long time) {

        return DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS).toString();

    }

}
