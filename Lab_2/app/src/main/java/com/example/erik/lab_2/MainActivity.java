package com.example.erik.lab_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HashMap<String,List<String>> Colors_category;
    List<String> Colors_list;
    ExpandableListView Exp_list;
    ColorAdapter colorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        Colors_category = DataProvider.getInfo();
        Colors_list = new ArrayList<String>(Colors_category.keySet());
        //intialize the list
        colorAdapter = new ColorAdapter(this, Colors_category, Colors_list);
        Exp_list.setAdapter(colorAdapter);

        Exp_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getBaseContext(), Colors_list.get(groupPosition) + " is expanded",
                        Toast.LENGTH_LONG).show();
            }
        });

        Exp_list.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getBaseContext(), Colors_list.get(groupPosition) + " is collapsed",
                        Toast.LENGTH_LONG).show();
            }
        });

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getBaseContext(),
                        Colors_category.get(Colors_list.get(groupPosition)).get(childPosition) + " from category " +
                        Colors_list.get(groupPosition) + " is selected ",
                        Toast.LENGTH_LONG).show();

                return false;
            }
        });
    }
}
