package com.example.sipenol.Entitas;

import com.google.gson.annotations.SerializedName;

public class User extends ServerMessage {
    @SerializedName(value = "user_id")
    private String mUserId;
    @SerializedName(value = "password")
    private String mPassword;
    @SerializedName(value = "peran")
    private int mPeran;
    @SerializedName(value = "username")
    private String mUsername;

    public User(String title, boolean status, String body, String mUserId, String mPassword, int mPeran, String mUsername) {
        super(title, status, body);
        this.mUserId = mUserId;
        this.mPassword = mPassword;
        this.mPeran = mPeran;
        this.mUsername = mUsername;
    }

    public User(String mUserId, String mPassword, int mPeran, String mUsername) {
        this.mUserId = mUserId;
        this.mPassword = mPassword;
        this.mPeran = mPeran;
        this.mUsername = mUsername;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword= mPassword;
    }

    public int getmPeran() {
        return mPeran;
    }

    public void setmPeran(int mPeran) {
        this.mPeran= mPeran;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername= mUsername;
    }
}
