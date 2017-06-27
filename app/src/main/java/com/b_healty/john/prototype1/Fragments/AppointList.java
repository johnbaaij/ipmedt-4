package com.b_healty.john.prototype1.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b_healty.john.prototype1.R;

/**
 * Created by Ben on 27/06/2017.
 */

public class AppointList extends Fragment
{
    FragmentActivity listener;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof Activity)
        {
            this.listener = (FragmentActivity) context;
        }
    }

    // Called when the fragment is being created
    // do things here that don't require the fragment to be fully built
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Maak de adapter hier
    }

    // Called when the fragment creates its layout from XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_appoint_list, parent, false);
    }

    // View lookup and listener attaching should happen here
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        // Do setAdapter hier
    }

    // Prevent memory leak
    @Override
    public void onDetach()
    {
        super.onDetach();
        this.listener = null;
    }

    // Accessing the view hierarchy of the parent must be done here
    // as this method is called once the parent view is done
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }


}
