package com.example.team_sallab.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by FAHMY on 10/06/2015.
 */
public class NetworkStatusAndType
{
    private Context _context;
    private static String TYPE_WIFI = "WIFI";
    private static String TYPE_MOBILE = "DATA";
    private static String TYPE_NOT_CONNECTED = "NONE";
    private static int state1 = 0;
    private static int state2 = 1;
    private static int state3 = 2;

    public NetworkStatusAndType(Context context)
    {
        this._context = context;
    }
    /**
     *  Check if there is any network connection connected
     *  WIFI , WIFI P2P (WIFI hot spot) and Mobile Data
     */
    public String NetworkType()
    {
        ConnectivityManager connectivity = (ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if(activeNetwork != null)
            {
                if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                    return TYPE_WIFI;

                if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                    return TYPE_MOBILE;
            }
        }
        return TYPE_NOT_CONNECTED;
    }

    public int NetworkStatus()
    {
        ConnectivityManager connectivity = (ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if(activeNetwork != null)
            {
                if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    if(activeNetwork.isConnected())
                    {
                        return state3;
                    }
                    else if(activeNetwork.isConnectedOrConnecting())
                    {
                        return state2;
                    }
                    else
                    {
                        return state1;
                    }
                }

                if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    if(activeNetwork.isConnected())
                    {
                        return state3;
                    }
                    else if(activeNetwork.isConnectedOrConnecting())
                    {
                        return state2;
                    }
                    else
                    {
                        return state1;
                    }
                }
            }
        }
        return state1;
    }

}
