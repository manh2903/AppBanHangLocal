package com.ndm.appbanhang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ndm.appbanhang.R;
import com.ndm.appbanhang.utils.Util;

public class PersonActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvAddress;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initUI();
        initData();
        initListen();
    }
    
    private void initUI() {
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);
        img_back = findViewById(R.id.img_back);
    }

    private void initData() {
        tvName.setText("Họ và tên: " + Util.user.getUserName());
        tvPhone.setText("Số điện thoại: " + Util.user.getPhone());
        tvEmail.setText("Email: " + Util.user.getEmail());
        tvAddress.setText("Địa chỉ: " + Util.user.getAddress());
    }

    private void initListen() {
        img_back.setOnClickListener(v -> onBackPressed());
    }
}