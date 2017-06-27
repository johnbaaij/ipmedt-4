package com.b_healty.john.prototype1.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.b_healty.john.prototype1.AppointAdapter;
import com.b_healty.john.prototype1.CalendarInteraction;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.Appointmodel;

import java.util.Calendar;

/**
 * Created by Ben on 27/06/2017.
 */

public class AppointList extends Fragment {

    private FragmentActivity listener;
    private FloatingActionButton createNew;
    private ListView appointListView;

    AppointAdapter appointAdapter;

    // Stuff
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }


    // Called when the fragment is being created
    // do things here that don't require the fragment to be fully built
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Maak de adapter hier
        Appointmodel appointmodel_data[] = new Appointmodel[] {

        };

        CalendarInteraction mCalHelper = new CalendarInteraction(listener);
        Cursor data = mCalHelper.getData();

        while (data.moveToNext())
        {
            long evtId;
            long evtDtStart;
            String evtTitle;
            String evtDescription;

            evtId = data.getLong(CalendarInteraction.getProjectionIdIndex());
            evtDtStart = data.getLong(CalendarInteraction.getProjectionDtstartIndex());
            evtTitle = data.getString(CalendarInteraction.getProjectionTitleIndex());
            evtDescription = data.getString(CalendarInteraction.getProjectionDescriptionIndex());

            System.out.println(evtDtStart);
            System.out.println(getDate(evtDtStart));
            System.out.println(getTime(evtDtStart));



        }

        appointAdapter = new AppointAdapter(listener,
                R.layout.appoint_list_layout, appointmodel_data);

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

    private String getDate(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    private String getTime(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        String time = DateFormat.format("HH:mm", cal).toString();
        return time;
    }

}
