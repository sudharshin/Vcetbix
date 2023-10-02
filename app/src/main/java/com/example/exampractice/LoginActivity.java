package com.example.exampractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText email, pass;
    private Button loginB;
    private TextView forgotpassB, signupB;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        loginB = findViewById(R.id.loginB); // Moved the initialization here
        forgotpassB = findViewById(R.id.forgot_pass);
        signupB = findViewById(R.id.signupB);
        mAuth = FirebaseAuth.getInstance();

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    login();
                }
            }
        });

        signupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateData() {
        if (email.getText().toString().isEmpty()) {
            email.setError("Enter Email ID");
            return false;
        }
        if (pass.getText().toString().isEmpty()) {
            pass.setError("Enter password");
            return false;
        }
        return true;
    }

    private void login() {
        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            LoginActivity.this.finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
