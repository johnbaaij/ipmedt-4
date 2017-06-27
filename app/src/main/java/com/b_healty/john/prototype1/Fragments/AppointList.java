package com.b_healty.john.prototype1.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.b_healty.john.prototype1.AppointAdapter;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.Appointmodel;

/**
 * Created by Ben on 27/06/2017.
 */

public class AppointList extends Fragment {

    private FragmentActivity listener;
    private FloatingActionButton createNew;
    private ListView appointListView;

    AppointAdapter appointAdapter;


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
            new Appointmodel("11:15", "15/12/2017", "Beenbreuk fixen", "Radiologie", "Sanjib"),
            new Appointmodel("12:45", "23/07/2017", "Gips resetten", "Gips", "Verlier"),
            new Appointmodel("17:45", "29/01/2017", "Been verdraaid", "Botbreuken", "Wingelaar"),
            new Appointmodel("15:25", "08/10/2017", "Date met de zuster", "Bevalling", "Sandy"),
            new Appointmodel("16:30", "17/04/2017", "X-Ray", "Radiologie", "Harlingbos"),
            new Appointmodel("09:15", "11/05/2017", "Knelverband", "Verbinding", "Bonteman")
        };

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

}
