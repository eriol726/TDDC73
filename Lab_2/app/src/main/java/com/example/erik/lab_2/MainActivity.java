package com.example.erik.lab_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    EditText editText;

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

        editText = (EditText) findViewById(R.id.serchtext);

        Exp_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getBaseContext(), Colors_list.get(groupPosition) + " is expanded",
                        Toast.LENGTH_LONG).show();
                String path = "/" + Colors_list.get(groupPosition) + "/" ;
                editText.setText(path);
            }
        });

        Exp_list.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getBaseContext(), Colors_list.get(groupPosition) + " is collapsed",
                        Toast.LENGTH_LONG).show();
                String path = "/" + Colors_list.get(groupPosition) + "/" ;
                editText.setText(path);
            }
        });

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getBaseContext(),
                        Colors_category.get(Colors_list.get(groupPosition)).get(childPosition) + " from category " +
                        Colors_list.get(groupPosition) + " is selected ",
                        Toast.LENGTH_LONG).show();
                String path = "/" + Colors_list.get(groupPosition) + "/" + Colors_category.get(Colors_list.get(groupPosition)).get(childPosition);
                editText.setText(path);

                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals("")){
                    searchColor(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void searchColor(String textToSearch){
        for(String item:Colors_list){
            Log.d("tag", "for loop: " + Colors_category.get(item));
            if (Colors_category.get(item).contains(textToSearch)){
                Log.d("tag", "found: " + textToSearch);
            }
        }

        colorAdapter.notifyDataSetChanged();
    }
}
