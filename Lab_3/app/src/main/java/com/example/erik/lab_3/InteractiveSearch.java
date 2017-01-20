package com.example.erik.lab_3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * TODO: document your custom view class.
 */
public class InteractiveSearch extends EditText {
    private String hint;


    public InteractiveSearch(Context context) {
        super(context);
        init(null, 0);
    }

    public InteractiveSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public InteractiveSearch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {


    }

    public void setSelectedItem(String s){

        this.setText(s);
    }

}
