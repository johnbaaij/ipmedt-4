package com.b_healty.john.prototype1.Fragments.tutorial;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.b_healty.john.prototype1.LauncherActivity;
import com.b_healty.john.prototype1.LoginActivity;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.TutorialActivity;

/**
 * Created by John on 14/06/2017.
 */

public class BasicTutorial extends Fragment {

    Button tutNext;
    Button tutSkip;
    TextView tutText;
    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tutorial_layout, container, false);

        tutNext = (Button) view.findViewById(R.id.tutNext);
        tutSkip = (Button) view.findViewById(R.id.tutSkip);
        tutText = (TextView) view.findViewById(R.id.tutText);
        updateText(count);

        tutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tutNext();


            }
        });


        tutSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tutSkip();
            }
        });

        return view;
    }

    public void tutNext(){



        if (count < 2){
            count++;
            updateText(count);
        }
        else{
            Fragment newFragment = new FinalTutorial();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.tutorialFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void tutSkip(){
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    public void updateText(int count){

        switch (count){
            case 0:
                tutText.setText("Dit is nul");
                break;
            case 1:
                tutText.setText("Dit is 1 ");
                break;
            case 2:
                tutText.setText("Dit is 2");
                break;
            default:
                tutText.setText("Dit is default");
                break;
        }


    }
}
