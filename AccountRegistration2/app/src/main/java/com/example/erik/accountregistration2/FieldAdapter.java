package com.example.erik.accountregistration2;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Erik on 2017-02-09.
 */

public class FieldAdapter  {

    private Context context;
    private ArrayList<View> fieldContent;

    public FieldAdapter(Context c){
        context = c;
        fieldContent = new ArrayList<View>();

    }

    public void addFiled(View view){
        fieldContent.add(view);
    }

    public int getSize(){
        return fieldContent.size();
    }

    public View getField(int index){
        return fieldContent.get(index);
    }




}
