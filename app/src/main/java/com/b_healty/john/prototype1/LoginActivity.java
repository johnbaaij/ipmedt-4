package com.b_healty.john.prototype1;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.b_healty.john.prototype1.dbhelpers.DBHandler;
import com.b_healty.john.prototype1.fragments.Login.Login;
import com.b_healty.john.prototype1.fragments.ProfilePic;
import com.b_healty.john.prototype1.models.Users;


public class LoginActivity extends AppCompatActivity {

    DBHandler dbHandler;

    public LoginActivity() {
        this.dbHandler = new DBHandler(this, null, null, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        Bundle bundle = new Bundle();

        // Create new fragment and transaction
        Fragment newFragment = new Login();
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.loginfragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

}
