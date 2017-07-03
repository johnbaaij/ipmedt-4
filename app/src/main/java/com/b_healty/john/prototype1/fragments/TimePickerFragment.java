package com.b_healty.john.prototype1.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import com.b_healty.john.prototype1.R;

import java.util.Calendar;

/**
 * Created by Ben on 28/06/2017.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private EditText timeView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use current time as default values for picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create new instance of time picker dialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String concatTime = hourOfDay + ":" + minute;

        timeView = (EditText) getActivity().findViewById(R.id.inputTime);
        timeView.setText(concatTime);

    }

}
