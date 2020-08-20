package com.example.tourtec.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tourtec.MainActivity;
import com.example.tourtec.R;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    private TextView tbtSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        clickEvents();
    }

    private void clickEvents() {
        tbtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tbtSignIn = findViewById(R.id.signInTxtBt);
    }
}