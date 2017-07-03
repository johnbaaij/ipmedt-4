package com.b_healty.john.prototype1.adapters;

/**
 * Created by John on 13/06/2017.
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.b_healty.john.prototype1.fragments.Text.HomeText;
import com.b_healty.john.prototype1.R;
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
        int data;
        int type;


        public DataObjectHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardTop);
            cardTitle = (TextView) itemView.findViewById(R.id.cardTitle);
            cardText = (TextView) itemView.findViewById(R.id.text);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
            button =(Button) itemView.findViewById(R.id.moreButton);

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


        faq = new FAQ();

        if (holder.type == 0){
            holder.button.setVisibility(View.GONE);
        }

        holder.button.setOnClickListener(new CustomOnClickListener(holder.type, holder.data) {
            @Override
            public void onClick(View v) {
                //do whatever you need here

                //Log.d(LOG_TAG, Integer.toString(variable));

                switch (type){

                    case 1:
                        Log.d(LOG_TAG, "faq");

                        String [] questionList = v.getContext().getResources().getStringArray(R.array.questions);
                        String [] answerList = v.getContext().getResources().getStringArray(R.array.answers);
                        String question = questionList[data];
                        String answer = answerList[data];




                        //Here goes your desired onClick behaviour. Like:
                        Toast.makeText(v.getContext(), "You have clicked " + v.getId(), Toast.LENGTH_SHORT).show(); //you can add data to the tag of your cardview in onBind... and retrieve it here with with.getTag().toString()..
                        //You can change the fragment, something like this, not tested, please correct for your desired output:
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        //Text myFragment = new Text();
                        //Create a bundle to pass data, add data, set the bundle to your fragment and:
                        //activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment2, myFragment).addToBackStack(null).commit();


                        Bundle bundle = new Bundle();
                        bundle.putString("question", question);
                        bundle.putString("answer", answer);

                        viewFlipper = (ViewFlipper) activity.findViewById(R.id.flipper);

                        viewFlipper.setDisplayedChild(2);


                        // Create new fragment and transaction
                        Fragment newFragment = new HomeText();
                        newFragment.setArguments(bundle);
                        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack
                        transaction.replace(R.id.fragment2, newFragment);
                        transaction.addToBackStack(null);

                        // Commit the transaction
                        transaction.commit();




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


