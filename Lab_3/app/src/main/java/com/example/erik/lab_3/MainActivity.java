package com.example.erik.lab_3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String[] searchContent = {"start"};
    String[] resultArray = {""};
    CustomListView customListView;
    InteractiveSearch interactiveSearch;
    EditText editN;
    long id = 0;
    JSONObject resultMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        customListView = (CustomListView) findViewById(R.id.customListView);
        interactiveSearch = (InteractiveSearch) findViewById(R.id.searchBar);
        editN = (EditText) findViewById(R.id.N);

        customListView.populate(searchContent);
        customListView.setN("1");


        customListView.selected(new SelectIntreface() {
            @Override
            public void setSelected(String item) {
                interactiveSearch.setSelectedItem(item);
            }
        });

        interactiveSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                SearchOperation searchOperation = new SearchOperation(new SearchInterface() {
                    // runs on onPostExecute
                    @Override
                    public void setResults(JSONObject result) {

                        try {
                            id = result.getInt("id");
                            JSONArray name = result.getJSONArray("result");
                            // separates all result name from the JSON-request into an add it into an array
                            resultArray = name.join(",").replaceAll("\"","").split(",");
                            customListView.setN(editN.getText().toString());
                            Log.d("tag", "id: " + id);
                            customListView.populate(resultArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
                // everything in AsyncTask is executed
                searchOperation.execute("http://flask-afteach.rhcloud.com/getnames/" + id + "/" + s);

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("tag", "N change: " + editN.getText().toString());

                    customListView.setN(editN.getText().toString());
                    customListView.populate(resultArray);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class SearchOperation extends AsyncTask<String, Void, String> {

    JSONObject result;
    private SearchInterface listener;

    public SearchOperation(SearchInterface listener){
        this.listener = listener;
    }
    // background thread
    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        String strFileContents = "";
        // https://www.youtube.com/watch?v=iTBnuCYeq3E
        // https://www.youtube.com/watch?v=5HDr9FdGIVg&t=103s
        //obtain connection
        try{
            // Params is the name download URL from SetResult, the type of the parameters sent to the task upon execution.
            url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            // we dont want to tread bytes one by one, creates an 1024 byte buffer instead
            int bytesRead = -1;
            byte[] buffer = new byte[1024];
            // as loga as inputStream is not -1 there is something to read
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
                // to save resources
                connection.disconnect();
            }
        }
        Log.d("tag", "strFileContents: " + strFileContents);
        //returns the whole array containing all names that matches the search characters
        return strFileContents;
    }
    // main thread when everything is over
    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        JSONObject jsonObject = null;

        try{
            // Result, the type of the result of the background computation.
            jsonObject = new JSONObject(s);
            result = jsonObject;
            //CustomListView.;
            //ArrayAdapter<MainActivity> = new ArrayAdapter<MainActivity>(MainActivity.this.getApplicationContext(),0);


            listener.setResults(result);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}