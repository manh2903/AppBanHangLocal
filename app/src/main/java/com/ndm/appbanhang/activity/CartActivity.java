package com.ndm.appbanhang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndm.appbanhang.R;
import com.ndm.appbanhang.adapter.CartAdapter;
import com.ndm.appbanhang.enitities.Product;
import com.ndm.appbanhang.utils.CartManager;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCartChangeListener{
    private RecyclerView rcvCart;
    private TextView tvEmptyCart, txtTongTien;
    private Button btnClose, btnPlaceOrder;

    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initViews();
        loadCartData();
        setupClickListeners();
    }

    private void initViews() {
        rcvCart = findViewById(R.id.rcv_cart);
        tvEmptyCart = findViewById(R.id.tv_empty_cart);
        txtTongTien = findViewById(R.id.txt_tongtien);
        btnClose = findViewById(R.id.btn_close);
        btnPlaceOrder = findViewById(R.id.add_to_cart_button);
        imgBack = findViewById(R.id.img_back);
        rcvCart.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadCartData() {
        if (CartManager.getCart().isEmpty()) {
            tvEmptyCart.setVisibility(View.VISIBLE);
            rcvCart.setVisibility(View.GONE);
            txtTongTien.setVisibility(View.GONE);
            btnPlaceOrder.setVisibility(View.GONE);
            btnClose.setVisibility(View.VISIBLE);
        } else {
            tvEmptyCart.setVisibility(View.GONE);
            rcvCart.setVisibility(View.VISIBLE);
            txtTongTien.setVisibility(View.VISIBLE);
            btnPlaceOrder.setVisibility(View.VISIBLE);

            CartAdapter cartAdapter = new CartAdapter(CartManager.getCart(),this, this);
            rcvCart.setAdapter(cartAdapter);

            updateTotalPrice();
        }
    }

    private void updateTotalPrice() {
        int total = 0;
        for (Product product : CartManager.getCart()) {
            total += product.getQuantity() * product.getCost();
        }
        txtTongTien.setText("Tổng tiền: " + total + " đ");
    }

    private void setupClickListeners() {
        btnClose.setOnClickListener(v -> finish());

        btnPlaceOrder.setOnClickListener(v -> {
            // Xử lý đặt hàng
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onCartChanged() {
        updateTotalPrice();
    }
}
