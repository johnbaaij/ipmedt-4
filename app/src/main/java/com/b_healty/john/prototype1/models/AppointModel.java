package com.b_healty.john.prototype1.models;

/**
 * Created by Ben on 27/06/2017.
 */

public class AppointModel {
    private long eventID;
    private String time;
    private String date;
    private String appointName;
    private String wardName;
    private String doctorName;

    public AppointModel()
    {
        super();
    }

    public AppointModel(long id, String time, String date, String appointName,
                        String wardName, String doctorName)
    {
        super();
        this.eventID = id;
        this.time = time;
        this.date = date;
        this.appointName = appointName;
        this.wardName = wardName;
        this.doctorName = doctorName;
    }

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppointName() {
        return appointName;
    }

    public void setAppointName(String appointName) {
        this.appointName = appointName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
