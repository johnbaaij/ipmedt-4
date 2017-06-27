package com.b_healty.john.prototype1.controllers;

import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.R;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by John on 14/06/2017.
 */

public class HomeController {

    public String[] generateHotTopic(){

        String[] questionList = MainActivity.resources.getStringArray(R.array.questions);
        String[] answerList = MainActivity.resources.getStringArray(R.array.answers);

        int random = new Random().nextInt(questionList.length);

        String question = questionList[random];
        String anwser = questionList[random];

        String hotTopic[] ={question, anwser};
        return hotTopic;
    }


    public String generateGreeting(String name ){
        String [] greetings = MainActivity.resources.getStringArray(R.array.greetings);
        int random = new Random().nextInt(greetings.length);
        String greeting = name + " " + greetings[random];
        return greeting;
    }


}
