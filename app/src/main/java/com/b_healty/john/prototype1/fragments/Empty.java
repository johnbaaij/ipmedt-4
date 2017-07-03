package com.b_healty.john.prototype1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 06/06/2017.
 */

public class Empty extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.empty_layout, container, false);
        return view;
    }
}
