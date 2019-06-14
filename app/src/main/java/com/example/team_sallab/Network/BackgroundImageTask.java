package com.example.team_sallab.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

public class BackgroundImageTask extends AsyncTask<String,Void, Bitmap>
{
    private final static String TAG = "BackgroundTask";

    private BackgroundImageTaskInterface delegate;

    public BackgroundImageTask(BackgroundImageTaskInterface backgroundImageTaskInterface)
    {
        this.delegate = backgroundImageTaskInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap response = null;
        try
        {
            InputStream in = new URL(strings[0]).openStream();
            response = BitmapFactory.decodeStream(in);
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    @Override
    protected void onPostExecute(Bitmap response)
    {
        this.delegate.processFinished(response);
    }
}
