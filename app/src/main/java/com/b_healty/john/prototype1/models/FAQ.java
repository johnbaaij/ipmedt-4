package com.b_healty.john.prototype1.models;

import android.content.res.Resources;

import com.b_healty.john.prototype1.MainActivity;
import com.b_healty.john.prototype1.R;

/**
 * Created by John on 01/06/2017.
 */

public class FAQ {

    private String[] questions;
    private String[] anwsers;

    public FAQ(String[] questions, String[] anwsers) {
        this.questions = questions;
        this.anwsers = anwsers;
    }

    public FAQ() {
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public void setAnwsers(String[] anwsers) {
        this.anwsers = anwsers;
    }

    public String[] getQuestions() {
        return questions;
    }

    public String[] getAnwsers() {
        return anwsers;
    }
}
