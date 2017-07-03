package com.b_healty.john.prototype1;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.b_healty.john.prototype1.Fragments.AppointList;
import com.b_healty.john.prototype1.Fragments.FAQ;
import com.b_healty.john.prototype1.Fragments.Home;
import com.b_healty.john.prototype1.Fragments.User;
import com.b_healty.john.prototype1.dbhelpers.DBHandler;
import com.b_healty.john.prototype1.decorations.BottomNavigationViewHelper;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private AppBarLayout appBarLayout;
    private ViewFlipper viewFlipper;
    public static Resources resources;

    DBHandler dbHandler;



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
                    viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.mainLayoutBasic)));
                    changeToCalendarFragment();
                    //mTextMessage.setText(R.string.menu_afspraken);
                    return true;
                case R.id.menu_FAQ:
                    viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.mainLayoutBasic)));
                    changeToFAQFragment();
                    //mTextMessage.setText(R.string.menu_faq);
                    return true;
                case R.id.menu_user:
                    viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.mainLayoutBasic)));
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

        this.dbHandler = new DBHandler(this, null , null ,1);

        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        resources = getResources();

        ActionBar actionBar = getActionBar();
        if(actionBar !=null)
        {
            actionBar.hide();
        }

            // http://static.rogerebert.com/uploads/movie/movie_poster/flipper-1996/large_vUc6LKlZvnSu3qTxWx3fJWHuIli.jpg
            viewFlipper = (ViewFlipper) this.findViewById(R.id.flipper);
            //appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

            changeToHomeFragment();


            //mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            Bundle b = getIntent().getExtras();

            disableShiftMode();


            if(b != null) {
                String name = b.getString("name");
            // mTextMessage.setText(name);

            }
            // TODO: 01/06/2017 add else

            else{

            }
        }

    public void disableShiftMode(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void changeToHomeFragment(){
        // Create new fragment and transaction

        Bundle bundle = new Bundle();
        bundle.putString("username" , dbHandler.usernameToString());

        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.mainLayoutCards)));
        Fragment newFragment = new Home();
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
                transaction.replace(R.id.fragment1, newFragment);
                transaction.addToBackStack(null);

        // Commit the transaction

        transaction.commit();
    }

    public void changeToCalendarFragment(){

        // Create new fragment and transaction
        Fragment newFragment = new AppointList();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment2, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
//        appBarLayout.setVisibility(View.GONE);

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

        Bundle bundle = new Bundle();
        bundle.putString("username" , dbHandler.usernameToString());
        bundle.putString("gender" , dbHandler.genderToString());

        // Create new fragment and transaction
        Fragment newFragment = new User();
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment2, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }
}


