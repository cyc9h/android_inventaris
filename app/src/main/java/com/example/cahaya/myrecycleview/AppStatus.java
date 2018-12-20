package com.example.cahaya.myrecycleview;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class AppStatus {
    static Context con;
    /**
     * We use this class to determine if the application has been connected to either WIFI Or Mobile
     * Network, before we make any network request to the server.
     * <p>
     * The class uses two permission - INTERNET and ACCESS NETWORK STATE, to determine the user's
     * connection stats
     */

    private static AppStatus instance = new AppStatus();
    ConnectivityManager connect;
    NetworkInfo wifiInfo, mobileInfo;
    boolean connected = false;

    public static AppStatus getInstance(Context con) {
        con = con.getApplicationContext();
        return instance;
    }

    public boolean isOnline() {
        try {
            connect = (ConnectivityManager) con
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo ni = connect.getActiveNetworkInfo();
            connected = ni != null && ni.isAvailable() &&
                    ni.isConnected();
            return connected;

        } catch (Exception ex) {
            System.out.println("CheckConnectivity Exception: " + ex.getMessage());
            Log.v("connectivity", ex.toString());
        }
        return connected;
    }
}
