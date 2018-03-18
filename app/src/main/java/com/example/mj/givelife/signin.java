package com.example.mj.givelife;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signin extends AppCompatActivity {
private EditText userEmail,userPass;
private FirebaseAuth mAuth;
private DatabaseReference mDatabase;
private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        userEmail= findViewById(R.id.email_signin);
        userPass= findViewById(R.id.pass_signin);
        mAuth=FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        if (user != null) {
            finish();
            Intent homeIntent = new Intent(signin.this, Home.class);
            startActivity(homeIntent);
        } else {
            finish();
            Intent signIntent =new Intent(signin.this,signin.class);
        }
    }


    public void signinButtonClicked(View view) {

        String email = userEmail.getText().toString().trim();
        String pass = userPass.getText().toString().trim();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        finish();
                        Intent homeIntent = new Intent(signin.this, Home.class);
                        startActivity(homeIntent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Email or password incorrect", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        else {
            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass))
            {

                    Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();

            }
          else
            {
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
  /*  public void checkUserExists() {
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user_id)) {
                    Intent homeIntent = new Intent(signin.this, Home.class);
                    startActivity(homeIntent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }*/
        public void createAccountClicked(View view){
            finish();
        Intent s = new Intent(signin.this,phoneAuth.class);
            startActivity(s);
    }
}


