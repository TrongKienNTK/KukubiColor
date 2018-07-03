package com.leobi.trongkien.kukubicolor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private static final String NAME = "Kukubi_Color" ;
    //public static final String hiScore = "score";


    TextView txtScore, txtScoreMess;
  //  Intent intent = getIntent();
  //  int score = Integer.parseInt(intent.getStringExtra("Current_Score"));
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        txtScore = findViewById(R.id.txtScore);
        txtScoreMess = findViewById(R.id.txtScoreMess);

        Intent intent = getIntent();
        String s = intent.getStringExtra("Current_Score");
//
//        //int score = Integer.parseInt(s);
//        //txtScore.setText("Your Score: " + s);




        SharedPreferences prefs = this.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        int score = prefs.getInt("SCORE", 0);
        int highScore = prefs.getInt("HIGH_SCORE", 0);

        android.content.SharedPreferences puts = getSharedPreferences(NAME, android.content.Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = puts.edit();

        txtScore.setText("Your Score: " + Integer.toString(score));

        if(score > highScore){
            highScore = score;
            Animation myAnim = AnimationUtils.loadAnimation(ScoreActivity.this, R.anim.my_trans);
            txtScoreMess.startAnimation(myAnim);
            txtScoreMess.setText("YOU HAVE A NEW BEST!");
            editor.putInt("HIGH_SCORE", highScore);
            editor.commit();
        }else{
            Animation myAnim = AnimationUtils.loadAnimation(ScoreActivity.this, R.anim.my_trans);
            txtScoreMess.startAnimation(myAnim);
            txtScoreMess.setText("THE BEST IS " + Integer.toString(highScore));
        }

        Button btTryAgain = findViewById(R.id.btTryAgain);
        btTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, GameActivity.class);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                startActivity(intent);



            }
        });

        Button btGoBack = findViewById(R.id.btGoBack);
        btGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });



    }


}
