package com.example.mj.givelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Splash extends AppCompatActivity  {
private TextView tv;
private ImageView iv;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth= FirebaseAuth.getInstance();
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);
        final Intent i = new Intent(this,signin.class);
        Thread timer = new Thread(){
            public void run(){

                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    finally {
                    finish();
                    startActivity(i);

                }
            }

        };
        timer.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
            if(auth.getCurrentUser()!=null)
            {
                finish();
                startActivity(new Intent(this,Home.class));
            }
    }
}
