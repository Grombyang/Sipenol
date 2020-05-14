package com.example.sipenol.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sipenol.Api;
import com.example.sipenol.Entitas.RegisterEntitas;
import com.example.sipenol.Entitas.ServerMessage;
import com.example.sipenol.GlobalFunction;
import com.example.sipenol.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText mUserame, mPassword, mRePassword, mNomorInduk;
    private static final String TAG = "Register";
    private Button mRegisterButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        init();

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = mPassword.getText().toString();
                String rePassword = mRePassword.getText().toString();
                if(!password.equals(rePassword)){
                    Toast.makeText(Register.this, "Password dan Re-Type Password haruslah sama!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String userId = "PL000";
                String username = mUserame.getText().toString();
                String nomorInduk = mNomorInduk.getText().toString();
                int peran = 1;
                RegisterEntitas regist = new RegisterEntitas(null, false, null, userId, password, peran, username, nomorInduk);
                Call<ServerMessage> sm = GlobalFunction.getInstance().initApi().doRegister(regist);
                final ProgressDialog pd = new ProgressDialog(Register.this);
                pd.setTitle("Loading...");
                pd.setMessage("Sedang memeriksa beberapa data...");
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                sm.enqueue(new Callback<ServerMessage>() {
                    @Override
                    public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                        pd.dismiss();
                        AlertDialog.Builder ab = new AlertDialog.Builder(Register.this);
                        ab.setCancelable(true);
                        ab.setNegativeButton("Cancel", null);
                        if(response.code() == Api.HTTP_ACCEPTED || response.code()==Api.HTTP_OK){
                            ServerMessage servMes = response.body();
                            ab.setMessage(servMes.getBody());
                            ab.setTitle(servMes.getTitle());
                            ab.show();
                        }else{
                            ab.setMessage("Terjadi kesalahan terhadap data diri!");
                            ab.setTitle("Error");
                            ab.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerMessage> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(Register.this, "Teradi kesalahan saat berhubungan dengan server!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });

            }
        });
    }
    private void init(){
        mUserame = findViewById(R.id.username_register_text_field);
        mPassword = findViewById(R.id.password_register_text_field);
        mRePassword = findViewById(R.id.repassword_register_text_field);
        mNomorInduk = findViewById(R.id.nomor_induk_register_text_field);
        mRegisterButton = findViewById(R.id.button_register);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 990){
            if (resultCode == Api.RESULT_OK){
                Toast.makeText(this, data.getStringExtra("pesan"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
