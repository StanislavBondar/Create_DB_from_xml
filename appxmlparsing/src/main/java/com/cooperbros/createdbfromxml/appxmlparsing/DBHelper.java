package com.cooperbros.createdbfromxml.appxmlparsing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * DB helper
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_TABLE = "country";
    private static final String COUNTRY_COLUMN = "country_name";
    private static final String CITY_COLUMN = "city_name";
    private static final String CITY_ID = "city_id";
    private final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + COUNTRY_COLUMN
            + " text not null, " + CITY_COLUMN + " text not null, "
            + CITY_ID + " integer);";

    public DBHelper(Context context) {
        super(context, "my_db.sqlite", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_SCRIPT);
        Log.d("My", "DB Create");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(newVersion > oldVersion) {

            db.execSQL("DROP TABLE IF IT EXIST" + DATABASE_TABLE);
            onCreate(db);

        }
    }
}