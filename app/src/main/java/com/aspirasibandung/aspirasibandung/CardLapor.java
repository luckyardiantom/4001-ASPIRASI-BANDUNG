package com.aspirasibandung.aspirasibandung;

public class CardLapor {
    String imagePath, kategori, harga, deskripsi;

    public CardLapor(String id, String imagePath, String kategori) {
        this.imagePath = imagePath;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getkategori() {
        return kategori;
    }

    public void setkategori(String kategori) {
        this.kategori = kategori;
    }

    public String getdeskripsi() {
        return deskripsi;
    }

    public void setdeskripsi(String Deskripsi) {
        this.deskripsi = Deskripsi;
    }
}

