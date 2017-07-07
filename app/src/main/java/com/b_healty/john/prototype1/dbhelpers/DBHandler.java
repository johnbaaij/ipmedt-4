package com.b_healty.john.prototype1.dbhelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.b_healty.john.prototype1.models.Users;

/**
 * Created by John on 30/05/2017.
 */

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "b-healthy.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    //public static final String COLUMN_PICTUREID = "picture";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_USERS + "(" +

                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_GENDER + " TEXT " +
                //COLUMN_PICTUREID + " TEXT " +
                ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE " + TABLE_USERS;
        db.execSQL(query);
        onCreate(db);
    }



    //add new user
    public void addUser(Users users) {
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db, 1, 1);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, users.getName());
        values.put(COLUMN_GENDER, users.getGender());
        db.insert(TABLE_USERS, null, values);
        db.close();

    }
    //add new pictureID
    //public void addPicture(Users users) {
      //  ContentValues values = new ContentValues();
        //values.put(COLUMN_PICTUREID, users.getPicture());
        //SQLiteDatabase db = getWritableDatabase();
        //db.insert(TABLE_USERS, null, values);
        //db.close();

   // }




    //database results

    public String usernameToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE 1";
        //Curson point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to first row in results
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("name")) != null) {
                dbString += c.getString(c.getColumnIndex("name"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return dbString;
    }

    public String genderToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT gender FROM " + TABLE_USERS + " WHERE 1";
        //Curson point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to first row in results
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("gender")) != null) {
                dbString += c.getString(c.getColumnIndex("gender"));
                //dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return dbString;
    }
    /*public String pictureToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT picture FROM " + TABLE_USERS + " WHERE 1";
        //Curson point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to first row in results
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("picture")) != null) {
                dbString += c.getString(c.getColumnIndex("picture"));
                //dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return dbString;
    }*/



}
