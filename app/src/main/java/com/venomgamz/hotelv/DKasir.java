package com.venomgamz.hotelv;

/**
 * Created by VENOM on 1/3/2018.
 */

public class DKasir {
    String id,nokamar,harga,lama,total;
    String nama,noktp;

    public DKasir() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNokamar() {
        return nokamar;
    }

    public void setNokamar(String nokamar) {
        this.nokamar = nokamar;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getLama() {
        return lama;
    }

    public void setLama(String lama) {
        this.lama = lama;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public DKasir(String id, String nokamar, String harga, String lama, String total, String nama, String noktp) {
        this.id = id;
        this.nokamar = nokamar;
        this.harga = harga;
        this.lama = lama;
        this.total = total;
        this.nama = nama;
        this.noktp = noktp;
    }
}
