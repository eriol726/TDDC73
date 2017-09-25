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
import java.util.List;

/**
 * Created by Erik on 2017-01-17.
 */

public class CustomListView extends View {

    Paint backgroundPaint, textPaint, linePaint;
    private GestureDetectorCompat gestureDetect;

    private SelectIntreface mlistener;

    private boolean listSizeChanged = false;

    private boolean isTouch = false;

    ArrayList resultArray = new ArrayList();
    List<ItemInfo> itemArray = new ArrayList();

    String[] mData = {};
    private int listSize = 2;

    public void selected(SelectIntreface listener){
         mlistener = listener;
    }


    public CustomListView(Context context, AttributeSet attrs){
        super(context, attrs);

        init();
    }

    // sets how many results should be visible
    public int setN(String N){
        if(N.equals("")){
            listSize = 0;
        }
        else{
            listSize = Integer.parseInt(N);
        }

        return listSize;
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

    // adding and drwing the search results in the list
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int itemHeight = 100;
        float width = 1000.0f;
        float nextLineHeight = 0;
        float firstLineHeight = 0;

        Log.d("tag", "listSize before: " + listSize );
        if(listSize != 0 && listSize<mData.length){
            listSize = listSize;
        }
        else if(listSize>mData.length){
            listSize = mData.length;
        }
        else{
            listSize = mData.length;
        }
        itemArray.clear();

        Log.d("tag", "listSize after: " + listSize );
        Log.d("tag", "mData.length: " + mData.length );
        for (int i = 0; i < listSize; i++){
            String name = mData[i];

            resultArray.add(name);
            firstLineHeight = i*itemHeight;
            nextLineHeight = firstLineHeight + itemHeight;

            ItemInfo itemInfo = new ItemInfo(i,name,firstLineHeight,nextLineHeight);
            itemArray.add(itemInfo);

            canvas.drawRect(0.0f,firstLineHeight,width,nextLineHeight, backgroundPaint);
            canvas.drawText(name,0,nextLineHeight-20,textPaint);

            if(i>0){
                canvas.drawLine(0.0f, 0.0f, width, 0.0f, linePaint);
                canvas.drawLine(0.0f, firstLineHeight, width, firstLineHeight, linePaint);
            }
        }


    }

    public void populate(String[] tempArr){
        mData = tempArr;
        invalidate();
    }

    //if item is tuched in the list, set the search field to its name
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("tag", "event" + event);
        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:

                for (int i = 0; i < listSize; i++ ){

                    if(itemArray.get(i).minY < Y && itemArray.get(i).maxY > Y){
                        mlistener.setSelected(itemArray.get(i).name.toString());
                       // Log.d("tag", "Name: " + itemArray.get(i).name);
                    }

                    isTouch = true;
                }

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


class ItemInfo {
    public int id;
    public String name;
    public float minY, maxY;

    public ItemInfo(int id, String name, float minY, float maxY){
        this.id = id;
        this.name = name;
        this.minY = minY;
        this.maxY = maxY;

    }
}