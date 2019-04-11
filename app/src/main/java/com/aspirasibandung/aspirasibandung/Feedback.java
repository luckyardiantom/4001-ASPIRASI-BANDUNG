package com.aspirasibandung.aspirasibandung;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.firebase.client.Firebase;//
public class Feedback extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText nameData, emailData, messageData;
    Button btnSend, btnDetail;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

///

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        nameData= findViewById(R.id.nameData);
        emailData= findViewById(R.id.emailData);
        messageData= findViewById(R.id.messageData);

        btnSend = findViewById(R.id.btnSend);
        btnDetail = findViewById(R.id.btnDetail);
        Firebase.setAndroidContext(this);

        String UniqueID =
                Settings.Secure.getString(getApplicationContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);

        firebase = new Firebase("https://feedback-d7f23.firebaseio.com/Users" +UniqueID);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDetail.setEnabled(true);
                final String name = nameData.getText().toString();
                final String email = emailData.getText().toString();
                final String message = messageData.getText().toString();

                Firebase child_name = firebase.child("Name");
                child_name.setValue(name);
                if (name.isEmpty()) {
                    nameData.setError("Nama Harus di Isi!");
                    btnSend.setEnabled(false);
                } else
                {
                    nameData.setError(null);
                    btnSend.setEnabled(true);
                }
                Firebase child_email = firebase.child("Email");
                child_email.setValue(email);
                if (email.isEmpty())
                {
                    emailData.setError("Email Harus di Isi!");
                    btnSend.setEnabled(false);
                } else
                {
                    emailData.setError(null);
                    btnSend.setEnabled(true);
                }
                Firebase child_message = firebase.child("Message");
                child_message.setValue(message);
                if (message.isEmpty())
                {
                    messageData.setError("Kritik dan Saran harus di isi");
                    btnSend.setEnabled(false);
                }
                else {
                    messageData.setError(null);
                    btnSend.setEnabled(true);
                }
                Toast.makeText(Feedback.this, "Data Anda Telah Tersimpan", Toast.LENGTH_SHORT).show();
                btnDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(Feedback.this)
                                .setTitle("Detail Masukan: ")
                                .setMessage("Name - " + name + "\n\nEmail - " + email + "\n\nMessage - " +message)
                                .show();
                    }
                });
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feedback, menu);
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

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), TampilanAwal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast.makeText(Feedback.this, "Thanks for visited", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
    private void Home(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void aboutus(){
        Intent intent = new Intent(getApplicationContext(), AboutUs.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void petunjukpenggunaan(){
        Intent intent = new Intent(getApplicationContext(), PetunjukPenggunaan.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void notlpdarurat(){
        Intent intent = new Intent(getApplicationContext(), Notlpdarurat.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void lapor(){
        Intent intent = new Intent(getApplicationContext(), Lapor.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void statuslaporan(){
        Intent intent = new Intent(getApplicationContext(), StatusLaporan.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            Home();
        } else if (id == R.id.nav_lapor) {
            lapor();

        } else if (id == R.id.nav_nomortelepondarurat) {
            notlpdarurat();

        } else if (id == R.id.nav_petunjukpenggunaan) {
            petunjukpenggunaan();

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_aboutus) {
            aboutus();

        } else if (id == R.id.keluar) {
            logout();
        }
        else if (id == R.id.nav_statuslaporan) {
            statuslaporan();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
