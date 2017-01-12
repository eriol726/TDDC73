package com.example.erik.lab_2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
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
    String url = "";
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
        //editText.setText("/");
        editText.setSelection(editText.getText().length());

        Exp_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                String path = "/" + Colors_list.get(groupPosition) + "/" ;
                editText.setText(path);
                editText.setBackgroundColor(Color.WHITE);
            }
        });

        Exp_list.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Deselect selected item in ListView
                colorAdapter.setGroupIndex(groupPosition);
                colorAdapter.setSelectedChildIndex(500);
                //Exp_list.clearChoices();
                //colorAdapter.notifyDataSetChanged();
                String path = "/" ;
                editText.setText(path);
            }
        });

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                //parent.setItemChecked(index, true);
                colorAdapter.setGroupIndex(groupPosition);
                colorAdapter.setSelectedChildIndex(childPosition);

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
                    String str = s.toString();
                   if(searchColor(str) ){
                       editText.setBackgroundColor(Color.WHITE);
                   }
                    else{
                       editText.setBackgroundColor(Color.RED);
                   }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if(!s.toString().contains("/")){
                    editText.setText("/" );
                    Selection.setSelection(editText.getText(), editText.getText().length());

                }*/
            }
        });
    }

    public boolean searchColor(String textToSearch){
        int categoryIndex =0;
        int falseIterations = 0;
        int colorIndexTotal = 0;
        boolean flag = false;
        String tempTextToSearch;


        tempTextToSearch = textToSearch;
            // Log.d("tag", "searchText: " + textToSearch);
        for(String category:Colors_list){
            if(tempTextToSearch.equals("") || tempTextToSearch.equals("/")){
                flag = true;
            }
            // letters in category
            Log.d("tag", "category: " + textToSearch);
            if (category.contains(tempTextToSearch)  ) {
                if (category.equals(tempTextToSearch)) {
                    Log.d("tag", "word exist in category: " + Colors_list.size());
                    collapseAll();
                    Exp_list.expandGroup(Colors_list.indexOf(category));
                    // String path =  tempTextToSearch ;
                    //editText.setText(path);

                }
                flag = true;
            }

            for(int colorIndex = 0; colorIndex < Colors_category.get(category).size(); colorIndex++ ){
                // letters in color
                if (Colors_category.get(category).get(colorIndex).contains(tempTextToSearch)  ) {
                    if(Colors_category.get(category).get(colorIndex).equals(tempTextToSearch)){
                        collapseAll();
                        Exp_list.expandGroup(Colors_list.indexOf(category));
                        String path =  Colors_list.get(categoryIndex) + "/" + tempTextToSearch;
                        editText.setText(path);
                        colorAdapter.setGroupIndex(categoryIndex);
                        colorAdapter.setSelectedChildIndex(colorIndex);
                    }
                    flag = true;
                }
                else{
                    falseIterations++;
                }

            }
            categoryIndex++;
        }


        return flag;
    }
    public void collapseAll(){
        for(int i= 0; i < Colors_list.size(); i++)
            Exp_list.collapseGroup(i);
    }
}
