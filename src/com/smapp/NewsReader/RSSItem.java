package com.smapp.NewsReader;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/10/13
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSItem {
    String title;
    String link;
    String description;
    String pubdate;
    String guid;

    public RSSItem() {
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPubdate() {
        return pubdate;
    }

    public String getGuid() {
        return guid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "\nITEM {\n Title: " + title + "\nLink: " + link + "\nPubDate: " + pubdate + "}\n";
    }
}
