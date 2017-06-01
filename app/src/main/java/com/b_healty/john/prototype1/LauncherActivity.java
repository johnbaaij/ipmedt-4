package com.b_healty.john.prototype1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    DBHandler dbHandler;

    public LauncherActivity() {
        this.dbHandler = new DBHandler(this, null, null, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_launcher);



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.commit();

            startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
        }

        else{


            String name = dbHandler.databaseToString();


            Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
            Bundle b = new Bundle();
            b.putString("name", name); 	// Your id
            intent.putExtras(b); 	// Put your id to your next Intent
            startActivity(intent);	// start
            //startActivity(new Intent(LauncherActivity.this, MainActivity.class));


        }

        finish();




    }
}
