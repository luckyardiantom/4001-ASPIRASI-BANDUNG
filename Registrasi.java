package com.aspirasibandung.aspirasibandung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrasi extends AppCompatActivity {
    private EditText txtEmailAddress;
    private EditText txtPassword;
    private EditText txtnama;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        txtEmailAddress = (EditText) findViewById(R.id.txtEmailRegistration);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegistration);
        txtnama = (EditText)findViewById(R.id.txtnama);
        firebaseAuth = firebaseAuth.getInstance();


        final TextView Login = (TextView) findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Registrasi.this,Login.class);
                startActivity(login);
            }
        });

    }

    public void btnRegistrationUser_Click(View v) {
        if (TextUtils.isEmpty(txtEmailAddress.getText()) || TextUtils.isEmpty(txtPassword.getText()) || TextUtils.isEmpty(txtnama.getText())) {
            Toast.makeText(Registrasi.this, "Data yang diminta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            //instansiasi database firebase
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            //Referensi database yang dituju
            DatabaseReference referensi = database.getReference("Nama Pengguna").child(txtnama.getText().toString());

            //memberi nilai pada referensi yang dituju
            referensi.child("E-mail").setValue(txtEmailAddress.getText().toString());
            referensi.child("Password").setValue(txtPassword.getText().toString());
            Toast.makeText(Registrasi.this, "Data pengguna baru berhasil ditambahkan", Toast.LENGTH_LONG).show();


            final ProgressDialog progressDialog = ProgressDialog.show(Registrasi.this, "Please Wait...", "Processing ...", true);
            ( firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(Registrasi.this, "Registrasi berhasil", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Registrasi.this, Login.class);
                                startActivity(i);

                            }else{
                                Log.e("Registrasi gagal", task.getException().toString());
                                Toast.makeText(Registrasi.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });


        }
    }


}
