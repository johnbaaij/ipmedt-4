package com.b_healty.john.prototype1.dbhelpers;

import android.database.Cursor;
import android.text.format.DateFormat;

import com.b_healty.john.prototype1.models.AppointModel;

import java.util.Calendar;

/**
 * Created by John on 04/07/2017.
 */

public class AppointmentGetter {

    public AppointmentGetter(){

    }

    public AppointModel getData(Cursor data){


        long evtId;
        long evtDtStart;
        String evtTitle;
        String evtDescription;

        // Put the data retrieved from the calendar in the variables
        evtId = data.getLong(CalendarInteraction.getProjectionIdIndex());
        evtDtStart = data.getLong(CalendarInteraction.getProjectionDtstartIndex());
        evtTitle = data.getString(CalendarInteraction.getProjectionTitleIndex());
        evtDescription = data.getString(CalendarInteraction.getProjectionDescriptionIndex());

        // Create new instance of the appointment model
        AppointModel appointModel = new AppointModel();

        // Fill 'er up
        appointModel.setEventID(evtId);
        appointModel.setTime(getTime(evtDtStart));
        appointModel.setDate(getDate(evtDtStart));
        appointModel.setAppointName(getTitle(evtTitle));
        appointModel.setWardName(getWard(evtDescription));
        appointModel.setDoctorName(getDoctor(evtDescription));

        return appointModel;
    }



    // Take the amount of milliseconds passed since 1 January 1970 and convert it
    // to a normal human-readable date
    private String getDate(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        return DateFormat.format("dd/MM/yyyy", cal).toString();
    }


    // Take the amount of milliseconds passed since 1 January 1970 and convert it
    // to a normal human-readable time
    private String getTime(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        return DateFormat.format("HH:mm", cal).toString();
    }


    // Split the string for the title
    private String getTitle(String title) {
        return title.split(" - ")[0];
    }


    // Split the description and return only the part containing
    // the name of the ward
    private String getWard(String description) {
        return description.split(" - ")[1];
    }


    // Split the description and return only the part containing
    // the name of the doctor
    private String getDoctor(String description) {
        return description.split(" - ")[0];
    }
}
