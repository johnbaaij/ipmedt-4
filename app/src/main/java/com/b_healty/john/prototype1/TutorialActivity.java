package com.b_healty.john.prototype1;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.b_healty.john.prototype1.Fragments.tutorial.BasicTutorial;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        changeFragment();

    }


    public void changeFragment(){
        // Create new fragment and transaction
        Fragment newFragment = new BasicTutorial();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.tutorialFragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
