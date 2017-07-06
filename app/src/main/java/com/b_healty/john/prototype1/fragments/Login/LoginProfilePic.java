package com.b_healty.john.prototype1.fragments.Login;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.fragments.ProfilePic;

/**
 * Created by John on 06/07/2017.
 */

public class LoginProfilePic extends ProfilePic {

    @Override
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
        transaction.replace(R.id.loginfragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        Toast.makeText(view.getContext(), "Wijzingen niet opgeslagen",
                Toast.LENGTH_SHORT).show();

        getFragmentManager().popBackStack();
    }
}
