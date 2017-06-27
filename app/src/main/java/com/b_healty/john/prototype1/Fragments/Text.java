package com.b_healty.john.prototype1.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 08/06/2017.
 */

public class Text extends Fragment {
    TextView scrollable;
    TextView title;

    String answer;
    String question;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.question = bundle.getString("question");
             this.answer = bundle.getString("answer");
                //Toast.makeText(getActivity(), answer, Toast.LENGTH_SHORT).show();


        }

        View view = inflater.inflate(R.layout.text_layout, container, false);


        scrollable = (TextView) view.findViewById(R.id.text);
        scrollable.setText(answer);

        title = (TextView)view.findViewById(R.id.questionFaq2);
        title.setText(question);

        //Enabling scrolling on TextView.
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }



}
