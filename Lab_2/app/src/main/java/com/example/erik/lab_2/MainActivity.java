package com.example.erik.lab_2;

import android.graphics.Color;
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
                String path = "/" + Colors_list.get(groupPosition) + "/" ;
                editText.setText(path);
            }
        });

        Exp_list.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                String path = "/" ;
                editText.setText(path);
            }
        });

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                //parent.setItemChecked(index, true);

                Log.d("tag", "childPosition: " + childPosition);
                //colorAdapter.setSelected(childPosition);
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

    public boolean searchColor(String textToSearch){
        int index =0;
        boolean flag = false;
        int adapterSize = 0;
        int falseIterations = 0;
        for(String category:Colors_list){

            if (Colors_category.get(category).contains(textToSearch)){
                Exp_list.expandGroup(index);
                //Exp_list.findViewById(0).setBackgroundColor(Color.GREEN);
                Exp_list.setItemChecked(6, true);
                Log.d("tag", "Exp_list: " + index);
                flag = true;
                String path = "/" + Colors_list.get(index) + "/" + textToSearch;
                editText.setText(path);
            }
            else{
                //Log.d("tag", "Colors_category.get(item): " +Colors_category.get(category).size());
               // Exp_list.setItemChecked(index, false);
            }
            index++;
        }

        Log.d("tag", "contains: " + colorAdapter.getAdapterSize());
        for(String category:Colors_list){
            for(int n = 0; n < Colors_category.get(category).size(); n++ ){
                if (Colors_category.get(category).get(n).contains(textToSearch)) {
                    editText.setBackgroundColor(Color.WHITE);
                }
                else{
                    falseIterations++;
                }

            }
            //if none of the items matched the search text
            if(falseIterations == colorAdapter.getAdapterSize()){
                editText.setBackgroundColor(Color.RED);
            }
        }


        return flag;
    }
}
