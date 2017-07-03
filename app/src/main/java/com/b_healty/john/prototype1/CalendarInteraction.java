package com.b_healty.john.prototype1;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.Calendar;

/**
 * Created by Ben-e on 27-6-2017.
 */

public class CalendarInteraction {
    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Events._ID,                // 0
            CalendarContract.Events.TITLE,              // 1
            CalendarContract.Events.DTSTART,            // 2
            CalendarContract.Events.DESCRIPTION         // 3
    };

    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_TITLE_INDEX = 1;
    private static final int PROJECTION_DTSTART_INDEX = 2;
    private static final int PROJECTION_DESCRIPTION_INDEX = 3;

    public final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1;
    // Bepaal de tijd op dit moment in milliseconden
    private final long calCur = Calendar.getInstance().getTimeInMillis();
    private Cursor cur;
    private ContentResolver cr;
    private Uri uri;
    private String selection;
    private String[] selectionArgs;
    private Activity activity;

    public CalendarInteraction(Activity activity) {
        this.activity = activity;
    }

    public static int getProjectionIdIndex() {
        return PROJECTION_ID_INDEX;
    }

    public static int getProjectionTitleIndex() {
        return PROJECTION_TITLE_INDEX;
    }

    public static int getProjectionDtstartIndex() {
        return PROJECTION_DTSTART_INDEX;
    }

    public static int getProjectionDescriptionIndex() {
        return PROJECTION_DESCRIPTION_INDEX;
    }

    @Nullable
    private Cursor runQuery(ContentResolver cr, Uri uri, String selection, String[] selectionArgs)
    {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            // In dit geval is er geen permissie, tijd om deze aan te vragen
            // dit gaat verder in de functie onRequestPermissionsResult
            ActivityCompat.requestPermissions(activity,
                    new String[] {Manifest.permission.READ_CALENDAR},
                    MY_PERMISSIONS_REQUEST_READ_CALENDAR);
        } else {
            // Debug thingy
            // Log.wtf("Permission is already there", "running code");

            // Nadat permissie is aangevraagd lanceren we hier de query
            cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs,
                    CalendarContract.Events.DTSTART);

            // Give back the filled cursor
            return cur;
        }
        return null;
    }

    public Cursor getData()
    {
        // Setup contentresolver
        cr = activity.getBaseContext().getContentResolver();

        // Setup uri
        uri = CalendarContract.Events.CONTENT_URI;

        // Setup select statement
        String selection = "((" + CalendarContract.Events.TITLE + " LIKE ?) AND ("
                + CalendarContract.Events.DTSTART + " > ? ))";


        String[] selectionArgs = new String[]{"%LGGYCL%", Long.toString(calCur)};

        // Run the query
        return runQuery(cr, uri, selection, selectionArgs);
    }
}
