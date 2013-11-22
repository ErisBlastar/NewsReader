package com.smapp.NewsReader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.smapp.NewsReader.models.NewsFeedModel;
import com.smapp.NewsReader.utilities.NewsFeedDataSourceUtil;

/**
 * Created by samarth on 22/11/13.
 */
public class AddFeedsActivity extends SherlockActivity {
    EditText etRssUrl, etTitleFeed, etCateogryFeeds;
    Button btnAddFeed;
    ActionBar actionBar;
    static NewsFeedModel newsfeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfeedurl);
        etRssUrl = (EditText) findViewById(R.id.etRssUrl);
        etTitleFeed = (EditText) findViewById(R.id.etTitleFeed);
        etCateogryFeeds = (EditText) findViewById(R.id.etCategoryFeed);
        btnAddFeed = (Button) findViewById(R.id.btnAdd);
        btnAddFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = etRssUrl.getText().toString();
                String title = etTitleFeed.getText().toString();
                String category = etCateogryFeeds.getText().toString();
                if (url.isEmpty()) {
                    etRssUrl.setError("URL Required");
                    return;
                }
                if (title.isEmpty()) {
                    etTitleFeed.setError("Title required");
                    return;
                }

                category = (category == null || category.isEmpty()) ? "*" : category;
                newsfeed = new NewsFeedModel(url, title, category);
                NewsFeedDataSourceUtil nfds = new NewsFeedDataSourceUtil(getApplicationContext());
                nfds.createNewsFeedDetail(newsfeed);
                Intent i = new Intent(AddFeedsActivity.this, ListOfNewsChannels.class);
                startActivity(i);

            }
        });

        actionBar = getSupportActionBar();
    }
}
