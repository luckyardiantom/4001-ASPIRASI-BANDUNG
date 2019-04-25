package com.example.aspirasilapor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText txtEmailLogin;
    private EditText txtPwd;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtPwd = (EditText) findViewById(R.id.txtPasswordLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        final TextView Regis = (TextView) findViewById(R.id.Regis);
        Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Regis = new Intent(Login.this,Registrasi.class);
                startActivity(Regis);
            }
        });

    }

    public void btnUserLogin_Click(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(Login.this, "Please wait...", "Proccessing...", true);
        (firebaseAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(), txtPwd.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login succesfull", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Login.this, MainActivity.class);
                            i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(i);
                        }

                        else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
