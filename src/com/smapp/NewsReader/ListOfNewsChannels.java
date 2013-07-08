package com.smapp.NewsReader;

//import android.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

//import android.view.Menu;
//import android.view.MenuInflater;

/**
 * Created with IntelliJ IDEA.
 * User: Samarth Sinha
 * Date: 6/11/13
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListOfNewsChannels extends SherlockActivity {
    List<NewsFeedLink> newsList = new ArrayList<NewsFeedLink>();
    NewsFeedLink nfl;
    ActionBar actionBar;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newschannellsit);
        cd = new ConnectionDetector(getApplicationContext());
        nfl = new NewsFeedLink("Reuters", "http://feeds.reuters.com/reuters/INtopNews");
        newsList.add(nfl);
        nfl = new NewsFeedLink("The Hindu - Opinion", "http://www.thehindu.com/opinion/?service=rss");
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
                String header = ((TextView) view.findViewById(R.id.tvNameOfChannel)).getText().toString();
                if (cd.hasActiveInternetConnection()) {
                    Intent intent = new Intent(ListOfNewsChannels.this, MyActivity.class).putExtra("URL", link);
                    intent.putExtra("NAME", header);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), "Connect to internet first", Toast.LENGTH_LONG).show();
                }
            }
        });
        actionBar = getSupportActionBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuExit:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
