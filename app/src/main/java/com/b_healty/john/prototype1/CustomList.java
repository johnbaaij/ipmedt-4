package com.b_healty.john.prototype1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by John on 07/06/2017.
 */

public class CustomList extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    ArrayList<String> data = null;

    public CustomList(Context context, int layoutResourceId, ArrayList<String> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CustomHolder holder = null;

        if(row == null)
        {

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CustomHolder();
            //holder.txtTitle = (TextView)row.findViewById(R.id.faqLi);

            row.setTag(holder);
        }
        else
        {
            holder = (CustomHolder)row.getTag();
        }

        //String s = data.get(postition);
        //holder.txtTitle.setText(s);

        return row;
    }

    static class CustomHolder
    {
        TextView txtTitle;
    }
}