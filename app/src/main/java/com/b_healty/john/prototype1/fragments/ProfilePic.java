package com.b_healty.john.prototype1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.dbhelpers.DBHandler;

/**
 * Created by rogie on 04/07/2017.
 */

public class ProfilePic extends Fragment {

    Button confirm;
    Button cancel;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    DBHandler dbHandler;
    FragmentActivity listener;
    ImageView mainPic;
    String picture;
    MainActivity mainActivity;
    boolean apacheSetting = false;


    int count = 0;


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

        final View view = inflater.inflate(R.layout.profilepic_layout, container, false);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        int drawableId = sharedPref.getInt("drawableId", 0);

        confirm = (Button) view.findViewById(R.id.pictureConfirm);
        cancel = (Button) view.findViewById(R.id.pictureCancel);
        mainPic = (ImageView) view.findViewById(R.id.profilePicMain);

        mainPic.setImageResource(drawableId);


        image1 = (ImageView) view.findViewById(R.id.profilePicSelect1);
        image2 = (ImageView) view.findViewById(R.id.profilePicSelect2);
        image3 = (ImageView) view.findViewById(R.id.profilePicSelect3);
        image4 = (ImageView) view.findViewById(R.id.profilePicSelect4);
        image5 = (ImageView) view.findViewById(R.id.profilePicSelect5);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancel(view);
            }




        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = sharedPref.edit();




                switch (count){
                    case 1:
                        editor.putInt("drawableId", R.drawable.krukken_icon);
                        editor.commit();
                        break;
                    case 2:
                        editor.putInt("drawableId", R.drawable.ic_brokenboneicon);
                        editor.commit();
                        break;
                    case 3:
                        editor.putInt("drawableId", R.drawable.ic_aidkiticon);
                        editor.commit();
                        break;
                    case 4:
                        editor.putInt("drawableId", R.drawable.ic_gipsvoeticon);
                        editor.commit();
                        break;
                    case 5:
                        editor.putInt("drawableId", R.drawable.ic_ambulanceicon);
                        editor.commit();
                        break;

                    case 420:
                        editor.putInt("drawableId", R.mipmap.ic_apache);
                        editor.commit();
                    default:
                        break;

                }



                /*

                Bundle bundle = new Bundle();

                // Create new fragment and transaction
                Fragment newFragment = new User();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment2, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit(); */


                    Toast.makeText(listener, "Wijzingen opgeslagen",
                    Toast.LENGTH_SHORT).show();

                getFragmentManager().popBackStack();

            }




        });


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPic.setImageResource(R.drawable.krukken_icon);
                count = 1;

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPic.setImageResource(R.drawable.ic_brokenboneicon);
                count = 2;


            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (apacheSetting){
                    mainPic.setImageResource(R.mipmap.ic_apache);
                    count = 420;
                }

                else {
                    mainPic.setImageResource(R.drawable.ic_aidkiticon);
                    count = 3;

                }


            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPic.setImageResource(R.drawable.ic_gipsvoeticon);
                count = 4;


            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPic.setImageResource(R.drawable.ic_ambulanceicon);
                count = 5;


            }
        });


        mainPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                int apache = sharedPref.getInt("apache", 0);
                apache++;


                if (apache > 20){
                    image3.setImageResource(R.mipmap.ic_apache);
                    apacheSetting = true;
                    Toast.makeText(view.getContext(), "Je bent nu een Apache helicopter!",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("apache", apache);
                    editor.commit();
                }




            }
        });





        return view;
    }


    protected void onCancel(View view){
        Bundle bundle = new Bundle();
        // Create new fragment and transaction
        Fragment newFragment = new ProfilePic();
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        SharedPreferences.Editor editor = sharedPref.edit();



        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment2, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Toast.makeText(view.getContext(), "Wijzingen niet opgeslagen",
                Toast.LENGTH_SHORT).show();

        getFragmentManager().popBackStack();
    }
}