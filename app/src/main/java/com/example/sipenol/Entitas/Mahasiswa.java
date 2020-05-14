package com.example.sipenol.Entitas;

import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    private String nama;
    @SerializedName(value= "kode_pedagang", alternate = {"kodePedagang"})
    private String kodePdg;
    @SerializedName("no_hp")
    private String noHp;
    @SerializedName("foto_pedagang")
    private String mProfil;

    public Mahasiswa(String nama, String kodePdg, String noHp) {
        this.nama = nama;
        this.kodePdg = kodePdg;
        this.noHp = noHp;
//        this.mProfil = mProfil;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKodePdg() {
        return kodePdg;
    }

    public void setKodePdg(String kodePdg) {
        this.kodePdg = kodePdg;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getmProfil() {
        return mProfil;
    }

    public void setmProfil(String mProfil) {
        this.mProfil = mProfil;
    }
}
