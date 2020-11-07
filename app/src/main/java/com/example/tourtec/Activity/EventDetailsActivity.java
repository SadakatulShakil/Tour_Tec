package com.example.tourtec.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourtec.Model.Events;
import com.example.tourtec.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class EventDetailsActivity extends AppCompatActivity {
    private TextView eventNameTv, eventDescriptionTv, eventStartTv, eventFinishTv, eventCostTv, organizedByTv;
    private Events eventsInfo;
    private LinearLayout legalAdmin;
    private Button requestCheckBt, memberAddBt, requestSendBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        inItView();
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Toast.makeText(this, "userName: "+ currentUserId, Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        eventsInfo = (Events) intent.getSerializableExtra("eventDetails");

        if(eventsInfo.getCreatorId().equals(currentUserId)){
            legalAdmin.setVisibility(View.VISIBLE);
            requestSendBt.setVisibility(View.GONE);
        }else {
            requestSendBt.setVisibility(View.VISIBLE);
            legalAdmin.setVisibility(View.GONE);
        }
        eventNameTv.setText(eventsInfo.getEventName());
        eventDescriptionTv.setText(eventsInfo.getEventDescription());
        eventStartTv.setText("Started at: "+eventsInfo.getEventStartTime());
        eventFinishTv.setText("Finished at: "+eventsInfo.getEventEndTime());
        eventCostTv.setText("Estimated Cost: "+eventsInfo.getEventBudget()+"৳ /per person");
        organizedByTv.setText("Organized by: "+eventsInfo.getCreatedBy());


    }

    private void inItView() {
        eventNameTv = findViewById(R.id.eventName);
        eventDescriptionTv = findViewById(R.id.eventDescription);
        eventStartTv = findViewById(R.id.journeyDate);
        eventFinishTv = findViewById(R.id.journeyFinishDate);
        eventCostTv = findViewById(R.id.journeyCost);
        organizedByTv = findViewById(R.id.creatorName);
        legalAdmin = findViewById(R.id.adminLayout);
        requestCheckBt = findViewById(R.id.checkRequest);
        memberAddBt = findViewById(R.id.addPeople);
        requestSendBt = findViewById(R.id.sendRequest);
    }
}