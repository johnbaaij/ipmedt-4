package com.b_healty.john.prototype1.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 06/06/2017.
 */

public class Calendar extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calendar_layout, container, false);

        // TODO: 18-6-2017 Het toetsenbord duwt de hele layout omhoog
        // TODO: 18-6-2017 Er moeten nog animaties gemaakt worden voor de progressbar 
        // TODO: 18-6-2017 Voor de tijd input en de datum input gestroomlijnde manieren 
        // TODO: 18-6-2017 Alle front end stuff moet gekoppeld worden aan een back-end 



        return view;
    }
}
