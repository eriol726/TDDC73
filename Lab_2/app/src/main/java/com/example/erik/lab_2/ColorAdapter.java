package com.example.erik.lab_2;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Erik on 2017-01-10.
 */

public class ColorAdapter extends BaseExpandableListAdapter {
    private Context Ctx;
    private HashMap<String, List<String>> Colors_category;
    private List<String> Colors_list;

    public ColorAdapter(Context ctx, HashMap<String, List<String>> colors_category, List<String> colors_list){
        this.Ctx = ctx;
        this.Colors_category = colors_category;
        this.Colors_list = colors_list;
    }

    @Override
    public int getGroupCount() {
        return Colors_list.size();
    }

    @Override
    public int getChildrenCount(int arg0) {
        return Colors_category.get(Colors_list.get(arg0)).size();
    }

    @Override
    public Object getGroup(int arg0) {
        return Colors_list.get(arg0);
    }

    @Override
    public Object getChild(int parent, int child) {
        return Colors_category.get(Colors_list.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int parentPos, int childPos) {
        return childPos;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String group_title = (String)getGroup(parent);

        if(convertView == null){
            LayoutInflater inflator = (LayoutInflater) Ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.parent_layout, parentView, false);
        }
        TextView parent_textview = (TextView) convertView.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        //returning the view with updated catogries (parents)
        return convertView;
    }

    @Override
    //return a view for each sub-category
    public View getChildView(int parent, int child, boolean lastChild, View convertView, ViewGroup parentView) {

        String child_title = (String)getChild(parent,child);
        //check if there is no view avable for the current action
        if(convertView == null){
            LayoutInflater inflator = (LayoutInflater) Ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.child_layout, parentView, false);
        }
        TextView child_textview = (TextView) convertView.findViewById(R.id.child_txt);
        child_textview.setText(child_title);

        //returning the view with updated sub-catogries (childs)
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
