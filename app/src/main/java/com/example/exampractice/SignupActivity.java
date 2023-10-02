package com.example.exampractice;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.exampractice.DbQuery;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.exampractice.DbQuery;

public class SignupActivity extends AppCompatActivity {
    private EditText name, email, pass, confirmpass;
    private Button signUpB;
    private ImageView backB;
    private FirebaseAuth mAuth;
    private String emailStr, PassStr, ConfirmPassStr, nameStr;
    private Dialog progressDialog;
    private TextView dialogText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.username);
        email = findViewById(R.id.emailID);
        pass = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirm_pass);
        signUpB = findViewById(R.id.signupbtn);
        backB = findViewById(R.id.backB);


    progressDialog=new Dialog(SignupActivity.this);
    progressDialog.setContentView(R.layout.dailog_layout);
    progressDialog.setCancelable(false);
    progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogText=progressDialog.findViewById(R.id.dialog_text);
        dialogText.setText("Registering user...");

        mAuth = FirebaseAuth.getInstance();


        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    signupNewUser();
                }
            }
        });
    }

    private boolean validate() {
        nameStr = name.getText().toString().trim();
        PassStr = pass.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        ConfirmPassStr = confirmpass.getText().toString().trim();
        if (nameStr.isEmpty()) {
            name.setError("Enter Your Name");
            return false;
        }
        if (emailStr.isEmpty()) {
            email.setError("Enter Email ID");
            return false;
        }
        if (PassStr.isEmpty()) {
            pass.setError("Enter Password");
            return false;
        }
        if (ConfirmPassStr.isEmpty()) {
            confirmpass.setError("Enter confirm password");
            return false;
        }
        if (!PassStr.equals(ConfirmPassStr)) {
            Toast.makeText(SignupActivity.this, "Password and confirm password should be the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void signupNewUser() {

        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(emailStr, PassStr)
                .addOnCompleteListener(this,(task) -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "SignUp Successful.", Toast.LENGTH_SHORT).show();

                            DbQuery.createUserData(emailStr,nameStr,new MyCompleteListener(){

                                @Override
                                public void onSuccess(){
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    SignupActivity.this.finish();

                                }
                                @Override
                                public void onFailure(){
                                    Toast.makeText(SignupActivity.this,"Something went wrong..please try again later",Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });


                        } else {
                            // If sign-in fails, display a message to the user
                            progressDialog.dismiss();
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                });
    }
}
