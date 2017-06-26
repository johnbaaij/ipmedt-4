package com.b_healty.john.prototype1.models;

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
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

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

    public void setElapsedDays(long elapsedDays) {
        this.elapsedDays = elapsedDays;
    }

    public long getElapsedHours() {
        return elapsedHours;
    }

    public void setElapsedHours(long elapsedHours) {
        this.elapsedHours = elapsedHours;
    }

    public long getElapsedMinutes() {
        return elapsedMinutes;
    }

    public void setElapsedMinutes(long elapsedMinutes) {
        this.elapsedMinutes = elapsedMinutes;
    }

    public long getElapsedSeconds() {
        return elapsedSeconds;
    }

    public void setElapsedSeconds(long elapsedSeconds) {
        this.elapsedSeconds = elapsedSeconds;
    }

    public long getSecondsInMilli() {
        return secondsInMilli;
    }

    public void setSecondsInMilli(long secondsInMilli) {
        this.secondsInMilli = secondsInMilli;
    }

    public long getMinutesInMilli() {
        return minutesInMilli;
    }

    public void setMinutesInMilli(long minutesInMilli) {
        this.minutesInMilli = minutesInMilli;
    }

    public long getHoursInMilli() {
        return hoursInMilli;
    }

    public void setHoursInMilli(long hoursInMilli) {
        this.hoursInMilli = hoursInMilli;
    }

    public long getDaysInMilli() {
        return daysInMilli;
    }

    public void setDaysInMilli(long daysInMilli) {
        this.daysInMilli = daysInMilli;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getDifferent() {
        return different;
    }

    public void setDifferent(long different) {
        this.different = different;
    }
}
