package com.b_healty.john.prototype1.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 06/06/2017.
 */

public class Calendar extends Fragment {

    // TODO: 18-6-2017 Er moeten nog animaties gemaakt worden voor de progressbar
    // TODO: 18-6-2017 Voor de tijd input en de datum input gestroomlijnde manieren
    // TODO: 18-6-2017 Alle front end stuff moet gekoppeld worden aan een back-end

    FragmentActivity listener;
    View view;
    Activity activity;

    EditText appointName;
    EditText inputTime;
    EditText inputDate;
    EditText wardName;
    EditText doctorName;
    Intent startCalendar;
    int progressCount;


    // This method is called when a fragment instance is associated with an activity and will run
    // before anything else
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }


    // This method is called when the fragment instance is being created, or re-created
    // use this for any standard setup that does not require the activity to be fully
    // created
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        progressCount = 0;

//        java.util.Calendar beginTime = java.util.Calendar.getInstance();
//        beginTime.set(2017, 4, 18, 4, 30);
//
//        java.util.Calendar endTime = java.util.Calendar.getInstance();
//        endTime.set(2017, 4, 18, 6, 50);
//
//        startCalendar = new Intent(Intent.ACTION_EDIT);
//        startCalendar.setType("vnd.android.cursor.item/event");
//        startCalendar.putExtra("title", "een titel");
//        startCalendar.putExtra("description", "ik schrijf om");
//        startCalendar.putExtra("beginTime", beginTime.getTimeInMillis());
//        startCalendar.putExtra("endTime", endTime.getTimeInMillis());
    }


    // This method is called when the fragment builds its views, ether from an XML layout or
    // dynamically
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity();
        view = inflater.inflate(R.layout.calendar_layout, container, false);

        return view;
    }


    // This method is called shortly after onCreateView() and is only called when onCreateView()
    // doesn't return a null object. Things like view lookups and attaching listeners should
    // happen in this method
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Button sendButton = (Button) view.findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // startActivity(startCalendar);

            }
        });
    }


    // This method is called when the fragment is no longer attached to the activity and any
    // references should be destroyed here to prevent memory leaks
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }


    // This method is called after the parent activities' onCreate() method has completed
    // If you want to access a view inside the parent you have to do that via this method
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
