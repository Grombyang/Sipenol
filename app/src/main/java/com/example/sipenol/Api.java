package com.example.sipenol;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import com.example.sipenol.Entitas.LoginCredential;
import com.example.sipenol.Entitas.Mahasiswa;
import com.example.sipenol.Entitas.NewPassword;
import com.example.sipenol.Entitas.RegisterEntitas;
import com.example.sipenol.Entitas.SPD;
import com.example.sipenol.Entitas.ServerMessage;
import com.example.sipenol.Entitas.User;

public interface Api {
    public String IP = "192.168.5.5";
    public String PATH_TO_IMAGE = "http://"+IP+"/server/assets/images/profil/";
    public final String mBaseUrl = "http://" + IP + "/server/api/rest/";
    public String BASE_PROFIL_URL = "http://" + IP + "server/assets/images/profil/";
    public final int HTTP_ACCEPTED = 202;
    public final int HTTP_BAD_GATEWAY = 502;
    public final int HTTP_FORBIDDEN = 403;
    public final int HTTP_OK = 200;
    public final int HTTP_BAD_REQUEST = 400;
    public final int USER_PELANGGAN = 1;
    public final int USER_PEDAGANG = 0;
    public final int DEFAULT_ID_PESANAN = -1;
    public final int DEFAULT_STATUS_PESANAN = 1;
    public final int FRAGMENT_PESANAN_PELANGGAN = 2;
    public final int FRAGMENT_HOME = 0;
    public final int FRAGMENT_PEDAGANG = 1;
    public final int RESULT_OK = 999;
    public final int PESANAN_PENDING_REQUEST_CODE = 0;
    public final int PESANAN_QUEUE_REQUEST_CODE = 1;

    @POST("login")
    Call<User> login(@Body LoginCredential loginInfo);

    @GET("pedagang")
    Call<List<Mahasiswa>> getAllPedagang();

    @GET("pedagang")
    Call<Mahasiswa> getProfilPedagang(@Query("id_pedagang") String kode_pedagang);

    //getPesananByUserId
//    @GET("pesanan/{id}")
//    Call<List<Pesanan>> getPesananByUserId(@Path("id") String userId);
//
    @GET("SPD/{id}")
    Call<SPD> getProfilSPD(@Path("id") String userId);

    @POST("register")
    Call<ServerMessage> doRegister(@Body RegisterEntitas rEnt);

    //ambil dagangan sesuai pedagang
//    @GET("daganganPdg/{id}")
//    Call<List<Dagangan>> getDaganganByPedagang(@Path("id") String id_pedagang);
//
//    //pesanan untuk setiap pedagang
//    @GET("order/{id}")
//    Call<List<Pesanan>> getPesananByPedagang(@Path("id") String kode_pedagang, @Query("flag") Integer flag);
//
//    @POST("buatPesanan")
//    Call<ServerMessage> setPesanan(@Body Pesanan pesanan);

    @GET("statusPesanan")
    Call<ServerMessage> ubahStatus(@QueryMap Map<String, Integer> qMap);

    @POST("checkReplacePassword")
    Call<ServerMessage> ubahPassword(@Body NewPassword newPassword);

    @GET("hapusDagangan")
    Call<ServerMessage> hapusDagangan(@Query("id_dagangan") Integer id_dagangan);

//    @POST("tambahDagangan")
//    Call<ServerMessage> tambahDagangan(@Body Dagangan dagangan);
}
