package com.b_healty.john.prototype1.adapters;

/**
 * Created by John on 13/06/2017.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_healty.john.prototype1.R;
import com.b_healty.john.prototype1.models.Card;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView
        .Adapter<CardAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Card> mDataset;
    private static MyClickListener myClickListener;




//    String [] questionList = Resources.getSystem().getStringArray(R.array.questions);
  //  String [] answerList = Resources.getSystem().getStringArray(R.array.answers);

    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_HEADER = 1;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView cardTitle;
        TextView cardText;
        ImageView overflow;
        CardView cardView;
        int data;
        int type;


        public DataObjectHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardTop);
            cardTitle = (TextView) itemView.findViewById(R.id.cardTitle);
            cardText = (TextView) itemView.findViewById(R.id.text);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
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
    public void onBindViewHolder(final DataObjectHolder holder, int position) {



        holder.cardTitle.setText(mDataset.get(position).getTitle());
        holder.cardText.setText(mDataset.get(position).getText());

        holder.overflow.setImageResource(mDataset.get(position).getImage());
        holder.itemView.setId(mDataset.get(position).getData());

        holder.data = mDataset.get(position).getData();
        holder.type = mDataset.get(position).getType();

        holder.cardView.setOnClickListener(new CustomOnClickListener(holder.type, holder.data) {
            @Override
            public void onClick(View v) {
                //do whatever you need here

                //Log.d(LOG_TAG, Integer.toString(variable));

                switch (type){

                    case 1:
                        Log.d(LOG_TAG, "faq");

//                        String question = questionList[data];
  //                      String answer = answerList[data];



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


