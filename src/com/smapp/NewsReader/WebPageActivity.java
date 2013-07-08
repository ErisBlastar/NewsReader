package com.smapp.NewsReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//import com.actionbarsherlock.app.ActionBar;
//import com.actionbarsherlock.app.SherlockActivity;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/11/13
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebPageActivity extends Activity {
   // ActionBar actionbar;
    String url;
    WebView wvMainLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpageview);
        ProgressDialog dialog= new ProgressDialog(WebPageActivity.this);
        url = getIntent().getStringExtra("LINK");
        //Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
        wvMainLink = (WebView) findViewById(R.id.wvMainLink);
        wvMainLink.setWebViewClient(new WebViewClient());
         wvMainLink.loadUrl(url);
        //actionbar = getSupportActionBar();
        dialog.dismiss();

    }
}
