package com.b_healty.john.prototype1.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.MyRecyclerViewAdapter;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.Card;
import com.b_healty.john.prototype1.models.DataObject;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);

        activity = getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        addCardToArray("Hallo", "Werkt dit ?", 0);







        mAdapter = new MyRecyclerViewAdapter(results);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }


    private void addCardToArray(String title, String text, int image){
        Card card = new Card(title, text, image);
        results.add(card);
    }


}

