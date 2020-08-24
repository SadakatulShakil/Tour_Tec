package com.example.tourtec.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tourtec.MainActivity;
import com.example.tourtec.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    private ImageView logoImg, textImg;
    private Animation topAnim, bottomAnim;
    private static int SPLASH_SCREEN = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        //Assign animation
        AssignAnim();
        //splash coding
        RunSplash();

    }

    private void RunSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }

    private void AssignAnim() {
        logoImg.setAnimation(topAnim);
        textImg.setAnimation(bottomAnim);
    }

    private void initView() {
        logoImg = findViewById(R.id.icon);
        textImg = findViewById(R.id.text);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
    }
}