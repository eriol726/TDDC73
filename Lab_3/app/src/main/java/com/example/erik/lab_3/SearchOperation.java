package com.example.erik.lab_3;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Erik on 2017-01-18.
 */

public class SearchOperation extends AsyncTask<String, Void, String> {


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
                Log.d("tag", "String: " + strFileContents);
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

        return null;
    }
}
