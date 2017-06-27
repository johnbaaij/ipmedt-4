package com.b_healty.john.prototype1.controllers;

import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.R;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by John on 14/06/2017.
 */

public class HomeController {
    String[] questionList = MainActivity.resources.getStringArray(R.array.questions);
    String [] greetings = MainActivity.resources.getStringArray(R.array.greetings);

    public String generateHotTopic(int number){
        String hotTopic = questionList[number];
        return hotTopic;
    }

    public int generateRandomValue(){

        int random = new Random().nextInt(questionList.length);
        return random;

    }


    public String generateGreeting(String name ){
        int random = new Random().nextInt(greetings.length);
        String greeting = greetings[random] + " " + name;
        return greeting;
    }


}
