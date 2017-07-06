package com.b_healty.john.prototype1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.adapters.CardAdapter;
import com.b_healty.john.prototype1.controllers.HomeController;
import com.b_healty.john.prototype1.decorations.GridSpacingItemDecoration;
import com.b_healty.john.prototype1.models.Card;

import java.util.ArrayList;

/**
 * Created by John on 06/06/2017.
 */

public class Home extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private Activity activity;
    private ArrayList results = new ArrayList<Card>();
    String username;
    boolean hasCards;
    HomeController controller;
    private final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1;
    public final static int greeting = 0;
    public final static int faq = 1;
    public final static int calendar = 2;
    public final static int fase = 3;

    private StaggeredGridLayoutManager sGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle != null){
            username = bundle.getString("username");
            hasCards = bundle.getBoolean("hasCards");
            results = bundle.getParcelableArrayList("array");
        }

        View view = inflater.inflate(R.layout.home_layout, container, false);

        activity = getActivity();
        controller = new HomeController(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        int spanCount = 2; // 3 columns

        GridLayoutManager llm = new GridLayoutManager(view.getContext(), spanCount);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),
                llm.getOrientation());

        int spacingInPixels = 0;

        int spacing = 50; // 50px
        boolean includeEdge = true;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        mRecyclerView.setLayoutManager(llm);

        if (!hasCards){
            mAdapter = new CardAdapter(controller.generateCards(null, null , null, null, activity ));
        }
        else {
            mAdapter = new CardAdapter(results);
        }
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {

        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALENDAR : {
                // If request is cancelled, the result arrays are empty
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted, yay!
                    // Now you can read from the calendar

                } else {

                    // Permission was denied, you have to disable
                    // the functionality that relies on this permission

                }
                return;
            }

            // Put other 'case' lines here if there are more
            // permissions that need to be approved
        }

    }
}

