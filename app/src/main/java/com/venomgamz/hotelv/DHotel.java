package com.venomgamz.hotelv;

/**
 * Created by VENOM on 1/3/2018.
 */

public class DHotel {
    String id,nama,harga,nokamar;

    public DHotel() {
    }

    public DHotel(String id, String nama, String harga, String nokamar) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.nokamar = nokamar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNokamar() {
        return nokamar;
    }

    public void setNokamar(String nokamar) {
        this.nokamar = nokamar;
    }
}
