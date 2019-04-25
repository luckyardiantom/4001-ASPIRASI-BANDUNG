package com.aspirasibandung.aspirasibandung;

public class CardLapor {
    String img, kategori,deskripsi;

    public CardLapor(String img, String kategori, String deskripsi) {
        this.img = img;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
    }

    public String getimg() {
        return img;
    }

    public void setimg(String img) {
        this.img = img;
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

