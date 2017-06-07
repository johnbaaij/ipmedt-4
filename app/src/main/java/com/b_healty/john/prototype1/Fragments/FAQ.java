package com.b_healty.john.prototype1.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.b_healty.john.prototype1.CustomList;
import com.b_healty.john.prototype1.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by John on 06/06/2017.
 */

public class FAQ extends Fragment {

   // private ArrayList list;
   ListView list;
    Gson gson;
    CustomList adapter;



    public FAQ() {
        this.gson = new Gson();
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        list=(ListView)getActivity().findViewById(R.id.faqList);



        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = obj.getJSONArray("faq");
            //ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            //HashMap<String, String> m_li;

            ArrayList<String> messages = new ArrayList<String>();
            for (int i = 0; i < jsonArray.length(); i++) {

                // Creating JSONObject from JSONArray
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                // Getting data from individual JSONObject
                String message = jsonObj.getString("question");
                messages.add(message);

            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, messages);
            //list=(ListView)getActivity().findViewById(R.id.faqList);
            //list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        View view = inflater.inflate(R.layout.faq_layout, container, false);
        return view;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("faq.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }




}
