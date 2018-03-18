package com.example.mj.givelife;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;
import java.util.jar.Attributes;

public class Profile extends AppCompatActivity {

    private TextView name,dob,location,number,bloodGroup,username;
    private StorageReference ref=null;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        name =(TextView) findViewById(R.id.name);
        username =(TextView) findViewById(R.id.username);
        dob =(TextView) findViewById(R.id.dob);
        location =(TextView) findViewById(R.id.location);
        number=(TextView)findViewById(R.id.contactNumber);
        bloodGroup = (TextView) findViewById(R.id.bloodGroup);
        ref= FirebaseStorage.getInstance().getReference();
        mRef =FirebaseDatabase.getInstance().getReference("users");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
