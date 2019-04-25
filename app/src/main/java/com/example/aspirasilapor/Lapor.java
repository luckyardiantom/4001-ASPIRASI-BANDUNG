package com.example.aspirasilapor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.Map;

public class Lapor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int REQUEST = 91, REQUEST_GET_SINGLE_FILE = 202, REQUEST_CAPTURE_IMAGE = 234;
    Bitmap bitmap;
    ImageView foto;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    Uri uri;
    String imagePath;
    EditText kategori, tanggal, deskripsi;

    FirebaseStorage storage;
    StorageReference storageReference;

    Map<String, Object> x;

    ProgressBar progressBar;

    boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        foto = findViewById(R.id.Img);
        db = FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();

        //init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        kategori = findViewById(R.id.edtKategori);
        tanggal = findViewById(R.id.edtTanggal);
        deskripsi = findViewById(R.id.edtDesk);

        progressBar = findViewById(R.id.progressBar);

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings();
    }

    /*private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Lapor.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Lapor.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }

        if (TextUtils.isEmpty(edtKategori.getText()) || TextUtils.isEmpty(edtTanggal.getText()) || TextUtils.isEmpty(edtDesk.getText())) {
            Toast.makeText(Lapor.this, "Data yang diminta tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            //instansiasi database firebase
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            //Referensi database yang dituju
            DatabaseReference referensi = database.getReference("Lapor").child(edtTanggal.getText().toString());

            //memberi nilai pada referensi yang dituju
            referensi.child("Deskripsi").setValue(edtDesk.getText().toString());
            referensi.child("Kategori").setValue(edtKategori.getText().toString());
        }*/
    }////////////////////////////////

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Img.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lapor, menu);
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
        Toast.makeText(Lapor.this, "Thanks for visited", Toast.LENGTH_SHORT).show();
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
    private void feedback(){
        Intent intent = new Intent(getApplicationContext(), Feedback.class);
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

        } else if (id == R.id.nav_nomortelepondarurat) {
            notlpdarurat();

        } else if (id == R.id.nav_petunjukpenggunaan) {
            petunjukpenggunaan();

        } else if (id == R.id.nav_feedback) {
            feedback();

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
