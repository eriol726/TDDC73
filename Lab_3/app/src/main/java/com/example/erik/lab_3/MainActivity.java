package com.example.erik.lab_3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    String[] searchContent = {"tja", "hejsan", "a", "b", "c", "d", "e", "f", "g", "h", "i", "a", "b", "c", "d", "e", "f", "g", "h", "i"};
    String[] searchContent2 = {"NYA STRANGAR", "HEJ SAN"};
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
        customListView.populate(searchContent);
        prevArray = searchContent;


        interactiveSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();

                if(prevArray[0].contains("kalle")) {
                    prevArray = searchContent2;
                }
                else{
                    prevArray = searchContent;
                }

                customListView.populate(prevArray);

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
