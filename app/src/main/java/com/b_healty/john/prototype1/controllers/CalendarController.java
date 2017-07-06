package com.b_healty.john.prototype1.controllers;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Switch;

import com.b_healty.john.prototype1.R;

/**
 * Created by John on 06/07/2017.
 */

public class CalendarController {

    public void setFase(String i, Activity activity){

        int fase;

        switch(i){
            case "Spalkgips":
                fase = 1;
                break;
            case "Normaal gips":
                fase = 2;
                break;
            case "Loop gips":
                fase = 3;
                break;
            case "Nazorg":
                fase = 4;
                break;
            default:
                fase = 0;
                break;
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("faseInt", fase);
        editor.commit();
    }

}
