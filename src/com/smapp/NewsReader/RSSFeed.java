package com.smapp.NewsReader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/10/13
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSFeed {
    String link;
    String title;
    String description;
    String language;
    List<RSSItem> items;

    public RSSFeed() {
    }

    public RSSFeed(String link, String title, String description, String language) {
        this.link = link;
        this.title = title;
        this.description = description;
        this.language = language;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<RSSItem> getItems() {
        return items;
    }

    public void setItems(List<RSSItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "\nRSSFeed{" +
                "language='" + language + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
