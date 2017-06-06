package com.b_healty.john.prototype1;

import android.content.Intent;
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

        String name = dbHandler.databaseToString();
        if (!name.isEmpty()) {
            Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
            Bundle b = new Bundle();
            b.putString("name", name); 	// Your id
            intent.putExtras(b); 	// Put your id to your next Intent
            startActivity(intent);	// start
        }

        else{
            startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
        }
        finish();

    }
}
