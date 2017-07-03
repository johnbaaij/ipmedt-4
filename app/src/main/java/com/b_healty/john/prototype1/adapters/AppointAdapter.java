package com.b_healty.john.prototype1.adapters;


import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.AppointModel;

/**
 * Created by Ben on 27/06/2017.
 */

public class AppointAdapter extends ArrayAdapter<AppointModel> {
    private Context context;
    private int layoutResourceID;
    private AppointModel data[] = null;

    public AppointAdapter(Context context, int layoutResourceID, AppointModel data[])
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

        final AppointModel appointModel = data[position];
        holder.timeView.setText(appointModel.getTime());
        holder.dateView.setText(appointModel.getDate());
        holder.appointName.setText(appointModel.getAppointName());
        holder.wardName.setText(appointModel.getWardName());
        holder.doctorName.setText(appointModel.getDoctorName());

        row.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                long eventID = appointModel.getEventID();
                Uri uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(uri);
                context.startActivity(intent);
            }
        });

        return row;
    }

    private static class CalendarHolder
    {
        TextView timeView;
        TextView dateView;
        TextView appointName;
        TextView wardName;
        TextView doctorName;
    }


}
