package com.b_healty.john.prototype1.Fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.b_healty.john.prototype1.AppointAdapter;
import com.b_healty.john.prototype1.CalendarInteraction;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.AppointModel;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ben on 27/06/2017.
 */

public class AppointList extends Fragment {
    AppointAdapter appointAdapter;
    private FragmentActivity listener;
    private FloatingActionButton createNew;
    private ListView appointListView;

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            onAttachToContext(activity);
        }
    }


    protected void onAttachToContext(Context context) {
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    // Called when the fragment is being created
    // do things here that don't require the fragment to be fully built
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create object of CalendarInteraction class
        CalendarInteraction mCalHelper = new CalendarInteraction(getActivity());

        // Retrieve data from the calender via the CalendarHelper Class
        Cursor data = mCalHelper.getData();

        // Create an arraylist of AppointModels
        ArrayList<AppointModel> appointmentList = new ArrayList<>();



        while (data.moveToNext())
        {
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

            // Add the appointment model to the arraylist
            appointmentList.add(appointModel);
        }

        // Close off the cursor
        data.close();

        // Maak de adapter hier
        AppointModel appointModel_data[] = new AppointModel[appointmentList.size()];

        // Create the adapter
        appointAdapter = new AppointAdapter(listener,
                R.layout.appoint_list_layout, appointModel_data);

        // Keep track of amount of models
        int count = 0;

        // Create an array of objects filled with the models from the AppointModel
        // arraylist. This array will then be used in conjunction with the appointAdapter
        // to create the list of appointments
        for (AppointModel a : appointmentList) {
            appointModel_data[count] = new AppointModel(
                    a.getEventID(),
                    a.getTime(),
                    a.getDate(),
                    a.getAppointName(),
                    a.getWardName(),
                    a.getDoctorName()
            );

            count += 1;
        }


    }


    // Called when the fragment creates its layout from XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appoint_list, parent, false);
    }


    // View lookup and listener attaching should happen here
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Do SetAdapter hier

        // Create fragment instance
        final Appointment point = new Appointment();

        // Voeg een listener toe aan de Fab
        createNew = (FloatingActionButton) listener.findViewById(R.id.createAppointment);
        createNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment2, point);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        appointListView = (ListView) listener.findViewById(R.id.AppointList);
        appointListView.setAdapter(appointAdapter);

        appointListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println();

            }
        });
    }


    // Prevent memory leak
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    // Accessing the view hierarchy of the parent must be done here
    // as this method is called once the parent view is done
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
