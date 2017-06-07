package com.b_healty.john.prototype1;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.Fragment;

import com.b_healty.john.prototype1.Fragments.Calendar;
import com.b_healty.john.prototype1.Fragments.FAQ;
import com.b_healty.john.prototype1.Fragments.Home;
import com.b_healty.john.prototype1.Fragments.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    public MainActivity() {

        //mTextMessage = (TextView) findViewById(R.id.textView);

    }

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
        Fragment newFragment = new Calendar();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment2, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }


    public void changeToFAQFragment(){

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("faq");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("question"));
                String formula_value = jo_inside.getString("question");
                String url_value = jo_inside.getString("text");

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("question", formula_value);
                m_li.put("text", url_value);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("faq.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public ArrayList addJSONtoArray(){
        ArrayList list= null;

        return list;
    }
}


