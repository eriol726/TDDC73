package com.example.erik.lab_3;

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
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String[] searchContent = {"start"};
    String[] resultArray = {""};
    CustomListView customListView;
    InteractiveSearch interactiveSearch;
    EditText editN;
    long id = 0;

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
                    @Override
                    public void setResults(JSONObject result) {

                        try {
                            id = result.getInt("id");
                            JSONArray name = result.getJSONArray("result");
                            resultArray = name.join(",").replaceAll("\"","").split(",");
                            customListView.setN(editN.getText().toString());
                            Log.d("tag", "id: " + id);
                            customListView.populate(resultArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
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
