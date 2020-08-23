package com.example.tourtec.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourtec.R;

public class AddEventsActivity extends AppCompatActivity {

    private TextView saveTv, cancelTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        initView();
        clickEvents();

    }

    private void clickEvents() {
        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddEventsActivity.this, "Event created !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddEventsActivity.this, "Event created failed!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void initView() {
        saveTv = findViewById(R.id.saveTV);
        cancelTv = findViewById(R.id.cancelTV);
    }
}