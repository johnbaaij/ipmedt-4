package com.b_healty.john.prototype1.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        final View view = inflater.inflate(R.layout.home_layout, container, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardView cardMidLeft = (CardView) view.findViewById(R.id.cardMidLeft);
                CardView cardMidRight = (CardView) view.findViewById(R.id.cardMidRight);
                CardView cardLowLeft = (CardView) view.findViewById(R.id.cardlowLeft);
                CardView cardLowRight = (CardView) view.findViewById(R.id.cardLowRight);

                cardLowLeft.setClickable(true);
                cardLowRight.setClickable(true);
                cardMidLeft.setClickable(true);
                cardMidRight.setClickable(true);

                switch (v.getId()){
                    case R.id.cardMidLeft:
                        Log.d ("","cardMidLeft");
                        Toast.makeText(getActivity(),"cardMidLeft",Toast.LENGTH_LONG).show();
                                break;
                    case R.id.cardMidRight:
                        Log.d ("","cardMidRight");
                        Toast.makeText(getActivity(),"cardMidRight",Toast.LENGTH_LONG).show();
                                break;
                    case R.id.cardlowLeft:
                        Log.d ("","cardLowLeft");
                        Toast.makeText(getActivity(),"cardLowLeft",Toast.LENGTH_LONG).show();
                                break;
                    case R.id.cardLowRight:
                        Log.d ("","cardLowRight");
                        Toast.makeText(getActivity(),"cardLowRight",Toast.LENGTH_LONG).show();
                                break;
                }
            }
        });

        return view;
    }


}

