package com.example.aspirasilapor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Card {
    String imagePath,kategori,tanggal,deskripsi;

    public Card(String imagePath, String nama, String harga, String deskripsi) {
        this.imagePath = imagePath;
        this.kategori = nama;
        this.tanggal = harga;
        this.deskripsi = deskripsi;
    }
///
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategoria(String kategori) {
        this.kategori = kategori;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}