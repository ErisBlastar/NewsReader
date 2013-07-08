package com.smapp.NewsReader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samarth on 05/08/13.
 */
public class ConnectionDetector {


    private Context context;

    public ConnectionDetector(Context context) {
        this.context = context;

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
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.example.com")).openConnection();
                urlc.setRequestProperty("User-Agent", "Mozilla");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(2000);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                System.out.println("IO Not Possible");
                return false;

            } catch (Exception e) {
                System.out.println("Something bad happened with connection");
                return false;
            }
        } else {
            System.out.println("No Network Available");
        }
        return false;

    }

}
