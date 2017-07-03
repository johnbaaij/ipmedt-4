package com.b_healty.john.prototype1.Fragments;

import android.app.Activity;
import android.app.DialogFragment;
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
 * Created by Ben on 06/06/2017.
 */

public class Appointment extends Fragment {

    // TODO: 18-6-2017 Er moeten nog animaties gemaakt worden voor de progressbar
    // TODO: Wanneer het aantal dagen lager is dan 1 verander 'dagen' in 'dag'

    private final String LEGGYCALL = "LGGYCL";
    private FragmentActivity listener;
    private View view;
    private Activity activity;
    private EditText appointName;
    private EditText inputTime;
    private EditText inputDate;
    private EditText wardName;
    private EditText doctorName;
    private Date datum;
    private boolean controlBit;
    private android.support.v7.widget.Toolbar toolBar;

    public Appointment() {
        datum = new Date();
    }

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
                String description;
                String wardNameString = null;
                String doctorNameString = null;


                // Stel de naam van de afspraak in en voeg het ID aan het einde toe
                if (!appointName.getText().toString().equals("")) {
                    startCal.putExtra("title", appointName.getText().toString() + " - " + LEGGYCALL);

                } else {
                    appointName.setError("Geef de afspraak een naam");
                    controlBit = false;
                }

                // Controleer of het veld van de tijd is ingevoerd
                if (!inputTime.getText().toString().equals("")) {
                    // Nothing here
                } else {
                    inputTime.setError("Voer een kloppende tijd in");
                    controlBit = false;
                }

                // Parse de datum en voeg deze samen met de tijd om een datetime
                // object te maken en dit in de bundle te stoppen
                if (!inputDate.getText().toString().equals("")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    try {
                        datum = dateFormat.parse(inputDate.getText().toString() + " " +
                                inputTime.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Calendar beginTime = Calendar.getInstance();
                    beginTime.setTime(datum);

                    Calendar endTime = Calendar.getInstance();
                    endTime.setTime(datum);
                    endTime.add(Calendar.HOUR, 2);

                    System.out.println(endTime);
                    startCal.putExtra("beginTime", beginTime.getTimeInMillis());
                    startCal.putExtra("endTime", endTime.getTimeInMillis());


                } else {
                    inputDate.setError("Voer een kloppende datum in");
                    controlBit = false;
                }


                // Get the wardname 
                if (!wardName.getText().toString().equals("")) {
                    wardNameString = wardName.getText().toString();
                } else {
                    wardName.setError("Voer de naam van je behandelkamer in");
                    controlBit = false;
                }

                // Get the doctor name
                if (!doctorName.getText().toString().equals("")) {
                    doctorNameString = doctorName.getText().toString();
                } else {
                    doctorName.setError("Voer de naam van je dokter in");
                    controlBit = false;
                }

                // Generate description
                description = doctorNameString + " - " + wardNameString;

                startCal.putExtra("description", description);

                // Controleer of alle vakken zijn ingevuld
                if (controlBit) {
                    startActivity(startCal);
                    getActivity().getFragmentManager().popBackStack();
                }


            }
        });

        inputTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TimePickerFragment();
                dialogFragment.show(activity.getFragmentManager(), "timePicker");
            }
        });

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(activity.getFragmentManager(), "datePicker");
            }
        });


    }

    // This method is called once the fragment is stopped. Remove stuff here that
    // shouldn't be existing outside this fragment
    @Override
    public void onStop() {
        super.onStop();
        toolBar.setNavigationIcon(null);
    }


    // This fragment is called once the fragment is started. Do stuff here that should
    // only happen once this fragment is active
    @Override
    public void onStart() {
        super.onStart();
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
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

        toolBar = (android.support.v7.widget.Toolbar) activity.findViewById(R.id.toolbarMain);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBackPressed();
            }
        });

    }
}
