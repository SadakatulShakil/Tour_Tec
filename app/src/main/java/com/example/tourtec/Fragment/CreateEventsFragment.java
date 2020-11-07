package com.example.tourtec.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourtec.Activity.AddEventsActivity;
import com.example.tourtec.Adapter.EventsAdapter;
import com.example.tourtec.Model.Events;
import com.example.tourtec.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreateEventsFragment extends Fragment {

    private FloatingActionButton addEventsFb;
    private Context context;
    private RecyclerView eventsRecyclerView;
    private ArrayList<Events> mEventsArrayList = new ArrayList<>();
    private EventsAdapter mEventsAdapter;
    private DatabaseReference eventsInfoReference;
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

        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mEventsAdapter = new EventsAdapter(context, mEventsArrayList);
        eventsRecyclerView.setAdapter(mEventsAdapter);

        retrieveEventsList();
    }

    private void retrieveEventsList() {

        eventsInfoReference = FirebaseDatabase.getInstance().getReference("EventsInfo");

        eventsInfoReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mEventsArrayList.clear();
            for(DataSnapshot eventSnapshot : snapshot.getChildren()){
                Events eventsInfo =eventSnapshot.getValue(Events.class);

                mEventsArrayList.add(eventsInfo);
            }
            mEventsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        eventsRecyclerView = view.findViewById(R.id.recyclerViewForEventList);
    }
}