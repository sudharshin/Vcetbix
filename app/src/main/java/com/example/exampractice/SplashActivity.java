package com.example.exampractice;

import static com.example.exampractice.DbQuery.g_firestore;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends AppCompatActivity {

    private TextView appName;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appName = findViewById(R.id.app_name);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.dancingscript);
        appName.setTypeface(typeface);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.myanim);
        appName.startAnimation(anim);

        mAuth = FirebaseAuth.getInstance();
        DbQuery.g_firestore= FirebaseFirestore.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser() != null) {

                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000);
    }
}
