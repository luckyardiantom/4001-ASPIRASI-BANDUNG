package com.aspirasibandung.aspirasibandung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TampilanAwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_awal);
    }

    public void btnRegistration_Click(View v) {
        Intent i = new Intent(TampilanAwal.this, Registrasi.class);
        startActivity(i);
    }

    public void btnLogin_Click(View v) {
        Intent i = new Intent(TampilanAwal.this, Login.class);
        startActivity(i);
    }

}
