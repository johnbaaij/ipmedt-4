package com.b_healty.john.prototype1.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.b_healty.john.prototype1.GridSpacingItemDecoration;
import com.b_healty.john.prototype1.GridSpacingItemDecoration;
import com.b_healty.john.prototype1.MyRecyclerViewAdapter;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.CalculateDifference;
import com.b_healty.john.prototype1.models.Card;


import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by John on 06/06/2017.
 */

public class Home extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private Activity activity;
    private ArrayList results = new ArrayList<Card>();

    //topCard
    String topCardTitle;
    String topCardText;
    int topCardImage;

    //leftCard
    String leftCardTitle;
    String leftCardText;
    int leftCardImage;

    //leftCard
    String rightCardTitle;
    String rightCardText;
    int rightCardImage;

    private StaggeredGridLayoutManager sGridLayoutManager;


    // Dit is de projection array. Hierin is vastgelegd welke kolommen uit
    // de database opgevraagd worden met de selection query
    public static final String[] EVENT_PROJECTION = new String[] {
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
    public final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);


        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        //RecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10),



        activity = getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setHasFixedSize(true);
        int spanCount = 2; // 3 columns



        GridLayoutManager llm = new GridLayoutManager(view.getContext(), spanCount);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),
                llm.getOrientation());

        int spacingInPixels = 0;


        int spacing = 50; // 50px
        boolean includeEdge = true;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));



        mRecyclerView.setLayoutManager(llm);







        //mLayoutManager = new LinearLayoutManager(activity);
        //RecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new MyRecyclerViewAdapter.GridSpacingItemDecoration(2, dpToPx(10), true));

        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());



        //topCard
        Card topCard = new Card("top", "top" ,1, true);

        //addCardToArray("Geen", "idee", 0);

        //leftCard
        Card leftCard = new Card("left", "left" ,1, false);

        //rightCard
        Card rightCard = new Card("right", "right",2, false);


        Card bonus = new Card("right", "right",2, false);



        results.add(0, topCard);
        results.add(1, leftCard);
        results.add(2, rightCard);
        results.add(3, bonus);



        mAdapter = new MyRecyclerViewAdapter(results);
        //((MyRecyclerViewAdapter) mAdapter).addItem(leftCard, 1);
        //((MyRecyclerViewAdapter) mAdapter).addItem(topCard, 2, true);

        //mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setAdapter(mAdapter);

        /**
         * Vanaf dit punt komt code om de afspraken uit de android kalender op te halen
         *                                                                  - Ben
         */
//        // Maak alvast een cursor aan en start de ContentResolver
//        Cursor cur = null;
//
//        // De contentResolver is hetgene wat zal de interactie lever tussen
//        // onze app en de android kalender
//        ContentResolver cr = activity.getContentResolver();
//
//        // De android kalender is zelf een 'content provider', dus heeft deze een adres
//        // en dat adres kunnen we benaderen met een URI
//        Uri uri = CalendarContract.Events.CONTENT_URI;
//
//        // Dit is het SELECT statement voor de kalender. Op het vraagtekentje komt een
//        // variabele te staan die in selectionArgs wordt aangemaakt
//        String selection = "(" + CalendarContract.Events.TITLE + " LIKE ?)";
//        String[] selectionArgs = new String[] {"%LGGYCL%"};
//
//        // Controleer of deze app permissie heeft om te lezen van de kalender
//        if (ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.READ_CALENDAR)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // In dit geval is er geen permissie, tijd om deze aan te vragen
//            // dit gaat verder in de functie onRequestPermissionsResult
//            ActivityCompat.requestPermissions(activity,
//                    new String[] {Manifest.permission.READ_CALENDAR},
//                    MY_PERMISSIONS_REQUEST_READ_CALENDAR);
//        }
//
//        // Nadat permissie is aangevraagd lanceren we hier de query
//        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs,
//                CalendarContract.Events.DTSTART + " ASC " + " LIMIT 1");
//
//        if (cur != null) {
//            while (cur.moveToNext()) {
//                // Maak variabelen aan die de data uit de kalender in zich kunnen nemen
//                String evtTitle = null;
//                String evtDescription = null;
//                String evtDTStart = null;
//
//                // Stop de de data van de kalender in de variabelen
//                evtTitle = cur.getString(PROJECTION_TITLE_INDEX);
//                evtDescription = cur.getString(PROJECTION_DESCRIPTION_INDEX);
//                evtDTStart = cur.getString(PROJECTION_DTSTART_INDEX);
//
//                Calendar calCurr = Calendar.getInstance();
//                Calendar calNext = Calendar.getInstance();
//                calNext.setTimeInMillis(Long.parseLong(evtDTStart));
//
//                // Roep de klasse CalculateDiff aan die het verschil tussen nu
//                // en de datum van de eerstvolgende afspraak zal berekenen
//                CalculateDifference callDiff =
//                        new CalculateDifference(calCurr.getTime(), calNext.getTime());
//
//                // Start de berekening
//                callDiff.controlDiff();
//
//                // Bouw een string met daarin de data van de berekening
//                // Deze data kan dus ook los gebruikt worden!
//                String daysToCome = "Nog " + callDiff.getElapsedDays()
//                        + " dagen, " + callDiff.getElapsedHours()
//                        + " uur, " + callDiff.getElapsedMinutes()
//                        + " minuten en " + callDiff.getElapsedSeconds()
//                        + " seconden tot de volgende afspraak!";
//
//
//                Log.wtf("Titel", evtTitle);
//                Log.wtf("Omschrijving ", evtDescription);
//                Log.wtf("Datum ", daysToCome);
//            }
//
//            cur.close();
//        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {

        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALENDAR : {
                // If request is cancelled, the result arrays are empty
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted, yay!
                    // Now you can read from the calendar

                } else {

                    // Permission was denied, you have to disable
                    // the functionality that relies on this permission

                }
                return;
            }

            // Put other 'case' lines here if there are more
            // permissions that need to be approved
        }

    }
}

