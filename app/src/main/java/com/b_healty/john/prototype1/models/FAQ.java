package com.b_healty.john.prototype1.models;

/**
 * Created by John on 01/06/2017.
 */

public class FAQ {

    private int id;
    private String question;
    private String text;

    public FAQ(int id, String question, String text) {
        this.id = id;
        this.question = question;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
