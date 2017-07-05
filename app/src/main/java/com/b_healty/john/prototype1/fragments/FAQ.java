package com.b_healty.john.prototype1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.b_healty.john.prototype1.fragments.Text.Text;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.adapters.FaqAdapter;

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
        adapter = new FaqAdapter(view.getContext() , questionList, answerList);
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
