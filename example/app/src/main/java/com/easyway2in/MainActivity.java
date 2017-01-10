package com.easyway2in;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;


public class MainActivity extends Activity {
	HashMap<String, List<String>> Movies_category;
	List<String> Movies_list;
	ExpandableListView Exp_list;
	MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        Movies_category = DataProvider.getInfo();
        Movies_list = new ArrayList<String>(Movies_category.keySet());
        adapter = new MoviesAdapter(this, Movies_category, Movies_list);
        Exp_list.setAdapter(adapter);
    }


   
}
