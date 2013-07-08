package com.smapp.NewsReader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrwho
 * Date: 6/11/13
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ItemsArrayAdapter extends BaseAdapter implements Filterable {
    private List<RSSItem> data;
    private List<RSSItem> publishedData;
    private Context context;

    public ItemsArrayAdapter(List<RSSItem> dat, Context cont) {
        this.data = dat;
        this.publishedData = dat;
        this.context = cont;
    }

    public void setData(List<RSSItem> da) {
        data = da;
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
            v = vi.inflate(R.layout.list_row, null);
        }
        ImageView image = (ImageView) v.findViewById(R.id.ivIcons);
        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        TextView tvPubDate = (TextView) v.findViewById(R.id.tvPubDate);
        TextView tvLink = (TextView) v.findViewById(R.id.tvLink);
        TextView tvGuid = (TextView) v.findViewById(R.id.tvGuid);
        TextView tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        //WebView wvDesc = (WebView) v.findViewById(R.id.wvDesc);

        RSSItem item = data.get(i);
        image.setImageResource(R.drawable.icon);
        tvTitle.setText(item.getTitle());
        tvPubDate.setText(item.getPubdate());
        tvLink.setText(item.getLink());
        tvGuid.setText(item.getGuid());
        tvDesc.setText(item.getDescription());
        // wvDesc.getSettings().setJavaScriptEnabled(false);
        //wvDesc.loadData(item.getDescription(),"text/html","UTF-8");
        //wvDesc.(item.getDescription());
        return v;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<RSSItem> FilteredList = new ArrayList<RSSItem>();

                if (data == null) {
                    data = new ArrayList<RSSItem>(publishedData);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = data.size();
                    results.values = data;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < data.size(); i++) {
                        String d = data.get(i).getDescription();
                        String t = data.get(i).getTitle();
                        if (d.toLowerCase().contains(constraint.toString()) || t.toLowerCase().contains(constraint.toString())) {
                            FilteredList.add(data.get(i));
                        }

                    }
                    results.count = FilteredList.size();
                    results.values = FilteredList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (List<RSSItem>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
