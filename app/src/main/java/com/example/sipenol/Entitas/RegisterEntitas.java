package com.example.sipenol.Entitas;

import com.google.gson.annotations.SerializedName;

public class RegisterEntitas extends User{
    @SerializedName("nomor_induk")
    private String nip;

    public RegisterEntitas(String title, boolean status, String body, String mUserId, String mPassword, int mPeran, String mUsername, String nip) {
        super(title, status, body, mUserId, mPassword, mPeran, mUsername);
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
