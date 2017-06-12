package com.b_healty.john.prototype1.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.b_healty.john.prototype1.CustomAdapter;
import com.b_healty.john.prototype1.CustomList;
import com.b_healty.john.prototype1.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.R.attr.id;
import static com.b_healty.john.prototype1.R.id.parent;

/**
 * Created by John on 06/06/2017.
 */

public class FAQ extends Fragment {

    String[] questionList;
    String[] answerList;
    Activity activity;
    ListAdapter adapter;
    ListView faqList;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.faq_layout, container, false);

        questionList = getResources().getStringArray(R.array.questions);
        answerList = getResources().getStringArray(R.array.answers);
        activity = getActivity();
        adapter = new CustomAdapter(view.getContext() , questionList, answerList);
        faqList = (ListView) view.findViewById(R.id.faqList);
        faqList.setAdapter(adapter);


        faqList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String question = String.valueOf(parent.getItemAtPosition(position));
                String answer = answerList[position];

                //Toast.makeText(activity, question, Toast.LENGTH_LONG).show();

                Bundle bundle = new Bundle();
                bundle.putString("question", question);
                bundle.putString("answer", answer);


                // Create new fragment and transaction
                Fragment newFragment = new Text();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment2, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();


            }
        });


        return view;
    }

}
