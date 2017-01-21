package com.example.erik.lab_3;

import android.os.AsyncTask;
import android.util.Log;

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

public class SearchOperation extends AsyncTask<String, Void, String> {

    JSONObject result;
    private SearchInterface listener;

    public SearchOperation(SearchInterface listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;

        byte[] contents = new byte[1024];
        int bytesRead = 0;
        String strFileContents = "";

        try{

            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());

            while((bytesRead = in.read(contents)) != -1){
                strFileContents += new String(contents,0,bytesRead);
                //Log.d("tag", "String: " + strFileContents);
            }

            in.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }

        return strFileContents;
    }

    private String loadJSON(String jsonURL) throws  IOException {
        URL url = new URL(jsonURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while((line = in.readLine()) != null){
            response.append(line);
        }

        in.close();

        return response.toString();
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        JSONObject jsonObject = null;

        try{
            jsonObject = new JSONObject(s);
            //Log.d("Tag", "JSONObj: " + jsonObject.get("result"));
            result = jsonObject;
            listener.setResults(result);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
