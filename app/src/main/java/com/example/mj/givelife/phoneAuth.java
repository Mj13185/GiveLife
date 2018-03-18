package com.example.mj.givelife;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class phoneAuth extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
    }

    public void signUpButtonClicked(View view) {
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 8) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 8 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(phoneAuth.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(phoneAuth.this, "Account Successfully created" , Toast.LENGTH_SHORT).show();
                        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference new_user = mDatabase.child(user_id);
                        new_user.child("Email").setValue(email);
                        Intent homeIntent = new Intent(phoneAuth.this, completeSignup.class);
                        startActivity(homeIntent);

                    }
                });

    }




    @Override
    protected void onResume() {
        super.onResume();
    }


}

