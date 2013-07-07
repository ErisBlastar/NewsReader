package com.smapp.NewsReader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/11/13
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListOfNewsChannels extends SherlockActivity {
    List<NewsFeedLink> newsList = new ArrayList<NewsFeedLink>();
    NewsFeedLink nfl;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newschannellsit);
        nfl = new NewsFeedLink("Reuters", "http://feeds.reuters.com/reuters/INtopNews");
        newsList.add(nfl);
        nfl = new NewsFeedLink("The Hindu", "http://www.thehindu.com/news/?service=rss");
        newsList.add(nfl);
        nfl = new NewsFeedLink("Times of India- Most Recent", "http://timesofindia.feedsportal.com/c/33039/f/533916/index.rss");
        newsList.add(nfl);
        nfl = new NewsFeedLink("NDTV-Top Stories", "http://feeds.feedburner.com/NdtvNews-Topstories");
        newsList.add(nfl);
        nfl = new NewsFeedLink("CNN", "http://rss.cnn.com/rss/edition.rss");
        newsList.add(nfl);
        nfl = new NewsFeedLink("Yahoo - Science", "http://in.news.yahoo.com/rss/science");
        newsList.add(nfl);
        nfl = new NewsFeedLink("Yahoo - Internet", "http://in.news.yahoo.com/rss/internet");
        newsList.add(nfl);
        nfl = new NewsFeedLink("Yahoo - Telecom", "http://in.news.yahoo.com/rss/telecom-mobile");
        newsList.add(nfl);
        nfl = new NewsFeedLink("Times of India - Ranchi", "http://timesofindia.indiatimes.com/rssfeeds/4118245.cms");
        newsList.add(nfl);
        nfl = new NewsFeedLink("Times of India - Goa", "http://timesofindia.indiatimes.com/rssfeeds/3012535.cms");
        newsList.add(nfl);
        nfl = new NewsFeedLink("Times of India - Gurgaon", "http://timesofindia.indiatimes.com/rssfeeds/6547154.cms");
        newsList.add(nfl);
        ListView lvNewsChaList = (ListView) findViewById(R.id.lvNewsChannel);
        lvNewsChaList.setAdapter(new NewsChannelListAdapter(newsList, getApplicationContext()));
        lvNewsChaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String link = ((TextView) view.findViewById(R.id.tvNewsFeedLink)).getText().toString();
                Intent intent = new Intent(ListOfNewsChannels.this, MyActivity.class).putExtra("URL", link);
                startActivity(intent);
            }
        });
        actionBar = getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menumain,menu);
        return true;
    }
}
