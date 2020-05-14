package com.example.sipenol.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sipenol.Api;
import com.example.sipenol.Entitas.NewPassword;
import com.example.sipenol.Entitas.ServerMessage;
import com.example.sipenol.GlobalFunction;
import com.example.sipenol.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GantiPassword extends AppCompatActivity {
    private EditText mNomorInduk, mPasswordLama, mPasswordBaru, mRePasswordBaru ;
    private static final String TAG = "Ganti Password";
    private Button mGantiPasswordButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ganti_password);
        init();

        mGantiPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordLama = mPasswordLama.getText().toString();
                String nomorInduk = mNomorInduk.getText().toString();
                String passwordBaru = mPasswordBaru.getText().toString();
                String rePasswordBaru = mRePasswordBaru.getText().toString();

                if (!passwordBaru.equals(rePasswordBaru)) {
                    Toast.makeText(GantiPassword.this, "Password Baru dan Re-Type Password Baru haruslah sama!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Call<ServerMessage> sm = GlobalFunction.getInstance().initApi().ubahPassword(new NewPassword(nomorInduk, passwordLama, passwordBaru));
                final ProgressDialog pd = GlobalFunction.getInstance().generateProgressDialog(GantiPassword.this);
                pd.show();
                sm.enqueue(new Callback<ServerMessage>() {
                    @Override
                    public void onResponse(Call<ServerMessage> call, Response<ServerMessage> response) {
                        pd.dismiss();
                        if (response.code() <= 299|| response.code() >= 200) {
                            ServerMessage servMes = response.body();
                            GlobalFunction.getInstance().generateAlertDialog(GantiPassword.this, servMes.getBody(), servMes.getTitle());
                            Intent intent = new Intent();
                            intent.putExtra("pesan", "selesai");
                            setResult(Api.RESULT_OK, intent);
                            finish();
                        } else {
                            GlobalFunction.getInstance().generateAlertDialog(GantiPassword.this, response.body().getBody(),response.body().getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerMessage> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(GantiPassword.this, "Teradi kesalahan saat berhubungan dengan server!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });

            }
        });
    }
    private void init(){
        mNomorInduk = findViewById(R.id.nomor_induk_gantiPass_text_field);
        mPasswordLama = findViewById(R.id.password_lama_gantiPass_text_field);
        mPasswordBaru = findViewById(R.id.password_baru_gantiPass_text_field);
        mRePasswordBaru = findViewById(R.id.repassword_gantiPass_text_field);
        mGantiPasswordButton = findViewById(R.id.button_register);

    }
}
