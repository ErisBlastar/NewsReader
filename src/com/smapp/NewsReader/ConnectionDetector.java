package com.smapp.NewsReader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by samarth on 05/08/13.
 */
public class ConnectionDetector extends AsyncTask<Void, Void, Integer> {


    private Context context;
    private Integer status;

    public ConnectionDetector(Context context) {
        this.context = context;
        this.status = new Integer(100);

    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        System.out.println("HELLO SA: " + info[i].getDetailedState() + " " + info[i].getTypeName());
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean hasActiveInternetConnection() {
        if (isConnectingToInternet()) {
            try {
                this.status = this.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Log.d("HERE", this.status.intValue() + "");
            if (this.status.intValue() == 200) {
                Log.d("HERE", this.status.intValue() + "");
                return true;
            }

        }
        return false;

    }

    @Override
    protected Integer doInBackground(Void... params) {
        try {
            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.example.com")).openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(2000);
            urlc.connect();
            Log.d("Respose", "" + urlc.getResponseCode());
            return urlc.getResponseCode();
        } catch (IOException e) {
            System.out.println("IO Not Possible");
            return 0;

        } catch (Exception e) {
            Log.e("ERROR", "Error checking internet connection", e);
            return 0;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        this.status = integer;
    }
}
