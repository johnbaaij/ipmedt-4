package com.b_healty.john.prototype1;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.b_healty.john.prototype1.Fragments.Appointment;
import com.b_healty.john.prototype1.Fragments.FAQ;
import com.b_healty.john.prototype1.Fragments.Home;
import com.b_healty.john.prototype1.Fragments.User;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    changeToHomeFragment();
                    //mTextMessage.setText(R.string.menu_home);
                    return true;
                case R.id.menu_afspraken:
                    changeToCalendarFragment();
                    //mTextMessage.setText(R.string.menu_afspraken);
                    return true;
                case R.id.menu_FAQ:
                    changeToFAQFragment();
                    //mTextMessage.setText(R.string.menu_faq);
                    return true;
                case R.id.menu_user:
                    changeToUserFragment();
                    return true;
            }
            return false;

        }

    };

    public MainActivity() {

        //mTextMessage = (TextView) findViewById(R.id.textView);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            changeToHomeFragment();


        //mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            Bundle b = getIntent().getExtras();

            disableShiftMode();


            if(b != null) {
                String name = b.getString("name");
//                mTextMessage.setText(name);

            }
            ////// TODO: 01/06/2017 add else

            else{

            }
        }

    public void disableShiftMode(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }




    public void changeToHomeFragment(){
        // Create new fragment and transaction
        Fragment newFragment = new Home();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
                transaction.replace(R.id.fragment2, newFragment);
                transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void changeToCalendarFragment(){

        // Create new fragment and transaction
        Fragment newFragment = new Appointment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment2, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }


    public void changeToFAQFragment(){


        // Create new fragment and transaction
        Fragment newFragment = new FAQ();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment2, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();


    }


    public void changeToUserFragment(){

        // Create new fragment and transaction
        Fragment newFragment = new User();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment2, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }


}


