package com.example.erik.lab_2;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{


    private SearchManager searchManager;
    private android.widget.SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<>();
    private MenuItem searchItem;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        parentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();

        editText = (EditText)findViewById(R.id.textSearch);





        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(s.toString().equals("")){
                   //reset listview
                   //whldispalyList();
                   TextView textView = (TextView) findViewById(R.id.textSearch);
                  // textView.setText("/");
               }
                else{
                   //perform search
                   onQueryTextChange(s.toString());
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // The app will crash if display list is not called here
        dispalyList();



        // This expands the list of continents
        expandAll();
    }
/*
    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }

        listAdapter.notifyDataSetChanged();
    }*/

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    private void dispalyList() {
        loadData();

        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new MyExpandableListAdapter(MainActivity.this, parentList);

        myList.setAdapter(listAdapter);

        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                //String child = listAdapter.getChild(groupPosition, childPosition).toString();
                //String parentRubrik = listAdapter.getGroup(groupPosition).toString();
                Log.d("tag", "onChildClick");
                String path = "/child";
                editText.setText(path);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchItem = menu.findItem(R.id.action_settings);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo
                (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();

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

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

    private void loadData(){
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;

        childRows.add(new ChildRow(R.mipmap.ic_launcher,
                "white"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher,
                "gray"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher,
                "beige"));
        parentRow = new ParentRow("light", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow(R.mipmap.ic_launcher
                , "green"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher
                , "yellow"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher
                , "red"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher
                , "blue"));
        parentRow = new ParentRow("medium", childRows);
        parentList.add(parentRow);

        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow(R.mipmap.ic_launcher
                , "black"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher
                , "brown"));
        childRows.add(new ChildRow(R.mipmap.ic_launcher
                , "purple"));
        parentRow = new ParentRow("dark", childRows);
        parentList.add(parentRow);
    }
}
