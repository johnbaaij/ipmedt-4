package com.b_healty.john.prototype1;

import java.util.Date;

/**
 * Created by Ben-e on 26-6-2017.
 */

public class CalculateDifference {
    private long elapsedDays,
                elapsedHours,
                elapsedMinutes,
                elapsedSeconds;

    private long secondsInMilli,
                 minutesInMilli,
                 hoursInMilli,
                 daysInMilli;

    private Date startDate;
    private Date endDate;
    private long different;


    public CalculateDifference(Date startDate, Date endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void controlDiff()
    {
        //milliseconds
        different = endDate.getTime() - startDate.getTime();

        secondsInMilli = 1000;
        minutesInMilli = secondsInMilli * 60;
        hoursInMilli = minutesInMilli * 60;
        daysInMilli = hoursInMilli * 24;

        elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        elapsedSeconds = different / secondsInMilli;
    }

    public long getElapsedDays() {
        return elapsedDays;
    }

    public long getElapsedHours() {
        return elapsedHours;
    }

    public long getElapsedMinutes() {
        return elapsedMinutes;
    }

    public long getElapsedSeconds() {
        return elapsedSeconds;
    }

}
