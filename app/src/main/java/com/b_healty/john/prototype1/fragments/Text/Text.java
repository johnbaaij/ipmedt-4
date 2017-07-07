package com.b_healty.john.prototype1.fragments.Text;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
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
    TextView title;

    String answer;
    String question;
    int questionNumber;

    protected Toolbar toolBar;
    protected FragmentActivity listener;
    protected Activity activity;
    protected TextView link;

    // This method is called when a fragment instance is associated with an activity and will run
    // before anything else
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity();


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.question = bundle.getString("question");
            this.answer = bundle.getString("answer");
            this.questionNumber = bundle.getInt("questionNumber");

                //Toast.makeText(getActivity(), answer, Toast.LENGTH_SHORT).show();


        }

        View view = inflater.inflate(R.layout.text_layout, container, false);


        scrollable = (TextView) view.findViewById(R.id.text);
        scrollable.setText(answer);

        title = (TextView)view.findViewById(R.id.questionFaq2);
        title.setText(question);
        link = (TextView)view.findViewById(R.id.textView5);


        if (questionNumber == 0){
            link.setVisibility(View.VISIBLE);
        }

        else {
            link.setVisibility(View.GONE);
        }

        //Enabling scrolling on TextView.
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    // shouldn't be existing outside this fragment
    @Override
    public void onStop() {
        super.onStop();
        toolBar.setNavigationIcon(null);
    }


    // This fragment is called once the fragment is started. Do stuff here that should
    // only happen once this fragment is active
    @Override
    public void onStart() {
        super.onStart();
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
    }


    // This method is called after the parent activities' onCreate() method has completed
    // If you want to access a view inside the parent you have to do that via this method
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        toolBar = (android.support.v7.widget.Toolbar) activity.findViewById(R.id.toolbarMain);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBackPressed();
            }
        });

    }

}
