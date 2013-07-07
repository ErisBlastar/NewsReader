package com.smapp.NewsReader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/11/13
 * Time: 7:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewsChannelListAdapter extends BaseAdapter {
    List<NewsFeedLink> data;
    Context context;


    public NewsChannelListAdapter(List<NewsFeedLink> _data, Context _context) {
        this.data = _data;
        this.context = _context;
    }

    @Override
    public int getCount() {
        return data.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.newschannel_row, null);
        }
        ImageView ivNewsChanIcon = (ImageView) v.findViewById(R.id.ivNewsChanIcon);
        TextView tvNameOfChannel = (TextView) v.findViewById(R.id.tvNameOfChannel);
        TextView tvNewsFeedLink = (TextView) v.findViewById(R.id.tvNewsFeedLink);
        NewsFeedLink nfl = data.get(i);
        String name = nfl.getChannelName();
        String link = nfl.getFeedLink();
        tvNameOfChannel.setText(name);
        tvNewsFeedLink.setText(link);
        if (name.toLowerCase().contains("ndtv")) {
            ivNewsChanIcon.setImageResource(R.drawable.ndtv);
        } else if (name.toLowerCase().contains("times of india")) {
            ivNewsChanIcon.setImageResource(R.drawable.toi);
        } else if (name.toLowerCase().contains("hindu")) {
            ivNewsChanIcon.setImageResource(R.drawable.thehindu);
        } else if (name.toLowerCase().contains("yahoo")) {
            ivNewsChanIcon.setImageResource(R.drawable.ynews);
        } else if (name.toLowerCase().contains("reuter")) {
            ivNewsChanIcon.setImageResource(R.drawable.reuters);
        } else if (name.toLowerCase().contains("cnn")) {
            ivNewsChanIcon.setImageResource(R.drawable.cnn);
        } else {
            ivNewsChanIcon.setImageResource(R.drawable.icon);
        }
        return v;

    }
}
