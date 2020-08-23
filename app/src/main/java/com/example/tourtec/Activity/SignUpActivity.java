package com.example.tourtec.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourtec.MainActivity;
import com.example.tourtec.R;
import com.example.tourtec.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SignUpActivity extends AppCompatActivity {

    private TextView tbtSignIn;
    private Button btSignUp;
    private TextInputEditText tieName, tieEmail, tiePhone, tieLocation, tiePassword, tieConfirmPassword;
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;
    private StringBuilder buf;
    public static final String TAG = "SignUp";

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
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignUp();
            }
        });

    }

    private void startSignUp() {
        String name = tieName.getText().toString().trim();
        String email = tieEmail.getText().toString().trim();
        Log.d(TAG, "startSignUp: " + email);
        String phone = tiePhone.getText().toString().trim();
        String location = tieLocation.getText().toString().trim();
        String password = tiePassword.getText().toString().trim();
        Log.d(TAG, "startSignUp: " + email);
        String cPassword = tieConfirmPassword.getText().toString().trim();

        if (name.isEmpty()) {
            tieName.setError("Name is required!");
            tieName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            tieEmail.setError("Email is required!");
            tieEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            tiePhone.setError("Email is required!");
            tiePhone.requestFocus();
            return;
        }

        if (location.isEmpty()) {
            tieLocation.setError("Email is required!");
            tieLocation.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            tiePassword.setError("Email is required!");
            tiePassword.requestFocus();
            return;
        }

        if (cPassword.isEmpty()) {
            tieConfirmPassword.setError("Email is required!");
            tieConfirmPassword.requestFocus();
            return;
        }

       /* if (password.equals(cPassword)) {
        } else {
            Toast.makeText(this, "Password are Not Matched!", Toast.LENGTH_SHORT).show();
        }*/
        SignUp(name,email,phone,location,password);


    }

    private void SignUp(final String name, final String email, final String phone, final String location, final String password) {
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = firebaseAuth.getCurrentUser().getUid();

                    User user = new User(userId, name, email, phone, location);
                    DatabaseReference userRef = databaseReference.child("Traveller").child(userId);

                    userRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Successfully Sign up!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(SignUpActivity.this, UserUiContainerActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                            }
                        }
                    });
                }
            }
        });

    }

    private void initView() {
        tbtSignIn = findViewById(R.id.signInTxtBt);

        tieName = findViewById(R.id.nameEditText);
        tieEmail = findViewById(R.id.emailEditText);
        tiePhone = findViewById(R.id.phoneEditText);
        tieLocation = findViewById(R.id.locationEditText);
        tiePassword = findViewById(R.id.passwordEditText);
        tieConfirmPassword = findViewById(R.id.confirmEditText);
        btSignUp = findViewById(R.id.signUpBt);

        progressBar = findViewById(R.id.progressBar);
    }
}