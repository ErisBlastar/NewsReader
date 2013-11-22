package com.smapp.NewsReader.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.smapp.NewsReader.NewsFeedDBHelper;
import com.smapp.NewsReader.models.NewsFeedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samarth on 22/11/13.
 */
public class NewsFeedDataSourceUtil {
    public SQLiteDatabase database;
    public NewsFeedDBHelper dbHelper;
    private String[] allColumns = {NewsFeedDBHelper.COLUMN_ID, NewsFeedDBHelper.COLUMN_FEED_URL, NewsFeedDBHelper.COLUMN_FEED_TITLE, NewsFeedDBHelper.COLUMN_FEED_CATEGORY};

    public NewsFeedDataSourceUtil(Context context) {
        dbHelper = new NewsFeedDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NewsFeedModel createNewsFeedDetail(NewsFeedModel d) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NewsFeedDBHelper.COLUMN_ID, d.getHashKey());
        values.put(NewsFeedDBHelper.COLUMN_FEED_URL, d.getUrl());
        values.put(NewsFeedDBHelper.COLUMN_FEED_TITLE, d.getTitle());
        values.put(NewsFeedDBHelper.COLUMN_FEED_CATEGORY, d.getCategory());
        long insertId = database.insert(NewsFeedDBHelper.TABLE_NAME, null,
                values);
        Log.d("INSERT DONE", insertId + "");
        database.close();
//        return d;
        database = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + NewsFeedDBHelper.TABLE_NAME + " WHERE " + NewsFeedDBHelper.COLUMN_ID + "='" + d.getHashKey() + "'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        NewsFeedModel newsfeed = cursorToFeed(cursor);
        cursor.close();
        database.close();
        return newsfeed;

    }

    public void deleteFeed(NewsFeedModel newsfeed) {
        String key = newsfeed.getHashKey();
        Log.d("DELETE FEED WITH KEY ", newsfeed.getHashKey());
        database.delete(NewsFeedDBHelper.TABLE_NAME, NewsFeedDBHelper.COLUMN_ID
                + " = " + key, null);
    }

    public List<NewsFeedModel> getAllFeeds() {
        List<NewsFeedModel> feeds = new ArrayList<NewsFeedModel>();
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NewsFeedModel feed = cursorToFeed(cursor);
            feeds.add(feed);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return feeds;
    }

    private NewsFeedModel cursorToFeed(Cursor cursor) {
        NewsFeedModel newsfeed = new NewsFeedModel(cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return newsfeed;
    }

}
