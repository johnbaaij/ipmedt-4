package com.b_healty.john.prototype1.models;

/**
 * Created by John on 01/06/2017.
 */

public class FAQ {

    private String title;
    private String message;

    public FAQ(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
