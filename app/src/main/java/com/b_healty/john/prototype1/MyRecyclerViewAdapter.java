package com.b_healty.john.prototype1;

/**
 * Created by John on 13/06/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b_healty.john.prototype1.models.Card;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Card> mDataset;
    private static MyClickListener myClickListener;

    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_HEADER = 1;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView cardTitle;
        TextView cardText;
        ImageView overflow;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            cardTitle = (TextView) itemView.findViewById(R.id.cardTitle);
            cardText = (TextView) itemView.findViewById(R.id.text);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
            Log.i(LOG_TAG, "Adding Listener");


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked
                    Log.d("test",v.toString());



                }
            });
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

            Log.d("test",v.toString());

        }
    }
    public MyRecyclerViewAdapter(ArrayList<Card> myDataset) {
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
    public void onBindViewHolder(DataObjectHolder holder, int position) {



        holder.cardTitle.setText(mDataset.get(position).getTitle());
        holder.cardText.setText(mDataset.get(position).getText());



        holder.overflow.setImageResource(mDataset.get(position).getImage());
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

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}


