package com.b_healty.john.prototype1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
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

        View view = inflater.inflate(R.layout.profilepic_layout, container, false);

        confirm = (Button) view.findViewById(R.id.pictureConfirm);
        cancel = (Button) view.findViewById(R.id.pictureCancel);
        mainPic = (ImageView) view.findViewById(R.id.profilePicMain);

        image1 = (ImageView) view.findViewById(R.id.profilePicSelect1);
        image2 = (ImageView) view.findViewById(R.id.profilePicSelect2);
        image3 = (ImageView) view.findViewById(R.id.profilePicSelect3);
        image4 = (ImageView) view.findViewById(R.id.profilePicSelect4);
        image5 = (ImageView) view.findViewById(R.id.profilePicSelect5);




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                // Create new fragment and transaction
                Fragment newFragment = new ProfilePic();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment2, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

                Toast.makeText(listener, "Wijzingen niet opgeslagen",
                        Toast.LENGTH_SHORT).show();

                getFragmentManager().popBackStack();
            }




        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPic.setImageResource(R.drawable.ic_brokenboneicon);

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPic.setImageResource(R.drawable.ic_aidkiticon);

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPic.setImageResource(R.drawable.ic_gipsvoeticon);

            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPic.setImageResource(R.drawable.ic_ambulanceicon);

            }
        });




        return view;
    }
}