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
    boolean expanded = true;
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

  /*      Exp_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                String path = "/" + "Colors_list.get(groupPosition)" + "/" ;
                editText.setText(path);
                colorAdapter.setGroupIndex(groupPosition);
                colorAdapter.setSelectedChildIndex(500);
                Log.d("tag", "cant close: " );
            }
        });
       Exp_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                String path = "/"  ;

                //colorAdapter.setGroupIndex(groupPosition);
                //colorAdapter.setSelectedChildIndex(500);
                path = "/" + Colors_list.get(groupPosition) + "/" ;
                //editText.setText(path);
                expanded = Exp_list.isGroupExpanded(groupPosition);
                if(!Exp_list.isGroupExpanded(groupPosition)){
                    editText.setText(path);
                }
                else{

                }
                Log.d("tag", "cant close: " );
                return false;
            }
        });

*/


        Exp_list.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Deselect selected item in ListView
                expanded = Exp_list.isGroupExpanded(groupPosition);
                colorAdapter.setGroupIndex(groupPosition);
                //colorAdapter.setSelectedChildIndex(500);
                //Exp_list.clearChoices();
                //colorAdapter.notifyDataSetChanged();
                Log.d("tag", "expanded: " + expanded );

                if(!editText.getText().toString().equals("/")){
                    String path2 = "/" ;
                    editText.setText(path2);
                    editText.setSelection(editText.getText().length());
                }



            }
        });

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                //parent.setItemChecked(index, true);
                collapseAll();
                colorAdapter.setGroupIndex(groupPosition);
                colorAdapter.setSelectedChildIndex(childPosition);
                expanded = true;

                Log.d("tag", "childPosition: " + childPosition);

                String path = "/" + Colors_list.get(groupPosition) + "/" + Colors_category.get(Colors_list.get(groupPosition)).get(childPosition);
                editText.setText(path);
                editText.setSelection(editText.getText().length());
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
                       //collapseAll();
                       editText.setBackgroundColor(Color.RED);
                   }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().contains("/")){
                    editText.setText("/" );
                    Selection.setSelection(editText.getText(), editText.getText().length());


                }
            }
        });


    }

    public boolean searchColor(String textToSearch){
        int categoryIndex =0;
        String parent="";
        String child="";
        boolean flag = false;
        String tempTextToSearch;

        tempTextToSearch = textToSearch;
        tempTextToSearch = tempTextToSearch.substring(1,textToSearch.length());


        String[] textArr = new String[]{};
        textArr = tempTextToSearch.split("/");

        parent = textArr[0].toString();
        if(textArr.length>1){
            child = textArr[1].toString();
            Log.d("tag", "child: " + child);
        }


        Log.d("tag", "parent: " + parent);
        if(tempTextToSearch.contains("/")){
            //Log.d("tag", "tempTextToSearch: " + tempTextToSearch + " " +child);
            //tempTextToSearch = child;
        }


        List<Boolean> isContainingColor = new ArrayList<Boolean>();
        List<Boolean> isContainingCatogory = new ArrayList<Boolean>();


        for(String category:Colors_list){
            if(  tempTextToSearch.equals("") || tempTextToSearch.equals("/")  ){
                //Exp_list.collapseGroup(0);
                collapseAll();
                flag = true;
            }
            // letters in category
            //Log.d("tag", "in first loop: ");
            if (category.contains(parent)  ) {
                flag = true;
                if (category.equals(parent)) {
                    //collapseAll();

                    Log.d("tag", "in first loop: " + Colors_list.indexOf(category));
                    if(!Exp_list.isGroupExpanded(Colors_list.indexOf(category))){
                        Log.d("tag", "not expanded: " + Exp_list.isGroupExpanded(Colors_list.indexOf(category)));
                        Exp_list.expandGroup(Colors_list.indexOf(category));

                        expanded = true;
                    }else{
                        //Exp_list.collapseGroup(Colors_list.indexOf(category));
                    }

                   //String path =  "/" + Colors_list.get(categoryIndex) + "/" ;
                    //editText.setText(path);
                    //Log.d("tag", "word exist in category: " + category);
                    //editText.setSelection(editText.getText().length());
                    for (int colorIndex = 0; colorIndex < Colors_category.get(category).size(); colorIndex++) {
                        // letters in color

                        isContainingColor.add(false);
                        if (Colors_category.get(category).get(colorIndex).contains(child)) {
                            Log.d("tag", "existing");
                            flag = true;
                            isContainingColor.set(colorIndex,true);
                            if (Colors_category.get(category).get(colorIndex).equals(child)) {

                                Log.d("tag", "word exist in color: " + child + " " + categoryIndex + " " + colorIndex);
                                /*if(!Exp_list.isGroupExpanded(Colors_list.indexOf(category))){
                                    Exp_list.expandGroup(Colors_list.indexOf(category));
                                    expanded = true;
                                }*/
                                 //path = "/" + Colors_list.get(categoryIndex) + "/" + Colors_category.get(category).get(colorIndex);
                                //url = child+"/";
                                //editText.setText(path);
                                //editText.setSelection(editText.getText().length());
                                colorAdapter.setGroupIndex(categoryIndex);
                                colorAdapter.setSelectedChildIndex(colorIndex);

                            }
                            //flag = true;
                        }


                    }
                }


            }


            categoryIndex++;
        }
        for(Boolean exist:isContainingColor){
            Log.d("tag", "boolean loop: " + exist);
            if(exist){
                return true;
            }
            else{
                flag = false;
            }
        }

        return flag;
    }
    public void collapseAll(){
        for(int i= 0; i < Colors_list.size(); i++){
            Exp_list.collapseGroup(i);
            //
            Log.d("tag", "Size: " + Colors_list.size());
        }
        colorAdapter.setGroupIndex(500);
        colorAdapter.setSelectedChildIndex(500);
    }
}
