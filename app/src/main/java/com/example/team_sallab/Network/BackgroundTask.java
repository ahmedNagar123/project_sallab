package com.example.team_sallab.Network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

public class BackgroundTask extends AsyncTask<String,Void,String>
{
    private final static String TAG = "BackgroundTask";

    private BackgroundTaskInterface delegate;
    private Map<String, Object> values;

    public BackgroundTask(BackgroundTaskInterface backgroundTaskInterface, Map<String, Object> values)
    {
        this.delegate = backgroundTaskInterface;
        this.values = values;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = null;
        ConnectionRequest connectionRequest = new ConnectionRequest();
        try
        {
            response = connectionRequest.makeServiceCallMethodPost(strings[0],values);
        }
        catch (IOException e)
        {
            Log.e(TAG,e.getMessage());
        }

        return response;
    }


    @Override
    protected void onPostExecute(String response)
    {
        this.delegate.processFinished(response);
    }
}
