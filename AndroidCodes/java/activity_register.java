package com.example.a3dprinterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button loginActButton = findViewById(R.id.button5);
        loginActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_register.this, activity_login.class);
                startActivity(intent);
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Button registerButton = findViewById(R.id.buttonregister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEmailField = findViewById(R.id.editTextTextEmailAddress2);
                EditText mPasswordField = findViewById(R.id.editTextTextPassword2);

                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(activity_register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(activity_register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(activity_register.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(activity_register.this, activity_login.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(activity_register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
