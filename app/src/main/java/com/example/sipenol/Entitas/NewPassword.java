package com.example.sipenol.Entitas;

import com.google.gson.annotations.SerializedName;

public class NewPassword {
    @SerializedName("nomor_induk")
    private String mNomorInduk;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("new_password")
    private String mNewPassword;

    public NewPassword(String mNomorInduk, String mPassword, String mNewPassword) {
        this.mNomorInduk = mNomorInduk;
        this.mPassword = mPassword;
        this.mNewPassword = mNewPassword;
    }

    public String getmNomorInduk() {
        return mNomorInduk;
    }

    public void setmNomorInduk(String mNomorInduk) {
        this.mNomorInduk = mNomorInduk;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmNewPassword() {
        return mNewPassword;
    }

    public void setmNewPassword(String mNewPassword) {
        this.mNewPassword = mNewPassword;
    }
}
