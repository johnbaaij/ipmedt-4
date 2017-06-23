package com.b_healty.john.prototype1.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

//import com.b_healty.john.prototype1.GridSpacingItemDecoration;
import com.b_healty.john.prototype1.GridSpacingItemDecoration;
import com.b_healty.john.prototype1.MyRecyclerViewAdapter;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.SpacesItemDecoration;
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

    //topCard
    String topCardTitle;
    String topCardText;
    int topCardImage;

    //leftCard
    String leftCardTitle;
    String leftCardText;
    int leftCardImage;

    //leftCard
    String rightCardTitle;
    String rightCardText;
    int rightCardImage;

    private StaggeredGridLayoutManager sGridLayoutManager;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);


        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        //RecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10),



        activity = getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setHasFixedSize(true);
        int spanCount = 2; // 3 columns



        GridLayoutManager llm = new GridLayoutManager(view.getContext(), spanCount);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),
                llm.getOrientation());

        int spacingInPixels = 0;


        int spacing = 50; // 50px
        boolean includeEdge = true;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));



        mRecyclerView.setLayoutManager(llm);







        //mLayoutManager = new LinearLayoutManager(activity);
        //RecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new MyRecyclerViewAdapter.GridSpacingItemDecoration(2, dpToPx(10), true));

        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());



        //topCard
        Card topCard = new Card("top", "top" ,1, true);

        //addCardToArray("Geen", "idee", 0);

        //leftCard
        Card leftCard = new Card("left", "left" ,1, false);

        //rightCard
        Card rightCard = new Card("right", "right",2, false);


        Card bonus = new Card("right", "right",2, false);



        results.add(0, topCard);
        results.add(1, leftCard);
        results.add(2, rightCard);
        results.add(3, bonus);



        mAdapter = new MyRecyclerViewAdapter(results);
        //((MyRecyclerViewAdapter) mAdapter).addItem(leftCard, 1);
        //((MyRecyclerViewAdapter) mAdapter).addItem(topCard, 2, true);

        //mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }









}

