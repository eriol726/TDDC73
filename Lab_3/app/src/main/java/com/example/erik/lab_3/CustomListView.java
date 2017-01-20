package com.example.erik.lab_3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Erik on 2017-01-17.
 */

public class CustomListView extends View {

    Paint backgroundPaint, textPaint, linePaint;
    private GestureDetectorCompat gestureDetect;

    private SelectIntreface mlistener;

    private boolean isTouch = false;

    ArrayList resultArray = new ArrayList();

    String[] mData;

    public void selected(SelectIntreface listener){
         mlistener = listener;
    }


    public CustomListView(Context context, AttributeSet attrs){
        super(context, attrs);


          /*
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomListView,
                0,0);*/

        init();


    }

    private void init(){
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60.0f);

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(Color.WHITE);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLUE);
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int itemHeight = 100;
        float width = 1000.0f;

        for (int i = 0; i < mData.length; i++){
            String name = mData[i];
            resultArray.add(name);
            float height = i * itemHeight;
            canvas.drawRect(0,height,width,height + itemHeight, backgroundPaint);
            canvas.drawText(name,0,height+itemHeight-20,textPaint);

            if(i>0){
                canvas.drawLine(10.0f, height, width, height, linePaint);
            }
        }

    }

    public void populate(String[] tempArr){
        mData = tempArr;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("tag", "event" + event);
        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                if(Y < 90 && X > 10){
                   mlistener.setSelected(resultArray.get(0).toString());
                    Log.d("tag", "Name: " + resultArray.get(0));
                }

                isTouch = true;
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d("tag", "MOVE "+"X: "+X+" Y: "+Y);
                break;

            case MotionEvent.ACTION_UP:
                Log.d("tag", "ACTION_UP "+"X: "+X+" Y: "+Y);
                break;
        }

        return true;
    }
}
