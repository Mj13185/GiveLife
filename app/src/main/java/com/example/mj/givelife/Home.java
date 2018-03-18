package com.example.mj.givelife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,new homeFragment());
        ft.commit();

        navigationView.setCheckedItem(R.id.Home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,new homeFragment());
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.Home) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,new homeFragment());
            ft.commit();
        } else if (id == R.id.profile) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,new fragmentProfile());
            ft.commit();
        } else if (id == R.id.nav_gallery) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,new gallery());
            ft.commit();
        } else if (id == R.id.news) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,new news());
            ft.commit();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,new share());
            ft.commit();
        } else if (id == R.id.nav_send) {

        }else if (id == R.id.signOut) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void referCardClicked(View view) {
        Intent s = new Intent(Home.this, Refer.class);
        startActivity(s);
    }
    public void searchCardClicked(View view) {
        Intent s = new Intent(Home.this, Search.class);
        startActivity(s);
    }
    public void donateCardClicked(View view) {
        Intent s = new Intent(Home.this, Donate_Blood.class);
        startActivity(s);
    }
    public void requestCardClicked(View view) {
        Intent s = new Intent(Home.this, Request_blood.class);
        startActivity(s);
    }
    public void profileCardClicked(View view) {
        Intent s = new Intent(Home.this, Profile.class);
        startActivity(s);
    }
    public void signoutButtonClicked(View view) {

        mAuth.signOut();
        sendToAuth();
    }
    private void sendToAuth() {
        Intent AuthIntent = new Intent(Home.this,signin.class);
        startActivity(AuthIntent);
    }
}
