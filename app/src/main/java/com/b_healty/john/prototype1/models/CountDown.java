package com.b_healty.john.prototype1.models;

/**
 * Created by John on 29/06/2017.
 */

public class CountDown {

    long days;
    long hours;
    long minutes;
    long seconds;
    String title;
    String description;
    String date;
    String daysToCome;

    public String getDaysToCome() {
        return daysToCome;
    }

    public void setDaysToCome(String daysToCome) {
        this.daysToCome = daysToCome;
    }

    public CountDown(long days, long hours, long minutes, long seconds, String title, String description, String date, String daysToCome) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.title = title;
        this.description = description;
        this.date = date;
        this.daysToCome = daysToCome;


    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }
}
