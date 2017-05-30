package com.b_healty.john.prototype1.models;

/**
 * Created by John on 29/05/2017.
 */

public class Users {

    private String gender;
    private String name;

    public Users(){

    }

    //getters

    public String getGender(){
        return gender;
    }

    public String getName(){
        return name;
    }

    //setters

    public void setGender(String gender){
        this.gender = gender;

    }

    public void setName (String name){
        this.name = name;
    }

}
