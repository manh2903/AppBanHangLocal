package com.ndm.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ndm.appbanhang.R;
import com.ndm.appbanhang.adapter.CommentAdapter;
import com.ndm.appbanhang.enitities.Comment;
import com.ndm.appbanhang.enitities.Product;
import com.ndm.appbanhang.utils.CartManager;
import com.ndm.appbanhang.utils.Util;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {
    private WebView productDescriptionWebView;
    private NotificationBadge badge ;
    private RecyclerView rcvComments;
    private TextView tvProductName, tvProductPrice, tvQuantity;
    private ImageView imgProductImage, imgBack;
    private Button btnAddToCart, btnBuyNow;

    private FrameLayout framegiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initViews();
        updateBadge();

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");
        if (product != null) {
            displayProductInfo(product);
        }

        setupComments();
        setupClickListeners(product);
    }

    @Override
    protected void onResume() {
        updateBadge();
        super.onResume();
    }

    private void initViews() {
        tvProductName = findViewById(R.id.product_name);
        tvProductPrice = findViewById(R.id.product_price);
        tvQuantity = findViewById(R.id.tv_quantity);
        imgProductImage = findViewById(R.id.product_image);
        btnAddToCart = findViewById(R.id.add_to_cart_button);
        btnBuyNow = findViewById(R.id.btn_buy);
        imgBack = findViewById(R.id.img_back);
        rcvComments = findViewById(R.id.rcv_comments);
        badge = findViewById(R.id.menu_sl);
        framegiohang = findViewById(R.id.framegiohang);
        productDescriptionWebView = findViewById(R.id.product_description_webview);
    }

    private void displayProductInfo(Product product) {
        tvProductName.setText(product.getName());

        // Format price to currency style
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedPrice = formatter.format(product.getCost());
        tvProductPrice.setText(formattedPrice);
        tvQuantity.setText(String.valueOf(product.getQuantity()));
        productDescriptionWebView.loadData(product.getDescription(), "text/html", "UTF-8");

        int img = product.getImage();
        if (img != 0) {
            Glide.with(this)
                    .load(img)
                    .into(imgProductImage);
        } else {
            Glide.with(this)
                    .load(R.drawable.ic_home)  // Placeholder if no image available
                    .into(imgProductImage);
        }
    }

    private void setupComments() {
        rcvComments.setLayoutManager(new LinearLayoutManager(this));
        List<Comment> fakeComments = Util.generateFakeComments();  // Assuming this method generates fake comments
        CommentAdapter commentAdapter = new CommentAdapter(fakeComments);
        rcvComments.setAdapter(commentAdapter);
        rcvComments.setVerticalScrollBarEnabled(false);
        rcvComments.setHorizontalScrollBarEnabled(false);
    }

    private void setupClickListeners(final Product product) {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setQuantity(1);
                boolean isAdded = CartManager.addProductToCart(product);
                if (isAdded) {
                    Toast.makeText(ProductDetailActivity.this, "Sản phẩm đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                    updateBadge();
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Sản phẩm đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        framegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyNow(product);
            }
        });
    }

    private void updateBadge() {
        int count = CartManager.Count();
        if (count > 0) {
            badge.setNumber(count);
            badge.setVisibility(View.VISIBLE);
        } else {
            badge.setVisibility(View.GONE);
        }
    }

    private void buyNow(Product product) {
        // Code to proceed with immediate purchase
        // You can implement this functionality based on your app's flow (e.g., navigate to payment screen)
    }
}
