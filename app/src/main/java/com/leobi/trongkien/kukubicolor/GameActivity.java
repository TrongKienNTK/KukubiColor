package com.leobi.trongkien.kukubicolor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
//import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private static final String NAME = "Kukubi_Color" ;
    TextView txtTime, txtPoint, txtNote;
    GridView gvColor;
    MyTools tools = new MyTools();
    private SoundPlayer sound;
    ArrayList arr;
     int point, numRow, checkTimer = 0, times = 3000, level = 0;
    //int timeUp;

    public int getScore(){

        return point;
    }

    CountDownTimer timer = new CountDownTimer(times, 10) {
        //@Override
        public void onTick(long millisUntilFinished) {
            txtTime.setText("Time left: " + millisUntilFinished / 10 + "");
            //timeUp = (int) (millisecondsUntilFinished);
        }

        public void onFinish() {
            checkTimer = 1;

            txtNote.setTextColor(Color.parseColor("#e74c3c"));
            txtNote.setText("Time up!");

            txtTime.setText("Time up!");
            txtTime.setTextColor(Color.parseColor("#e74c3c"));

            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setMessage("Game over!!! \nTry your best!")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
//                                    intent.putExtra("Current_Score", Integer.toString(point));
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                                    android.content.SharedPreferences prefs = getSharedPreferences(NAME, android.content.Context.MODE_PRIVATE);
                                    android.content.SharedPreferences.Editor editor = prefs.edit();
                                    editor.putInt("SCORE", point);
                                    editor.commit();
                                    finish();
                                }
                            }
                    )
            ;
            //Creating dialog box
            AlertDialog alert = builder.create();
            alert.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getReady();
        anhXa();
        setGridView();
        setData();
        setAction();

    }

    @Override
    public void onBackPressed() {
        //Do not thing
    }


    private void getReady(){
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setMessage("Get Ready?\nLet's choose the different color!")
                .setCancelable(false)
                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(GameActivity.this, HomeActivity.class);
                                finish();
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                            }
                        }
                );

        builder.setPositiveButton("Start!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                setGridView();
                timer.start();
            }
        });
        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void anhXa(){
        point = 0;
        gvColor = findViewById(R.id.gvColor);
        txtPoint = findViewById(R.id.txtPoint);
        txtTime = findViewById(R.id.txtTime);
        txtNote = findViewById(R.id.txtNote);
    }
    public void setGridView(){
        if(level <= point) level = point;
        if(level < 100){
            gvColor.setNumColumns(2);
            numRow = 4;
        }else{
            times = times + level/10;
            gvColor.setNumColumns(level/100+2);
            numRow = (level/100+2) * (level/100+2);
        }

    }
    public void setData(){
        arr = new ArrayList(tools.generateColor(numRow));
        Adapter adapter = new Adapter(GameActivity.this, R.layout.activity_game, arr);
        gvColor .setAdapter(adapter);
        txtPoint.setText("Score: " + Integer.toString(point));
    }
    public void setAction(){
        gvColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == MyTools.result && checkTimer == 0){
                        setGridView();
                        timer.start();
//                        sound.playHitSound();
                        point = point + 20;

                        Animation myAnim = AnimationUtils.loadAnimation(GameActivity.this, R.anim.my_trans);
                        txtNote.startAnimation(myAnim);
                        txtNote.setTextColor(Color.parseColor("#34495e"));
                        txtNote.setText(tools.generateMess());
                        setData();
                    }else if(position != MyTools.result && checkTimer == 0){
                        if(point < 100){
                            point = point - 10;
//                            sound.playOverSound();
                        }else{
                            point = point - (point/100+2)*5;
//                            sound.playOverSound();
                        }
                        Animation myAnim = AnimationUtils.loadAnimation(GameActivity.this, R.anim.my_trans);
                        txtNote.startAnimation(myAnim);
                        txtNote.setTextColor(Color.parseColor("#e74c3c"));
                        txtNote.setText(tools.generateErr());
                        txtPoint.setText("Score: " + Integer.toString(point));
                }
            }
        });
    }

}

