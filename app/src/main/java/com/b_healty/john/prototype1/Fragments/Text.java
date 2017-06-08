package com.b_healty.john.prototype1.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 08/06/2017.
 */

public class Text extends Fragment {
    TextView scrollable;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.text_layout, container, false);


        scrollable = (TextView)view.findViewById(R.id.text);

        //Enabling scrolling on TextView.
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }



}
