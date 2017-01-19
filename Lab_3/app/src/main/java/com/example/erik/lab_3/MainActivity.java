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

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    String[] searchContent = {"kalle", "kaka", "kul", "a", "b", "c", "d", "e", "f", "g", "h", "i", "a", "b", "c", "d", "e", "f", "g", "h", "i"};
    String[] searchContent2 = {"NYA STRANGAR", "KALLE ANKA"};
    String prevArray[];
    CustomListView customListView;
    InteractiveSearch interactiveSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        customListView = (CustomListView) findViewById(R.id.customListView);
        interactiveSearch = (InteractiveSearch) findViewById(R.id.searchBar);
       // new SearchOperation().execute("http://flask-afteach.rhcloud.com/getnames/4/Emm");
        customListView.populate(searchContent);
        prevArray = searchContent;


        interactiveSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                String[] resultArray = new String[0];
                SearchOperation searchOperation = new SearchOperation(new SearchInterface() {
                    @Override
                    public void setResults(JSONArray result) {
                        try {
                            prevArray = searchContent;
                            //resultArray = searchOperation.result.join(",").split(",");
                            Log.d("tag", "resultArray" + result.join(","));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        customListView.populate(searchContent);
                    }
                });
                searchOperation.execute("http://flask-afteach.rhcloud.com/getnames/4/" + s);

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
