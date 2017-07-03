package com.b_healty.john.prototype1.models;

/**
 * Created by John on 14/06/2017.
 */

public class Card {

    String title;
    String text;
    int image;
    int id;
    int type;


    public Card(String title, String text, int image, int id, int type) {
        this.title = title;
        this.text = text;
        this.image = image;
        this.id = id;
        this.type = type;
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

    public void setMainText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getData() {
        return id;
    }

    public void setData(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
