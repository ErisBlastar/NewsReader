package com.smapp.NewsReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by samarth on 22/11/13.
 */
public class NewsFeedDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "feeds_meta_db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "feeds";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FEED_URL = "feedurl";
    public static final String COLUMN_FEED_TITLE = "feedtitle";
    public static final String COLUMN_FEED_CATEGORY = "category";
    private static final String TABLE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "(" + COLUMN_ID
            + " TEXT PRIMARY KEY, "
            + COLUMN_FEED_URL
            + " text not null, "
            + COLUMN_FEED_TITLE + " text, "
            + COLUMN_FEED_CATEGORY + " text);";

    public NewsFeedDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newVersion) {
        Log.w(NewsFeedDBHelper.class.getName(),
                "Upgrading database from version " + oldversion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
