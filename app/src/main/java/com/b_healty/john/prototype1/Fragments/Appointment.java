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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by John on 06/06/2017.
 */

public class Appointment extends Fragment {

    // TODO: 18-6-2017 Er moeten nog animaties gemaakt worden voor de progressbar
    // TODO: 18-6-2017 Alle front end stuff moet gekoppeld worden aan een back-end

    FragmentActivity listener;
    View view;
    Activity activity;
    int progressCount;

    private EditText appointName;
    private EditText inputTime;
    private EditText inputDate;
    private EditText wardName;
    private EditText doctorName;
    private Date datum;
    private boolean controlBit;

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
    public void onViewCreated(View view, final Bundle savedInstanceState) {

        final Button sendButton = (Button) view.findViewById(R.id.sendButton);

        appointName = (EditText) view.findViewById(R.id.appointName);
        inputTime = (EditText) view.findViewById(R.id.inputTime);
        inputDate = (EditText) view.findViewById(R.id.inputDate);
        wardName = (EditText) view.findViewById(R.id.wardName);
        doctorName = (EditText) view.findViewById(R.id.doctorName);

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                controlBit = true;
                Intent startCal = new Intent(Intent.ACTION_EDIT);
                startCal.setType("vnd.android.cursor.item/event");

                if (!appointName.getText().toString().equals("")) {
                    startCal.putExtra("title", appointName.getText().toString());

                } else {
                    appointName.setError("Geef de afspraak een naam");
                    controlBit = false;
                }


                if (!inputTime.getText().toString().equals("")) {
                    // Nothing here
                } else {
                    inputTime.setError("Voer een kloppende tijd in");
                    controlBit = false;
                }


                if (!inputDate.getText().toString().equals("")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/ddhh:mm");

                    try {
                        datum = dateFormat.parse(inputDate.getText().toString() + inputTime.getText().toString());
                        // Toast.makeText(activity, datum.toString(), Toast.LENGTH_LONG).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(datum.getYear(), datum.getMonth(), datum.getDay(), datum.getHours(), datum.getMinutes());

                    Calendar endTime = Calendar.getInstance();
                    endTime.set(datum.getYear(), datum.getMonth(), datum.getDay(), datum.getHours() + 1, datum.getMinutes());

                    startCal.putExtra("beginTime", beginTime.getTimeInMillis());
                    startCal.putExtra("endTime", endTime.getTimeInMillis());


                } else {
                    inputDate.setError("Voer een kloppende datum in");
                    controlBit = false;
                }


                if (controlBit) {
                    startActivity(startCal);
                }
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
