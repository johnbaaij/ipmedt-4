package com.b_healty.john.prototype1;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.b_healty.john.prototype1.models.Appointmodel;

/**
 * Created by Ben on 27/06/2017.
 */

public class AppointAdapter extends ArrayAdapter<Appointmodel> {
    private Context context;
    private int layoutResourceID;
    private Appointmodel data[] = null;

    public AppointAdapter(Context context, int layoutResourceID, Appointmodel data[])
    {
        super(context, layoutResourceID, data);
        this.layoutResourceID = layoutResourceID;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        CalendarHolder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceID, parent, false);

            holder = new CalendarHolder();
            holder.timeView = (TextView) row.findViewById(R.id.timeView);
            holder.dateView = (TextView) row.findViewById(R.id.dateView);
            holder.appointName = (TextView) row.findViewById(R.id.appointName);
            holder.wardName = (TextView) row.findViewById(R.id.wardName);
            holder.doctorName = (TextView) row.findViewById(R.id.doctorName);

            row.setTag(holder);
        }
        else
        {
            holder = (CalendarHolder) row.getTag();
        }

        Appointmodel appointmodel = data[position];
        holder.timeView.setText(appointmodel.getTime());
        holder.dateView.setText(appointmodel.getDate());
        holder.appointName.setText(appointmodel.getAppointName());
        holder.wardName.setText(appointmodel.getWardName());
        holder.doctorName.setText(appointmodel.getDoctorName());

        return row;

    }

    static class CalendarHolder
    {
        TextView timeView;
        TextView dateView;
        TextView appointName;
        TextView wardName;
        TextView doctorName;
    }


}
