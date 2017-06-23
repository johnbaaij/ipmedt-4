package com.b_healty.john.prototype1.models;

/**
 * Created by John on 14/06/2017.
 */

public class Card {

    String title;
    String text;
    int image;
    boolean size;


    public Card(String title, String text, int image, boolean size) {
        this.title = title;
        this.text = text;
        this.image = image;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isSize() {
        return size;
    }

    public void setSize(boolean size) {
        this.size = size;
    }
}
