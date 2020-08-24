package com.example.tourtec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourtec.Activity.SignUpActivity;
import com.example.tourtec.Activity.UserUiContainerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView tbtSignUp, tbtForgetPassword;
    private Button btnSignIn;
    private TextInputEditText tieEmail, tiePassword;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            Intent intent = new Intent(getApplicationContext(), UserUiContainerActivity.class);
            startActivity(intent);
        }

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
                StartingSignIn();
            }
        });
    }

    private void StartingSignIn() {

        String email = tieEmail.getText().toString().trim();
        String password = tiePassword.getText().toString().trim();

        if (email.isEmpty()) {
            tieEmail.setError("Email is required!");
            tieEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            tiePassword.setError("Password is required!");
            tiePassword.requestFocus();
            return;
        }

        signIn(email, password);
    }

    private void signIn(final String email, final String password) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(MainActivity.this, UserUiContainerActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

    }


    private void initView() {
        firebaseAuth = FirebaseAuth.getInstance();
        tbtSignUp = findViewById(R.id.sinUpTxtbt);
        btnSignIn = findViewById(R.id.signInBt);
        progressBar = findViewById(R.id.progressBar);

        tbtForgetPassword = findViewById(R.id.forgetPassword);
        tieEmail = findViewById(R.id.emailEditText);
        tiePassword = findViewById(R.id.passwordEditText);
    }
}