package com.b_healty.john.prototype1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    public MainActivity() {

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    mTextMessage.setText(R.string.menu_home);
                    return true;
                case R.id.menu_afspraken:
                    mTextMessage.setText(R.string.menu_afspraken);
                    return true;
                case R.id.menu_FAQ:
                    mTextMessage.setText(R.string.menu_faq);
                    return true;
                case R.id.menu_user:
                    mTextMessage.setText(R.string.menu_user);
                    return true;
            }
            return false;

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            Bundle b = getIntent().getExtras();

            disableShiftMode();


            if(b != null) {
                String name = b.getString("name");
                mTextMessage.setText(name);

            }
            ////// TODO: 01/06/2017 add else

            else{

            }
        }

    public void disableShiftMode(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }
}


