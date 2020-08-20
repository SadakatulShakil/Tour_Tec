package com.example.tourtec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tourtec.Activity.SignUpActivity;
import com.example.tourtec.Activity.UserUiContainerActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tbtSignUp;
    private Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        clickEvents();
    }

    private void clickEvents() {
        tbtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserUiContainerActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initView() {
        tbtSignUp = findViewById(R.id.sinUpTxtbt);
        btnSignIn = findViewById(R.id.signInBt);
    }
}