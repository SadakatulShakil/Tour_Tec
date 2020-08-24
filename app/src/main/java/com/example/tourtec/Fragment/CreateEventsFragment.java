package com.example.tourtec.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourtec.Activity.AddEventsActivity;
import com.example.tourtec.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateEventsFragment extends Fragment {

    private FloatingActionButton addEventsFb;
    private Context context;
    public CreateEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        clickEvents();
    }

    private void clickEvents() {

        addEventsFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddEventsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    private void initView(View view) {

        addEventsFb = view.findViewById(R.id.addEventsFAB);
    }
}