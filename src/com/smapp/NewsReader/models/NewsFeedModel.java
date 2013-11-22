package com.smapp.NewsReader.models;

import com.smapp.NewsReader.utilities.EncryptionUtil;

/**
 * Created by samarth on 22/11/13.
 */
public class NewsFeedModel {
    private String url;
    private String title;
    private String category;
    private String hashVal;

    public NewsFeedModel(String url, String title, String category) {
        this.url = url;
        this.title = title;
        this.category = category;
        setHashVal();
    }

    public void setHashVal() {
        this.hashVal = String.valueOf(EncryptionUtil.hashMD5(this.url));
    }

    public String getHashKey() {
        return this.hashVal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return hashVal + " " + url + " " + title + " " + category;
    }
}
