package com.ndm.appbanhang.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputEditText;
import com.ndm.appbanhang.R;
import com.ndm.appbanhang.enitities.User;
import com.ndm.appbanhang.utils.Util;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout layoutSignUp;
    TextInputEditText txtEmail, txtPassEdt;
    private Button btnSignIn;
    private ProgressDialog progressDialog;

    public static final String PREF_LOGIN = "LoginPrefs";
    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private TextView tv_forgot_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        initListener();
    }
    private void initUI() {
        layoutSignUp = findViewById(R.id.layout_sign_up);
        txtEmail = findViewById(R.id.txt_emailedt);
        txtPassEdt = findViewById(R.id.txt_passEdt);
        btnSignIn = findViewById(R.id.btn_sign_in);
        progressDialog = new ProgressDialog(this);
        tv_forgot_pw = findViewById(R.id.tv_forgot_pass);
        tv_forgot_pw.setVisibility(View.GONE);
    }
    private void initListener() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignIn();
            }
        });
    }

    private void onClickSignIn() {
        String strEmail = txtEmail.getText().toString().trim();
        String strPassword = txtPassEdt.getText().toString().trim();

        if (strEmail.isEmpty() || strPassword.isEmpty()) {
            // Hiển thị thông báo yêu cầu nhập đầy đủ thông tin
            Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ email và mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Đang đăng nhập...");
        progressDialog.show();

        if(strEmail.equals(Util.user.getEmail()) && strPassword.equals(Util.user.getPassWord())){
            saveLoginState(true);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveLoginState(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }


}