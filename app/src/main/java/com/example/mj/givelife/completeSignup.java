package com.example.mj.givelife;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class completeSignup extends AppCompatActivity {
private ImageButton imgbtn;
private static final int GALLREQ=1;
private Uri uri=null;
private EditText name,dob,location,number,bloodGroup;
private StorageReference ref=null;
private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_signup);
        name =(EditText) findViewById(R.id.name);
        dob =(EditText)findViewById(R.id.dob);
        location =(EditText) findViewById(R.id.location);
        number=(EditText)findViewById(R.id.contactNumber);
        bloodGroup = (EditText) findViewById(R.id.bloodGroup);
        ref= FirebaseStorage.getInstance().getReference();
        mRef =FirebaseDatabase.getInstance().getReference("users");
    }

    public void saveDetailsButtonClicked(View view) {

        final String name_= name.getText().toString().trim();
        final String dob_= dob.getText().toString().trim();
        final String location__= location.getText().toString().trim();
        final String bloodGroup_= bloodGroup.getText().toString().trim();
        final String num_= number.getText().toString().trim();

        if(!TextUtils.isEmpty(name_) && !TextUtils.isEmpty(dob_) && !TextUtils.isEmpty(location__) && !TextUtils.isEmpty(bloodGroup_) && !TextUtils.isEmpty(num_) )
        {
                    StorageReference filepath= ref.child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       final Uri downloadurl =taskSnapshot.getDownloadUrl();
                       Toast.makeText(completeSignup.this,"Image Uploaded successfully",Toast.LENGTH_SHORT).show();
                            String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            DatabaseReference newuser = mRef.child(user_id);
                            newuser.child("Name").setValue(name_);
                            newuser.child("DOB").setValue(dob_);
                            newuser.child("Location").setValue(location__);
                            newuser.child("Contact").setValue(num_);
                            newuser.child("BloodGroup").setValue(bloodGroup_);
                            newuser.child("ProfileImage").setValue(downloadurl.toString());

                            Intent homeIntent = new Intent(completeSignup.this, Home.class);
                            startActivity(homeIntent);
                        }
                    });
                }



    }

    public void imageButtonClicked(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, GALLREQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLREQ && resultCode==RESULT_OK){
            uri=data.getData();
            imgbtn=(ImageButton) findViewById(R.id.imageButton);
            imgbtn.setImageURI(uri);
        }
    }
}
