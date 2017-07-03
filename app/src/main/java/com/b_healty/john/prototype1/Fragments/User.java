package com.b_healty.john.prototype1.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 06/06/2017.
 */

public class User extends Fragment {



    EditText name;
    Button confirm;
    Button cancel;
    ImageView profile;
    ImageView profilesettings;

    String username = "niks";
    RadioButton radioButtonM;
    RadioButton radioButtonV;
    String gender;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_layout, container, false);

        Bundle bundle = this.getArguments();

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
        }

        else{
            radioButtonM.setChecked(false);
            radioButtonV.setChecked(true);
        }








        //radioButton.setChecked(true);


        return view;
    }
}
