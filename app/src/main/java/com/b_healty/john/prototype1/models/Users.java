package com.b_healty.john.prototype1.models;

/**
 * Created by John on 29/05/2017.
 */

public class Users {

    private String gender;
    private String name;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users() {

    }

    public Users(String gender, String name) {
        this.gender = gender;
        this.name = name;
    }


}
