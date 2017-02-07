package com.example.erik.lab_3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.erik.lab_3.R.id.customListView;

/**
 * Created by Erik on 2017-01-18.
 */
/*
public class SearchOperation extends AsyncTask<String, Void, String> {

    JSONObject result;
    private SearchInterface listener;

    public SearchOperation(SearchInterface listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        String strFileContents = "";

        try{

            url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());

            int bytesRead = -1;
            byte[] buffer = new byte[1024];
            while((bytesRead = inputStream.read(buffer)) != -1){
                strFileContents += new String(buffer,0,bytesRead);
                //Log.d("tag", "String: " + strFileContents);
            }

            inputStream.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                connection.disconnect();
            }
        }

        return strFileContents;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        JSONObject jsonObject = null;

        try{
            jsonObject = new JSONObject(s);
            result = jsonObject;

            listener.setResults(result);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
*/