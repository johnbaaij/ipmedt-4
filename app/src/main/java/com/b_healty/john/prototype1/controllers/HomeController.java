package com.b_healty.john.prototype1.controllers;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.b_healty.john.prototype1.CalculateDifference;
import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.CountDown;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by John on 14/06/2017.
 */

public class HomeController {
    String[] questionList = MainActivity.resources.getStringArray(R.array.questions);
    String[] greetings = MainActivity.resources.getStringArray(R.array.greetings);

    private long days;
    private long hours;
    private long minutes;
    private long seconds;
    private String title;
    private String description;
    private String date;
    private String daysToCome;




    // Dit is de projection array. Hierin is vastgelegd welke kolommen uit
    // de database opgevraagd worden met de selection query
    private static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Events.TITLE,          // 0
            CalendarContract.Events.DESCRIPTION,    // 1
            CalendarContract.Events.DTSTART         // 2
    };

    // Elke kolom heeft zijn eigen plaats in de array, hier worden die
    // plaatsen vastgelegd
    private static final int PROJECTION_TITLE_INDEX = 0;
    private static final int PROJECTION_DESCRIPTION_INDEX = 1;
    private static final int PROJECTION_DTSTART_INDEX = 2;

    // Permission string
    private final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1;

    public String generateHotTopic(int number) {
        String hotTopic = questionList[number];
        return hotTopic;
    }

    public int generateRandomValue() {

        int random = new Random().nextInt(questionList.length);
        return random;

    }


    public String generateGreeting(String name) {
        int random = new Random().nextInt(greetings.length);
        String greeting = greetings[random] + " " + name;
        return greeting;
    }

    public CountDown generateTimeStamp(Activity activity) {

        /**
         * Vanaf dit punt komt code om de afspraken uit de android kalender op te halen
         *                                                                  - Ben
         */
        // Maak alvast een cursor aan en start de ContentResolver
        Cursor cur = null;

        // De contentResolver is hetgene wat zal de interactie lever tussen
        // onze app en de android kalender
        ContentResolver cr = activity.getContentResolver();


        // De android kalender is zelf een 'content provider', dus heeft deze een adres
        // en dat adres kunnen we benaderen met een URI
        Uri uri = CalendarContract.Events.CONTENT_URI;


        // Dit is het SELECT statement voor de kalender. Op het vraagtekentje komt een
        // variabele te staan die in selectionArgs wordt aangemaakt
        String selection = "(" + CalendarContract.Events.TITLE + " LIKE ?)";
        String[] selectionArgs = new String[]{"%LGGYCL%"};


        // Controleer of deze app permissie heeft om te lezen van de kalender
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            // In dit geval is er geen permissie, tijd om deze aan te vragen
            // dit gaat verder in de functie onRequestPermissionsResult
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_CALENDAR},
                    MY_PERMISSIONS_REQUEST_READ_CALENDAR);
        } else {
            Log.wtf("Permission is already there", "running code");

            // Nadat permissie is aangevraagd lanceren we hier de query
            cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs,
                    CalendarContract.Events.DTSTART + " ASC " + " LIMIT 1");


            if (cur != null) {
                while (cur.moveToNext()) {
                    // Maak variabelen aan die de data uit de kalender in zich kunnen nemen
                    String evtTitle = null;
                    String evtDescription = null;
                    String evtDTStart = null;

                    // Stop de de data van de kalender in de variabelen
                    title = cur.getString(PROJECTION_TITLE_INDEX);
                    description = cur.getString(PROJECTION_DESCRIPTION_INDEX);
                    evtDTStart = cur.getString(PROJECTION_DTSTART_INDEX);

                    Calendar calCurr = Calendar.getInstance();
                    Calendar calNext = Calendar.getInstance();
                    calNext.setTimeInMillis(Long.parseLong(evtDTStart));


                    // Roep de klasse CalculateDiff aan die het verschil tussen nu
                    // en de datum van de eerstvolgende afspraak zal berekenen
                    CalculateDifference callDiff =
                            new CalculateDifference(calCurr.getTime(), calNext.getTime());


                    // Start de berekening
                    callDiff.controlDiff();

                    days = callDiff.getElapsedDays();
                    hours = callDiff.getElapsedHours();
                    minutes = callDiff.getElapsedMinutes();

                    // Bouw een string met daarin de data van de berekening
                    // Deze data kan dus ook los gebruikt worden!

                    if (days == 1){
                        daysToCome = "Nog " + days
                                + " dag, " + hours
                                + " uur en " + minutes
                                + " minuten tot de volgende afspraak!";
                    }
                    else{
                        daysToCome = "Nog " + days
                                + " dagen, " + hours
                                + " uur en " + minutes
                                + " minuten tot de volgende afspraak!";

                    }
                    }

                // Sluit de cursor weer af
                cur.close();
            }

            else{
                cur.close();
            }
        }

        if (daysToCome == null){
            Log.d("days", "null");
            daysToCome ="Geen afspraken toegevoegd. Klik hier om een afspraak te maken";
        }
        CountDown countDown = new CountDown(days,hours, minutes,seconds, title, description, date, daysToCome );
        return countDown;

    }
}
