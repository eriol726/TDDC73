package com.example.erik.lab_2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Erik on 2016-11-09.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter{

    private Context context;
    private ArrayList<ParentRow> parentRowList;
    private ArrayList<ParentRow> originalList;

    public  MyExpandableListAdapter(Context context
            , ArrayList<ParentRow> originalList) {
        this.context = context;
        this.parentRowList = new ArrayList<>();
        this.parentRowList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);
    }

    @Override
    public int getGroupCount() {
        return parentRowList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentRowList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentRowList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentRowList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentRow parentRow = (ParentRow) getGroup(groupPosition);

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_row, null);
        }

        TextView heading = (TextView) convertView.findViewById(R.id.parent_text);

   /*     final TextView parentText = (TextView) convertView.findViewById(R.id.parent_text);

        parentText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d("tag", "clicked " + parentText.getText());
            }
        });*/

        heading.setText(parentRow.getName().trim());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildRow childRow = (ChildRow) getChild(groupPosition, childPosition);
        parent.getParent();
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_row, null);
        }

        ImageView childIcon = (ImageView) convertView.findViewById(R.id.child_icon);
        childIcon.setImageResource(R.mipmap.ic_launcher);

        final TextView childText = (TextView) convertView.findViewById(R.id.child_text);
        childText.setText(childRow.getText().trim());

        final TextView parentText = (TextView) parent.findViewById(R.id.parent_text);


        final View finalConvertView = convertView;


        childText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(finalConvertView.getContext()
                        , childText.getText()
                        , Toast.LENGTH_SHORT).show();
                Log.d("tag", "clicked " + "/" + parentText.getText() + "/" + childText.getText());

            }

        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public  void filterData(String query){
        query = query.toLowerCase();
        parentRowList.clear();

        if(query.isEmpty()){
            parentRowList.addAll(originalList);
        }
        else{
            Log.d("d", "submit1: ");
            for (ParentRow parentRow : originalList) {
                ArrayList<ChildRow> childList = parentRow.getChildList();
                ArrayList<ChildRow> newList = new ArrayList<ChildRow>();

                for (ChildRow childRow: childList){
                    if (childRow.getText().toLowerCase().contains(query)){
                        newList.add(childRow);
                    }
                }// end for (Child childRow: childList)
                if (newList.size() > 0){
                    ParentRow nParentRow = new ParentRow(parentRow.getName(), newList);
                    parentRowList.add(nParentRow);
                }
            }
        }
        notifyDataSetChanged();
    }
}
