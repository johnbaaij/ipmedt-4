package com.b_healty.john.prototype1.fragments.Login;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.b_healty.john.prototype1.LoginActivity;
import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.dbhelpers.DBHandler;
import com.b_healty.john.prototype1.fragments.ProfilePic;
import com.b_healty.john.prototype1.models.Users;

/**
 * Created by rogie on 06/07/2017.
 */

public class Login extends Fragment {





    EditText name;
    Button login;

    // DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);

    DBHandler dbHandler;
    MainActivity mainActivity;
    RadioButton radioButton;
    FloatingActionButton profilesettings;
    RadioGroup radioGroup;
    ImageView imageView;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






        final View view = inflater.inflate(R.layout.login_layout, container, false);



        this.dbHandler = new DBHandler(getActivity(), null, null, 1);


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        final int drawableId = sharedPref.getInt("drawableId", 0);

        //mainActivity.finish();

        login = (Button) view.findViewById(R.id.button);
        name = (EditText) view.findViewById(R.id.editText2);
        profilesettings = (FloatingActionButton) view.findViewById(R.id.profileSettings);
        imageView = (ImageView) view.findViewById(R.id.profilePicLogin);

        imageView.setImageResource(drawableId);

        name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        profilesettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                // Create new fragment and transaction
                Fragment newFragment = new LoginProfilePic();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.loginfragment, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();


            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {


                String message = name.getText().toString();


                if (message.isEmpty()) {

                    Toast.makeText(getActivity(), "Voer je naam in", Toast.LENGTH_LONG).show();


                } else {

                    //int selectedId = radioGroup.getCheckedRadioButtonId();

                    radioButton = (RadioButton) view.findViewById(R.id.radioButtonM);
                    String gender;


                    if (radioButton.isChecked()) {

                        gender = "man";
                    } else {
                        gender = "vrouw";
                    }

                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

                    //Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = sharedPref.edit();

                    int cardProfilePic;
                    switch (drawableId){
                        case R.drawable.krukken_icon:
                            cardProfilePic = R.drawable.ic_krukken_iconvk;
                            editor.putInt("userPic", cardProfilePic);
                            editor.commit();
                            break;
                        case R.drawable.ic_brokenboneicon:
                            cardProfilePic = R.drawable.ic_brokenboneiconvk;
                            editor.putInt("userPic", cardProfilePic);
                            editor.commit();
                            break;
                        case R.drawable.ic_aidkiticon:
                            cardProfilePic = R.drawable.ic_aidkiticonvk;
                            editor.putInt("userPic", cardProfilePic);
                            editor.commit();
                            break;
                        case R.drawable.ic_gipsvoeticon:
                            cardProfilePic = R.drawable.ic_gipsvoeticonvk;
                            editor.putInt("userPic", cardProfilePic);
                            editor.commit();
                            break;
                        case R.drawable.ic_ambulanceicon:
                            cardProfilePic = R.drawable.ic_ambulanceiconvk;
                            editor.putInt("userPic", cardProfilePic);
                            editor.commit();
                            break;
                        case R.mipmap.ic_apache:
                            cardProfilePic = R.mipmap.ic_apachevk;
                            editor.putInt("userPic", cardProfilePic);
                            editor.commit();
                            break;
                    }

                    Users users = new Users();
                    users.setName(message);
                    users.setGender(gender);
                    dbHandler.addUser(users);
                    editor.putInt("faseInt", 1);
                    editor.commit();




                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Bundle b = new Bundle();
                    b.putString("name", message);    // Your id
                    intent.putExtras(b);    // Put your id to your next Intent
                    startActivity(intent);    // start
                }


            }


        });

        return view;



    }
}
