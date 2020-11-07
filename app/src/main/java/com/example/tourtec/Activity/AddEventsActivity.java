package com.example.tourtec.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourtec.Model.Events;
import com.example.tourtec.Model.User;
import com.example.tourtec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEventsActivity extends AppCompatActivity {
    private String addingTime;
    private TextView saveTv, cancelTv;
    private TextInputEditText tieEventName, tieEventDescription, tieEventBudget;
    private EditText eventStartDate, eventEndDate;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference UserRef;
    private DatabaseReference eventsRef;
    private User user;
    private String userName;
    private FirebaseUser fUser;
    protected static TextView viewDate1,viewDate2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);
        final FragmentManager fm = getSupportFragmentManager();
        initView();
        eventStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment1();

                newFragment.show(fm, "datePicker");
            }
        });
        eventEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment2();

                newFragment.show(fm, "datePicker");
            }
        });

        clickEvents();
        getCurrentUserData();

    }

    private void getCurrentUserData() {

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            FirebaseDatabase fdb = FirebaseDatabase.getInstance();
            UserRef = fdb.getReference("Traveller");
            String userId = firebaseUser.getUid();
            UserRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                user =snapshot.getValue(User.class);
                userName = user.getUserName();
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clickEvents() {
        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eName = tieEventName.getText().toString().trim();
                String eDescription = tieEventDescription.getText().toString().trim();
                String eBudget = tieEventBudget.getText().toString().trim();
                String eStartDate = viewDate1.getText().toString().trim();
                String eEndDate = viewDate2.getText().toString().trim();

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat myDateFormat = new SimpleDateFormat("hh:mm a");
                addingTime = myDateFormat.format(calendar.getTime());


                if (eName.isEmpty()) {
                    tieEventName.setError("Name is required!");
                    tieEventName.requestFocus();
                    return;
                }
                if (eDescription.isEmpty()) {
                    tieEventDescription.setError("Description is required!");
                    tieEventDescription.requestFocus();
                    return;
                }

                if (eBudget.isEmpty()) {
                    tieEventBudget.setError("Budget is required!");
                    tieEventBudget.requestFocus();
                    return;
                }

                if (eStartDate.isEmpty()) {
                    viewDate1.setError("Date is required!");
                    viewDate1.requestFocus();
                    return;
                }

                if (eEndDate.isEmpty()) {
                    viewDate2.setError("Date is required!");
                    viewDate2.requestFocus();
                    return;
                }

                storeEventsInfo(eName, eDescription, eBudget, eStartDate, eEndDate, addingTime);

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

    private void storeEventsInfo(final String eName, final String eDescription, final String eBudget,
                                 final String eStartDate, final String eEndDate, final String addingTime) {

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        eventsRef = FirebaseDatabase.getInstance().getReference("EventsInfo");
        String userId = fUser.getUid();
        String pushId = eventsRef.push().getKey();

        Events events = new Events(pushId, userName, userId, eName, eDescription, eBudget,
                eStartDate, eEndDate, addingTime);

        eventsRef.child(pushId).setValue(events).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddEventsActivity.this, "Event Created Successfully!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddEventsActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        saveTv = findViewById(R.id.saveTV);
        cancelTv = findViewById(R.id.cancelTV);

        tieEventName = findViewById(R.id.nameEditText);
        tieEventDescription = findViewById(R.id.descriptionEditText);
        tieEventBudget = findViewById(R.id.budgetEditText);

        eventStartDate = findViewById(R.id.startEventDateET);
        eventEndDate = findViewById(R.id.endEventDateET);
    }

    //DatePickerMethods
    @SuppressLint("ValidFragment")
    public static class DatePickerFragment1 extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
            viewDate1 = getActivity().findViewById(R.id.startEventDateET);
           /* int actualMonth = month+1; // Because month index start from zero
            // Display the unformatted date to TextView
            tvDate.setText("Year : " + year + ", Month : " + actualMonth + ", Day : " + day + "\n\n");*/

            // Create a Date variable/object with user chosen date
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();

            // Format the date using style medium and UK locale
            DateFormat df_medium_uk = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
            String df_medium_uk_str = df_medium_uk.format(chosenDate);
            // Display the formatted date
            viewDate1.setText(df_medium_uk_str);
        }
    }
    //End of DatePickerMethods

    //DatePickerMethods
    @SuppressLint("ValidFragment")
    public static class DatePickerFragment2 extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
            viewDate2 = getActivity().findViewById(R.id.endEventDateET);
           /* int actualMonth = month+1; // Because month index start from zero
            // Display the unformatted date to TextView
            tvDate.setText("Year : " + year + ", Month : " + actualMonth + ", Day : " + day + "\n\n");*/

            // Create a Date variable/object with user chosen date
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();

            // Format the date using style medium and UK locale
            DateFormat df_medium_uk = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
            String df_medium_uk_str = df_medium_uk.format(chosenDate);
            // Display the formatted date
            viewDate2.setText(df_medium_uk_str);
        }
    }
    //End of DatePickerMethods
}