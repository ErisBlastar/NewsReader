package com.smapp.NewsReader;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MyActivity extends SherlockActivity {
    /**
     * Called when the activity is first created.
     */
    ActionBar actionBar;
    TextView tvTest;
    List<RSSFeed> rssFeed;
    RSSHandler handler;
    // String url = "http://192.168.1.2/image_of_the_day.rss";
    static String url = "http://timesofindia.feedsportal.com/c/33039/f/533923/index.rss";
    String channelName;
    ListView itemListView;
    static ItemsArrayAdapter iteAdapter;
    EditText etUrl, etSearch;
    Button bGo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newslayout);
        url = getIntent().getStringExtra("URL");
        channelName = getIntent().getStringExtra("NAME");
        etUrl = (EditText) findViewById(R.id.etURL);
        bGo = (Button) findViewById(R.id.bGo);
        etSearch = (EditText) findViewById(R.id.etSearch);

        itemListView = (ListView) findViewById(R.id.lvItems);
        handler = new RSSHandler();
        actionBar = getSupportActionBar();
        actionBar.setTitle(channelName);
        new connectToInternet().execute(url);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(MyActivity.this,((TextView) view.findViewById(R.id.tvLink)).getText() + " POSITION" + i,Toast.LENGTH_LONG).show();
                TextView tvLink = (TextView) view.findViewById(R.id.tvLink);
                Intent intent = new Intent(MyActivity.this, WebPageActivity.class).putExtra("LINK", tvLink.getText());
                startActivity(intent);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MyActivity.this.iteAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.refreshmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRefresh:
                new connectToInternet().execute(url);
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public class connectToInternet extends AsyncTask<String, List<RSSItem>, List<RSSItem>> {
        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MyActivity.this);
            dialog.setMessage("Loading News...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.show();
        }

        @Override
        protected List<RSSItem> doInBackground(String... strings) {
            StringBuilder result = null;
            rssFeed = null;
            List<RSSItem> items = null;
            try {
                rssFeed = handler.processWithParsing(strings[0]);
                Iterator<RSSFeed> iter = rssFeed.iterator();
                StringBuilder sb = new StringBuilder("");
                while (iter.hasNext()) {
                    RSSFeed rsf = iter.next();
                    items = rsf.getItems();
                    publishProgress(items);
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return items;
            }
        }

        @Override
        protected void onProgressUpdate(List<RSSItem>... values) {
            iteAdapter = new ItemsArrayAdapter(values[0], getApplicationContext());
            itemListView.setAdapter(new ItemsArrayAdapter(values[0], getApplicationContext()));
            dialog.dismiss();
        }

        @Override
        protected void onPostExecute(List<RSSItem> s) {
            //dialog.dismiss();


        }
    }
}
