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
    HomeController controller;
    private final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1;


    int normal = 0;
    int faq = 1;
    int calendar = 2;



    private StaggeredGridLayoutManager sGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle != null){
            username = bundle.getString("username");
        }


        View view = inflater.inflate(R.layout.home_layout, container, false);




        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        //RecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10),



        activity = getActivity();
        controller = new HomeController();
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

        //Greetings card
        Card greetingsCard = new Card(controller.generateGreeting(username), null ,R.drawable.krukken_icon, 0, normal);

        //addCardToArray("Geen", "idee", 0);

        //Faq hot topic
        int random = controller.generateRandomValue();
        Card hotTopicCard = new Card(controller.generateHotTopic(random), null ,R.drawable.krukken_icon, random, faq);

        //rightCard
        Card CalendarCard = new Card(controller.generateTimeStamp(activity).getDaysToCome(), null,R.drawable.krukken_icon, 3, calendar);

        controller.generateTimeStamp(activity);


        Card bonus = new Card("Extra", "Lorem ipsum",R.drawable.krukken_icon, 4, normal);



        results.add(0, greetingsCard);
        results.add(1, hotTopicCard);
        results.add(2, CalendarCard);
        results.add(3, bonus);




        mAdapter = new CardAdapter(results);
        //((MyRecyclerViewAdapter) mAdapter).addItem(leftCard, 1);
        //((MyRecyclerViewAdapter) mAdapter).addItem(topCard, 2, true);

        //mRecyclerView.setAdapter(mAdapter);
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

