package com.b_healty.john.prototype1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.b_healty.john.prototype1.dbhelpers.DBHandler;
import com.b_healty.john.prototype1.LoginActivity;
import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.Users;

/**
 * Created by John on 06/06/2017.
 */

public class User extends Fragment {



    EditText name;
    Button confirm;
    Button cancel;
    ImageView profile;
    ImageView profilesettings;
    Users users;

    String username = "niks";
    RadioButton radioButtonM;
    RadioButton radioButtonV;
    String gender;
    DBHandler dbHandler;
    FragmentActivity listener;
    String newName;
    String newGender;

    @Override
    public void onAttach (Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_layout, container, false);
        users = new Users();

        Bundle bundle = this.getArguments();

        dbHandler = new DBHandler(listener, null, null, 5);

        if (bundle != null){
             username = bundle.getString("username");

        }

        else {
            username ="leeg";
        }


        if (bundle != null) {
            gender = bundle.getString("gender");
        }
        else{
            gender ="leeg";
        }

        //mainActivity.finish();

        confirm = (Button) view.findViewById(R.id.userConfirm);
        cancel = (Button) view.findViewById(R.id.userCancel);
        name = (EditText) view.findViewById(R.id.editText2);
        profile = (ImageView) view.findViewById(R.id.profilePic);
        profilesettings = (ImageView) view.findViewById(R.id.profileSettings);
        radioButtonM = (RadioButton) view.findViewById(R.id.radioButtonM);
        radioButtonV = (RadioButton) view.findViewById(R.id.radioButtonV);

        name.setText(username, TextView.BufferType.EDITABLE);

        if (gender.equals("man")) {
            radioButtonM.setChecked(true);
            radioButtonV.setChecked(false);
        } else{
            radioButtonM.setChecked(false);
            radioButtonV.setChecked(true);
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioButtonM.isChecked())
                {

                    newGender = "man";
                }
                else
                {
                    newGender = "vrouw";
                }

                newName = name.getText().toString();
                SQLiteDatabase db = dbHandler.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put(dbHandler.COLUMN_NAME, newName);
                values.put(dbHandler.COLUMN_GENDER, newGender);
                db.update(dbHandler.TABLE_USERS, values, null, null );


            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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




        });








        //radioButton.setChecked(true);


        return view;
    }
}
