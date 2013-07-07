package com.smapp.NewsReader;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/11/13
 * Time: 7:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewsFeedLink {
    String channelName;
    String feedLink;

    public NewsFeedLink(String channelName, String feedLink) {
        this.channelName = channelName;
        this.feedLink = feedLink;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getFeedLink() {
        return feedLink;
    }

    public void setFeedLink(String feedLink) {
        this.feedLink = feedLink;
    }
}
