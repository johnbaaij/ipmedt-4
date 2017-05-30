package com.b_healty.john.prototype1.generalClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by John on 30/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    public static final String dbName = "data";
    public static final int dbVersion = 1;        // Versie nr van je db.

    public DatabaseHelper(Context ctx) {
            super(ctx, dbName, null, dbVersion);    // gebruik de super constructor.
    }

    // synchronized … dit zorgt voor . . . . (?)
    // welk design pattern is dit ??
    public static synchronized DatabaseHelper getHelper(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
            return mInstance;
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseInfo.UserTable.USER + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.UserColumn.NAME + " TEXT," +
                DatabaseInfo.UserColumn.GENDER + " TEXT,"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.UserTable.USER);
        onCreate(db);

    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }
}

