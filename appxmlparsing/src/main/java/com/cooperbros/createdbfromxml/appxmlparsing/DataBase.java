package com.cooperbros.createdbfromxml.appxmlparsing;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLDataException;

/**
 * Data base
 */
public class DataBase {

    private static DBHelper dbHelper;
    private static final String DATABASE_TABLE = "country";
    public static SQLiteDatabase sqLiteDatabase;
    public static Context mContext;


    public DataBase(Context context){

        mContext = context;
        dbHelper = new DBHelper(mContext);

    }

    public DataBase(){

    }

    public void open() throws SQLDataException {

        sqLiteDatabase = dbHelper.getWritableDatabase();
        Log.d("My", "database open = " + sqLiteDatabase);

    }

    public void close(){

        dbHelper.close();
        Log.d("My", "database close");

    }

    public void todo(ContentValues contentValues) throws SQLDataException{

        sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);

    }

}
