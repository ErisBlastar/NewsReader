package com.smapp.NewsReader;


import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

//import com.actionbarsherlock.app.ActionBar;
//import com.actionbarsherlock.app.SherlockActivity;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/11/13
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebPageActivity extends SherlockActivity {
    String url;
    static WebView wvMainLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpageview);
        url = getIntent().getStringExtra("LINK");
        wvMainLink = (WebView) findViewById(R.id.wvMainLink);
        wvMainLink.setWebViewClient(new WebViewClient());
        wvMainLink.loadUrl(url);
        //wvMainLink.setVisibility(View.VISIBLE);
        //new LoadPages().execute(url);
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
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
