package com.example.erik.lab1_1_java;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.graphics.Color;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.util.Log;
import android.text.method.PasswordTransformationMethod;


public class MainActivity extends AppCompatActivity {

    // lab1.3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = new LinearLayout(this);

        LinearLayout.LayoutParams linearLayoutParam =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        RelativeLayout relativeLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams relativeLayoutParam =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);

        //myLayout.setOrientation(RelativeLayout.VERTICAL);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Laboration 1.3");
        LinearLayout.LayoutParams toolbarParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        toolbar.setBackgroundColor(Color.parseColor("#78909C"));
        setSupportActionBar(toolbar);

        TextView question1, question2, question3;
        question1 = new TextView(this);
        question1.setText("Hur trivs du på LiU ?");
        question2 = new TextView(this);
        question2.setText("Läser du på LiTH ?");
        question3 = new TextView(this);
        question3.setText("Är detta LiUs logotyp ?");

        toolbar.setId(0);
        question1.setId(1);
        question2.setId(2);
        question3.setId(4);


        RelativeLayout.LayoutParams question1Param =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        question1Param.addRule(RelativeLayout.BELOW, toolbar.getId());
        question1Param.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        CheckBox goodBox, veryGoodBox, greatBox, yesBox, noBox, yesBox2, noBox2;
        Button submit;
        submit = new Button(this);
        submit.setText("Skicka in");

        ImageView logo = new ImageView(this);

        logo.setImageResource(R.drawable.logga);


        goodBox = new CheckBox(this);
        veryGoodBox = new CheckBox(this);
        greatBox = new CheckBox(this);
        yesBox = new CheckBox(this);
        noBox = new CheckBox(this);
        yesBox2 = new CheckBox(this);
        noBox2 = new CheckBox(this);

        goodBox.setText("Bra");
        veryGoodBox.setText("Mycket bra");
        greatBox.setText("Jättebra");
        yesBox.setText("Ja");
        yesBox2.setText("Ja");
        noBox.setText("Nej");
        noBox2.setText("Nej");

        goodBox.setId(5);
        veryGoodBox.setId(6);
        yesBox.setId(7);
        logo.setId(8);
        yesBox2.setId(9);

        RelativeLayout.LayoutParams goodBoxParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        goodBoxParams.addRule(RelativeLayout.BELOW, question1.getId());
        goodBoxParams.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);


        RelativeLayout.LayoutParams veryGoodBoxParam =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        veryGoodBoxParam.addRule(RelativeLayout.BELOW, question1.getId());
        veryGoodBoxParam.addRule(RelativeLayout.RIGHT_OF, goodBox.getId());

        RelativeLayout.LayoutParams greatBoxParam =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        greatBoxParam.addRule(RelativeLayout.BELOW, question1.getId());
        greatBoxParam.addRule(RelativeLayout.RIGHT_OF, veryGoodBox.getId());

        RelativeLayout.LayoutParams question2Param =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        question2Param.addRule(RelativeLayout.BELOW, goodBox.getId());
        question2Param.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        RelativeLayout.LayoutParams yesBoxParam =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        yesBoxParam.addRule(RelativeLayout.BELOW, question2.getId());
        yesBoxParam.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);


        RelativeLayout.LayoutParams noBoxParam =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        noBoxParam.addRule(RelativeLayout.BELOW, question2.getId());
        noBoxParam.addRule(RelativeLayout.RIGHT_OF, yesBox.getId());

        RelativeLayout.LayoutParams logoParam =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        logoParam.height = 500;
        logoParam.addRule(RelativeLayout.BELOW, yesBox.getId());
        logoParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.ALIGN_PARENT_TOP);

        RelativeLayout.LayoutParams question3Param =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        question3Param.topMargin =20;

        question3Param.addRule(RelativeLayout.BELOW, logo.getId());
        question3Param.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        RelativeLayout.LayoutParams yesBox2Param =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        yesBox2Param.addRule(RelativeLayout.BELOW, question3.getId());
        yesBox2Param.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);

        RelativeLayout.LayoutParams noBox2Param =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        noBox2Param.addRule(RelativeLayout.BELOW, question3.getId());
        noBox2Param.addRule(RelativeLayout.RIGHT_OF, yesBox2.getId());

        RelativeLayout.LayoutParams buttonParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        buttonParam.addRule(RelativeLayout.BELOW, yesBox2.getId());
        buttonParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);



        linearLayout.addView(toolbar, toolbarParams);
        relativeLayout.addView(question1, question1Param);
        relativeLayout.addView(goodBox, goodBoxParams);
        relativeLayout.addView(veryGoodBox, veryGoodBoxParam);
        relativeLayout.addView(greatBox, greatBoxParam);
        relativeLayout.addView(question2, question2Param);
        relativeLayout.addView(yesBox, yesBoxParam);
        relativeLayout.addView(noBox, noBoxParam);
        relativeLayout.addView(logo, logoParam);
        relativeLayout.addView(question3, question3Param);
        relativeLayout.addView(yesBox2, yesBox2Param);
        relativeLayout.addView(noBox2, noBox2Param);
        relativeLayout.addView(submit, buttonParam);
        linearLayout.addView(relativeLayout);
        setContentView(linearLayout);
    }

    // Lab1.2

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout myLayout = new LinearLayout(this);
        myLayout.setOrientation(LinearLayout.VERTICAL);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Laboration 1.2");
        LinearLayout.LayoutParams toolbarParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        toolbar.setBackgroundColor(Color.parseColor("#78909C"));
        setSupportActionBar(toolbar);

        GridLayout gridLayout = new GridLayout(this);

        int total = 6;
        int column = 2;
        int row = total / column;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);

        TextView textName = new TextView(this);
        textName.setText("Namn");
        //rad 0
        GridLayout.LayoutParams param00 =new GridLayout.LayoutParams();
        param00.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        param00.columnSpec = GridLayout.spec(0);
        param00.rowSpec = GridLayout.spec(0);

        EditText editName = new EditText(this);


        GridLayout.LayoutParams param01 =new GridLayout.LayoutParams();
        editName.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        param01.width = LinearLayout.LayoutParams.MATCH_PARENT;
        param01.columnSpec = GridLayout.spec(1);
        param01.rowSpec = GridLayout.spec(0);

        //rad 1
        TextView textPassword = new TextView(this);
        textPassword.setText("Lösenord");

        GridLayout.LayoutParams param10 =new GridLayout.LayoutParams();
        param10.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        param10.columnSpec = GridLayout.spec(0);
        param10.rowSpec = GridLayout.spec(1);

        EditText editPassword = new EditText(this);
        editPassword.setTransformationMethod(new PasswordTransformationMethod());

        GridLayout.LayoutParams param11 =new GridLayout.LayoutParams();
        editName.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        param11.width = LinearLayout.LayoutParams.MATCH_PARENT;
        param11.columnSpec = GridLayout.spec(1);
        param11.rowSpec = GridLayout.spec(1);

        //rad 2
        TextView textEpost = new TextView(this);
        textEpost.setText("Epost");

        GridLayout.LayoutParams param20 =new GridLayout.LayoutParams();
        param20.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        param20.columnSpec = GridLayout.spec(0);
        param20.rowSpec = GridLayout.spec(2);

        EditText editEpost = new EditText(this);

        GridLayout.LayoutParams param21 =new GridLayout.LayoutParams();
        editName.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        param21.width = LinearLayout.LayoutParams.MATCH_PARENT;
        param21.columnSpec = GridLayout.spec(1);
        param21.rowSpec = GridLayout.spec(2);

        //rad 3
        TextView textAge = new TextView(this);
        textAge.setText("Ålder");

        GridLayout.LayoutParams param30 =new GridLayout.LayoutParams();
        param30.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        param30.columnSpec = GridLayout.spec(0);
        param30.rowSpec = GridLayout.spec(3);

        SeekBar ageBar = new SeekBar(this);

        GridLayout.LayoutParams param31 =new GridLayout.LayoutParams();
        editName.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        param31.width = LinearLayout.LayoutParams.MATCH_PARENT;
        param31.columnSpec = GridLayout.spec(1);
        param31.rowSpec = GridLayout.spec(3);

        gridLayout.addView(textName, param00);
        gridLayout.addView(editName, param01);

        gridLayout.addView(textPassword, param10);
        gridLayout.addView(editPassword, param11);

        gridLayout.addView(textEpost, param20);
        gridLayout.addView(editEpost, param21);

        gridLayout.addView(textAge, param30);
        gridLayout.addView(ageBar, param31);

        myLayout.addView(toolbar, toolbarParams);
        myLayout.addView(gridLayout);
        setContentView(myLayout);
    }
    */

    /*
    // Lab1.1
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout myLayout = new LinearLayout(this);
        myLayout.setOrientation(LinearLayout.VERTICAL);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Laboration 1.1");
        LinearLayout.LayoutParams toolbarParams =
                new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

        toolbar.setBackgroundColor(Color.parseColor("#78909C"));
        setSupportActionBar(toolbar);

        Button myButton = new Button(this);
        myButton.setText("Knapp");

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        EditText myEditText = new EditText(this);
        myEditText.setSingleLine(true);

        RatingBar ratingBar = new RatingBar(this);

        LinearLayout.LayoutParams ratingParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        //centrering
        ratingParams.gravity = Gravity.CENTER_HORIZONTAL;

        ratingBar.setNumStars(5);

        EditText multiLineText = new EditText(this);

        LinearLayout.LayoutParams multiLineTextParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);



        myLayout.addView(toolbar, toolbarParams);
        myLayout.addView(myButton, buttonParams);
        myLayout.addView(myEditText);
        myLayout.addView(ratingBar, ratingParams);
        myLayout.addView(multiLineText, multiLineTextParams);
        setContentView(myLayout);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
