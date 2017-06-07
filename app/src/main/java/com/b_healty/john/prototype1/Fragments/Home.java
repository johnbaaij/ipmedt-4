package com.b_healty.john.prototype1.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by John on 06/06/2017.
 */

public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_layout, container, false);
        return view;
    }


}

