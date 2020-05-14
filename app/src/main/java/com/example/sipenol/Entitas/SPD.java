package com.example.sipenol.Entitas;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SPD implements Parcelable {
    @SerializedName(value = "user_id", alternate = "userId")
    private String mUserid;
    @SerializedName(value = "nomor_induk", alternate = "nomorInduk")
    private String mNomorInduk;
    @SerializedName(value = "nama")
    private String mNama;
    @SerializedName(value = "jabatan")
    private String mJabatan;
    @SerializedName(value = "no_hp")
    private String mNoHp;
    @SerializedName("foto_pelanggan")
    private String fotoPelanggan;

    public SPD(String mUserid, String mNomorInduk, String mNama, String mJabatan, String mNoHp, String fotoPelanggan) {
        this.mUserid = mUserid;
        this.mNomorInduk = mNomorInduk;
        this.mNama = mNama;
        this.mJabatan = mJabatan;
        this.mNoHp = mNoHp;
        this.fotoPelanggan = fotoPelanggan;
    }

    public String getmUserid() {
        return mUserid;
    }

    public void setmUserid(String mUserid) {
        this.mUserid = mUserid;
    }

    public String getmNomorInduk() {
        return mNomorInduk;
    }

    public void setmNomorInduk(String mNomorInduk) {
        this.mNomorInduk = mNomorInduk;
    }

    public String getmNama() {
        return mNama;
    }

    public void setmNama(String mNama) {
        this.mNama = mNama;
    }

    public String getmJabatan() {
        return mJabatan;
    }

    public void setmJabatan(String mJabatan) {
        this.mJabatan = mJabatan;
    }

    public String getmNoHp() {
        return mNoHp;
    }

    public void setmNoHp(String mNoHp) {
        this.mNoHp = mNoHp;
    }

    public String getFotoPelanggan() {
        return fotoPelanggan;
    }

    public void setFotoPelanggan(String fotoPelanggan) {
        this.fotoPelanggan = fotoPelanggan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mNama);
        dest.writeString(this.mJabatan);
        dest.writeString(this.mNoHp);
        dest.writeString(this.mNomorInduk);
        dest.writeString(this.mUserid);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public SPD createFromParcel(Parcel in){
            return new SPD(in);
        }
        public SPD[] newArray(int size){
            return new SPD[size];
        }
    };
    public SPD(Parcel in){
        this.mNama = in.readString();
        this.mUserid = in.readString();
        this.mNomorInduk = in.readString();
        this.mJabatan = in.readString();
        this.mNoHp = in.readString();
}}
