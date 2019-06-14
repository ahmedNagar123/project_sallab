package com.example.team_sallab.Network;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by FAHMY on 05/10/2017.
 */

public class ConnectionRequest
{
    private  final String TAG = "ConnectionRequest";
    //static String JsonResponse;
    private static String json;
    // Encoding format
    private static final String charset = "UTF-8";
    // Internet Connection preRequirements for read and write Using POST method from and to server side
    private HttpURLConnection connection = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    /**
     * Making service call
     *
     * For Connections With data to be sent with url
     */
    public String makeServiceCallMethodGet(String url, Map<String, Object> values) throws IOException
    {
        try
        {
            // receiving url getData from app classes
            StringBuilder getData = new StringBuilder();
            for (Map.Entry<String,Object> param : values.entrySet())
            {
                if (getData.length() != 0)
                {
                    getData.append('&');
                }
                getData.append(URLEncoder.encode(param.getKey(), charset));
                getData.append('=');
                getData.append(URLEncoder.encode(String.valueOf(param.getValue()), charset));
            }
            String combineDataWithURL = getData.toString();
//            Log.e(TAG, URLDecoder.decode(combineDataWithURL,charset));

            // preparing connection (initialization)
            URL post_url = new URL(url.trim() + "?" + combineDataWithURL );
            connection = (HttpURLConnection)post_url.openConnection() ;
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            // proceed with completing connection request
            connection.connect();

            // requesting response code which sent from server side after initiate a connection
            int responseCode = connection.getResponseCode();

            // if there is data sent correctly we will receive response this Coming part is about how we read and parse it
            // ***** this approach works for Json *****

            // check that we received response not error log file
            // inShort Response code must be 200
            if (responseCode == HttpsURLConnection.HTTP_OK)
            {
                // get the input stream then buffer a reader to get data from response
                inputStream = new BufferedInputStream(connection.getInputStream());
                reader = new BufferedReader(new InputStreamReader(inputStream , charset));
                // string builder to transform the data content from what ever encode into string
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                // while we can read data from response lines
                while ((line = reader.readLine()) != null)
                {
                    // separate lines
                    stringBuilder.append(line).append("\n");
                }
                // get the data content (Json request)in string form and save it in json variable for future use
                json = stringBuilder.toString();
            }
            else
            {
                Log.e(TAG,"code " + responseCode);
            }
        }
        catch (Exception e)
        {
            Log.e(TAG,e.getMessage());
        }
        finally
        {
            // close every resource we have used through making a connection
            if (inputStream != null)
                inputStream.close();
            if (outputStream != null)
                outputStream.close();
            if (writer != null)
                writer.close();
            if (reader != null)
                reader.close();
            if (connection != null)
                connection.disconnect();
        }
        // return_back JSON String
        return json;
    }

    /**
     * Making service call
     *
     * For Connections With data to be sent with url
     */
    public String makeServiceCallMethodPost(String url,Map<String, Object> values ) throws IOException
    {
        try
        {
            // preparing connection (initialization)
            URL post_url = new URL(url.trim());
            connection = (HttpURLConnection)post_url.openConnection();
            connection.setRequestProperty("connection", "close");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // receiving url postData from app classes
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : values.entrySet())
            {
                if (postData.length() != 0)
                {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), charset));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), charset));
            }
            String postedDataWithURL = postData.toString();
            Log.e(TAG, URLDecoder.decode(postedDataWithURL,charset));

            // sending data to server with url connection request
            outputStream = new BufferedOutputStream(connection.getOutputStream());
            writer = new BufferedWriter(new OutputStreamWriter(outputStream , charset));
            writer.write(postedDataWithURL);
            writer.flush();

            // proceed with completing connection request
            connection.connect();

            // requesting response code which sent from server side after initiate a connection
            int responseCode = connection.getResponseCode();

            // if there is data sent correctly we will receive response this Coming part is about how we read and parse it
            // ***** this approach works for Json *****

            // check that we received response not error log file
            // inShort Response code must be 200
            if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpsURLConnection.HTTP_CREATED)
            {
                // get the input stream then buffer a reader to get data from response
                inputStream = new BufferedInputStream(connection.getInputStream());
                reader = new BufferedReader(new InputStreamReader(inputStream , charset));
                // string builder to transform the data content from what ever encode into string
                StringBuilder sb = new StringBuilder();
                String line;
                // while we can read data from response lines
                while ((line = reader.readLine()) != null)
                {
                    // separate lines
                    sb.append(line).append("\n");
                }
                // get the data content (Json request)in string form and save it in json variable for future use
                json = sb.toString();
            }
            else
            {
                Log.e(TAG,"code " + responseCode);
            }
        }
        catch (IOException e)
        {
            Log.e(TAG,"IO " + e.getMessage());
        }
        finally
        {
            // close every resource we have used through making a connection
            if (inputStream != null)
                inputStream.close();
            if (outputStream != null)
                outputStream.close();
            if (writer != null)
                writer.close();
            if (reader != null)
                reader.close();
            if (connection != null)
                connection.disconnect();
        }
        // return_back JSON String

        Log.e(TAG,json);
        return json;
    }
}
