package com.example.sipenol.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sipenol.GlobalFunction;
import com.example.sipenol.Api;
import com.example.sipenol.Entitas.User;
import com.example.sipenol.Entitas.LoginCredential;
import com.example.sipenol.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText mUsername, mPassword;
    private TextView registerText;
    private TextView lupaPassText;
    private Button mLoginButton;
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();

        mPassword.setOnEditorActionListener(editorListener);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsername.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Silahkan isi Username anda terlebih dahulu!!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mPassword.getText().toString().length() < 8) {
                    Toast.makeText(Login.this, "Panjang password haruslah 8 karakter!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Call<User> serverRespons = GlobalFunction.getInstance().initApi()
                        .login(new LoginCredential(mUsername.getText().toString(), mPassword.getText().toString()));
                final ProgressDialog progressDoalog = GlobalFunction.getInstance().generateProgressDialog(Login.this);
                progressDoalog.show();
                serverRespons.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        progressDoalog.dismiss();
                        if (response.code() == Api.HTTP_ACCEPTED){
                            User user = response.body();
                            if (user.isStatus()){
                                if (user.getmPeran() == 1){
                                    Intent intent = new Intent(Login.this, HomeScreen.class);//PelangganMainNav.class);
                                    intent.putExtra("user_id", user.getmUserId());
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(Login.this, HomeScreen.class);//PedagangMainNav.class);
                                    intent.putExtra("user_id", user.getmUserId());
                                    startActivity(intent);
                                }
                            }else{
                                GlobalFunction.getInstance()
                                        .generateAlertDialog(Login.this,"Anda Siapa?","Error");
                            }
                        }else{
                            GlobalFunction.getInstance()
                                    .generateAlertDialog(Login.this,"Password anda salah!!","Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        progressDoalog.dismiss();
                        Toast.makeText(Login.this, "Gagal", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Login.this, Register.class);
                startActivityForResult(intent, 990);
            }
        });
        lupaPassText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "jangan disebar", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, GantiPassword.class);
                startActivityForResult(intent, 990);
//                GlobalFunction.getInstance().generateAlertDialog(Login.this, "Silahkan hubungi Admin","Dasar Pikun");
            }
        });

    }

    private void init() {
        mUsername = findViewById(R.id.username_text_field);
        mPassword = findViewById(R.id.password_text_field);
        mLoginButton = findViewById(R.id.login_button);
        registerText = findViewById(R.id.register_text_view);
        lupaPassText = findViewById(R.id.lupaPassword);

    }

    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:{
                    if (mUsername.getText().toString().isEmpty()) {
                        Toast.makeText(Login.this, "Silahkan isi Username anda terlebih dahulu!!", Toast.LENGTH_SHORT).show();
                        return false;
                    } else if (mPassword.getText().toString().length() < 8) {
                        Toast.makeText(Login.this, "Panjang password haruslah 8 karakter!!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    Call<User> serverRespons = GlobalFunction.getInstance().initApi()
                            .login(new LoginCredential(mUsername.getText().toString(), mPassword.getText().toString()));
                    final ProgressDialog progressDoalog = GlobalFunction.getInstance().generateProgressDialog(Login.this);
                    progressDoalog.show();
                    serverRespons.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            progressDoalog.dismiss();
                            if (response.code() == Api.HTTP_ACCEPTED){
                                User user = response.body();
                                if (user.isStatus()){
                                    if (user.getmPeran() == 1){
                                        Intent intent = new Intent(Login.this, HomeScreen.class);//PelangganMainNav.class);
                                        intent.putExtra("user_id", user.getmUserId());
                                        startActivity(intent);
                                    }else {
                                        Intent intent = new Intent(Login.this, HomeScreen.class);//PedagangMainNav.class);
                                        intent.putExtra("user_id", user.getmUserId());
                                        startActivity(intent);
                                    }
                                }else{
                                    GlobalFunction.getInstance()
                                            .generateAlertDialog(Login.this,"Anda Siapa?","Error");
                                }
                            }else{
                                GlobalFunction.getInstance()
                                        .generateAlertDialog(Login.this,"Password anda salah!!","Error");
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            progressDoalog.dismiss();
                            Toast.makeText(Login.this, "Gagal", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onFailure: "+t.getMessage());
                        }
                    });
                }
            }
            return false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 990){
            if (resultCode == Api.RESULT_OK){
                Toast.makeText(this, "Silahkan Login dengan Akun anda!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}