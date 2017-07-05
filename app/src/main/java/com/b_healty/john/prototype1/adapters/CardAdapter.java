package com.b_healty.john.prototype1.adapters;

/**
 * Created by John on 13/06/2017.
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.b_healty.john.prototype1.controllers.HomeController;
import com.b_healty.john.prototype1.dbhelpers.AppointmentGetter;
import com.b_healty.john.prototype1.dbhelpers.CalendarInteraction;
import com.b_healty.john.prototype1.fragments.Calendar.AppointmentHome;
import com.b_healty.john.prototype1.fragments.Home;
import com.b_healty.john.prototype1.fragments.Text.HomeText;
import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.AppointModel;
import com.b_healty.john.prototype1.models.Card;
import com.b_healty.john.prototype1.models.FAQ;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView
        .Adapter<CardAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Card> mDataset;
    private static MyClickListener myClickListener;
    private ViewFlipper viewFlipper;
    AppointModel appointModel;
    AppointmentGetter appointmentGetter;
    CalendarInteraction mCalHelper;
    private ArrayList results = new ArrayList<Card>();
    int test = 1;
    HomeController controller;





    FAQ faq;






    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_HEADER = 1;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView cardTitle;
        TextView cardText;
        ImageView overflow;
        CardView cardView;
        Button button;
        ImageButton refresh;
        int data;
        int type;



        public DataObjectHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardTop);
            cardTitle = (TextView) itemView.findViewById(R.id.cardTitle);
            cardText = (TextView) itemView.findViewById(R.id.text);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
            button =(Button) itemView.findViewById(R.id.moreButton);
            refresh =(ImageButton) itemView.findViewById(R.id.refreshButton);

            itemView.setClickable(true);

            Log.i(LOG_TAG, "Adding Listener");



        }




        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

            Log.d(LOG_TAG, "test");

            Log.d("test",v.toString());

        }
    }
    public CardAdapter(ArrayList<Card> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {

        mDataset.size();
        View view;

        DataObjectHolder dataObjectHolder;


        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_single, parent, false);
        dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;

    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.cardTitle.setText(mDataset.get(position).getTitle());
        holder.cardText.setText(mDataset.get(position).getText());

        holder.overflow.setImageResource(mDataset.get(position).getImage());
        holder.itemView.setId(mDataset.get(position).getData());
        holder.data = mDataset.get(position).getData();
        holder.type = mDataset.get(position).getType();


        faq = new FAQ();

        if (holder.type == Home.greeting || holder.type == Home.fase){
            holder.button.setVisibility(View.GONE);
        }

        if (holder.type == Home.fase){
            holder.refresh.setVisibility(View.GONE);
        }


        holder.refresh.setOnClickListener(new CustomOnClickListener(holder.type, holder.data){
            @Override
            public void onClick(View v){

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                controller = new HomeController(activity);


                switch (type){
                    case 0:
                        results = controller.generateCards(null, mDataset.get(position + 1), mDataset.get(position + 2) ,null, activity);
                        break;
                    case 1:
                        results = controller.generateCards(mDataset.get(position - 1),null , mDataset.get(position + 1) ,null, activity);
                        break;
                    case 2:
                        results = controller.generateCards(mDataset.get(position - 2),mDataset.get(position - 1) ,null  ,null, activity);
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("hasCards", true);
                bundle.putParcelableArrayList("array", results);
                Fragment newFragment = new Home();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment1, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        holder.button.setOnClickListener(new CustomOnClickListener(holder.type, holder.data) {
            @Override
            public void onClick(View v) {
                //do whatever you need here
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                switch (type){

                    case 1:
                        Log.d(LOG_TAG, "faq");

                        String [] questionList = v.getContext().getResources().getStringArray(R.array.questions);
                        String [] answerList = v.getContext().getResources().getStringArray(R.array.answers);
                        String question = questionList[data];
                        String answer = answerList[data];
                        Bundle bundle = new Bundle();
                        bundle.putString("question", question);
                        bundle.putString("answer", answer);
                        viewFlipper = (ViewFlipper) activity.findViewById(R.id.flipper);
                        viewFlipper.setDisplayedChild(2);
                        Fragment newFragment = new HomeText();
                        newFragment.setArguments(bundle);
                        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment2, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;


                    case 2:

                        if (holder.data == 0){
                            viewFlipper = (ViewFlipper) activity.findViewById(R.id.flipper);
                            viewFlipper.setDisplayedChild(2);
                            Fragment calendar = new AppointmentHome();
                            FragmentTransaction calendarTransaction = activity.getFragmentManager().beginTransaction();
                            calendarTransaction.replace(R.id.fragment2, calendar);
                            calendarTransaction.addToBackStack(null);
                            calendarTransaction.commit();
                        }

                        else {
                            CalendarInteraction mCalHelper = new CalendarInteraction(activity);
                            appointmentGetter = new AppointmentGetter();

                            // Retrieve data from the calender via the CalendarHelper Class
                            Cursor data = mCalHelper.getData();

                            data.moveToFirst();

                            // Create an arraylist of AppointModels

                            appointModel = new AppointModel();

                            appointModel = appointmentGetter.getData(data);

                            // Add the appointment model to the arraylist

                            // Close off the cursor
                            data.close();

                            Log.d(LOG_TAG, "calendar");
                            long eventID = appointModel.getEventID();

                            Uri uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
                            Intent intent = new Intent(Intent.ACTION_VIEW)
                                    .setData(uri);
                            activity.startActivity(intent);
                        }

                        break;
                    default:
                        Log.d(LOG_TAG, "niet faq");
                        break;
                }
            }


        });
    }


    public void addItem(Card card, int index) {

        mDataset.add(index, card);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private interface MyClickListener {
        void onItemClick(int position, View v);
    }


    private class CustomOnClickListener implements View.OnClickListener
    {

        int type;
        int data;
        private CustomOnClickListener(int type, int data) {
            this.type = type;
            this.data = data;
        }

        public void onClick(View v)
        {
            //read your lovely variable
        }

    }






}


