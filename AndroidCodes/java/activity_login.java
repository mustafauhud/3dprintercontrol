package com.example.a3dprinterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginActButton = findViewById(R.id.button2);
        loginActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_login.this, activity_register.class);
                startActivity(intent);
            }
        });

        FirebaseAuthenticationService mAuthService = new FirebaseAuthenticationService();

        EditText emailEditText = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordEditText = findViewById(R.id.editTextTextPassword);

        Button loginButton = findViewById(R.id.buttonlogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                mAuthService.loginUser(email, password, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success, do something
                            Toast.makeText(activity_login.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();

                        } else {
                            // Login failed, do something
                            Toast.makeText(activity_login.this, "Error!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}