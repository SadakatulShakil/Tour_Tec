package com.example.tourtec.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourtec.Activity.EventDetailsActivity;
import com.example.tourtec.Fragment.EventDetailsFragment;
import com.example.tourtec.Model.Events;
import com.example.tourtec.R;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.viewHolder> {
    private Context context;
    private ArrayList<Events> eventsArrayList;

    public EventsAdapter(Context context, ArrayList<Events> eventsArrayList) {
        this.context = context;
        this.eventsArrayList = eventsArrayList;
    }

    @NonNull
    @Override
    public EventsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_list_view, parent, false);
        return new EventsAdapter.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.viewHolder holder, int position) {
        final Events eventsInfo = eventsArrayList.get(position);

        holder.eventNameTv.setText("Events: "+eventsInfo.getEventName());
        holder.eventStartDateTv.setText(eventsInfo.getEventStartTime());
        holder.eventEndDateTv.setText(eventsInfo.getEventEndTime());
        holder.eventsCostTv.setText("Amount: "+eventsInfo.getEventBudget()+"৳ /per person");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context, EventDetailsActivity.class);
               intent.putExtra("eventDetails", eventsInfo);
               context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventsArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView eventNameTv, eventStartDateTv, eventEndDateTv, eventsCostTv;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            eventNameTv = itemView.findViewById(R.id.eventsName);
            eventStartDateTv = itemView.findViewById(R.id.startDate);
            eventEndDateTv = itemView.findViewById(R.id.endDate);
            eventsCostTv = itemView.findViewById(R.id.eventCost);
        }
    }
}
