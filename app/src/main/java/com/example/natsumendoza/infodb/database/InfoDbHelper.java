package com.example.natsumendoza.infodb.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by natsumendoza on 4/23/2017.
 */

public class InfoDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Info.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + InfoContract.InfoEntry.TABLE_NAME + "(" +
                    InfoContract.InfoEntry._ID + " INTEGER PRIMARY KEY," +
                    InfoContract.InfoEntry.COLUMN_NAME_NAME + " TEXT," +
                    InfoContract.InfoEntry.COLUMN_NAME_AGE + " TEXT," +
                    InfoContract.InfoEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    InfoContract.InfoEntry.COLUMN_NAME_MOBILE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + InfoContract.InfoEntry.TABLE_NAME;

    public InfoDbHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
