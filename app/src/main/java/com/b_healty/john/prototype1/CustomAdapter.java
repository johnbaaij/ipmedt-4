package com.b_healty.john.prototype1;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by John on 08/06/2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    String[] answers;


    public CustomAdapter(Context context, String[] resource, String[] answers) {
        super(context, R.layout.list_faq , resource);
        this.answers = answers;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        LayoutInflater inflater  = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_faq, parent, false);






        String singleQuestion = getItem(position);


        TextView textView =(TextView) view.findViewById(R.id.questionFaq);
        TextView subText =(TextView) view.findViewById(R.id.subText);



        textView.setText(singleQuestion);
        subText.setText(answers[position]);

        return view;
    }
}
