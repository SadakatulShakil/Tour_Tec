package com.example.tourtec.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.tourtec.Fargment.CreateEventsFragment;
import com.example.tourtec.Fargment.HomeFragment;
import com.example.tourtec.R;

public class EventsActivity extends AppCompatActivity {

    private Toolbar dToolbar;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        initView();
        dToolbar.setNavigationIcon(R.drawable.ic_arrow);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.eventsFragmentContainer, new CreateEventsFragment())
                .commit();

        dToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this, UserUiContainerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dToolbar = findViewById(R.id.toolbar);
        }
        frameLayout = findViewById(R.id.eventsFragmentContainer);
    }
}