package com.ndm.appbanhang.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.ndm.appbanhang.R;
import com.ndm.appbanhang.adapter.SanPhamHomeAdapter;
import com.ndm.appbanhang.enitities.Product;
import com.ndm.appbanhang.utils.GridSpacingItemDecoration;
import com.ndm.appbanhang.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ViewFlipper viewFlipper;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private RecyclerView rcv_list_item;
    private SanPhamHomeAdapter sanPhamHomeAdapter;
    private ImageView imgsearch;
    private FrameLayout framegiohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data();
        initUI();
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        ActionViewFlipper();
        getProduct();
        initListen();
        displayProducts();
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewlipper);
        navigationView = findViewById(R.id.navigation_view);
        rcv_list_item = findViewById(R.id.rcv_list_item);
        imgsearch = findViewById(R.id.imgsearch);
        framegiohang = findViewById(R.id.framegiohang);
        progressDialog = new ProgressDialog(this);
    }

    private void initListen() {
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intent);
            }
        });
        framegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CartActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void ActionViewFlipper() {
        List<Integer> mangquangcao = new ArrayList<>();
        mangquangcao.add(R.drawable.img_slide_1);
        mangquangcao.add(R.drawable.img_slide_2);
        mangquangcao.add(R.drawable.img_slide_3);

        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(mangquangcao.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_rigth);

        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }


    private void getProduct() {
    }

    private void displayProducts() {
        sanPhamHomeAdapter = new SanPhamHomeAdapter(this, Util.products);
        rcv_list_item.setAdapter(sanPhamHomeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rcv_list_item.setLayoutManager(layoutManager);
        int spacing = getResources().getDimensionPixelSize(R.dimen.spacing);
        rcv_list_item.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_logo_out) {
//            handleLogout();
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(getApplicationContext(), PersonActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_post) {
//            Intent intent = new Intent(getApplicationContext(), PostActitivy.class);
//            startActivity(intent);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void data() {
        Util.products.add(new Product(1, "Classic Shampoo", "Dầu gội dưỡng ẩm với tinh chất tự nhiên", 120000, 2, 1, R.drawable.image_1, 1));
        Util.products.add(new Product(2, "Moisturizing Lotion", "Sữa dưỡng ẩm làm mềm da và cung cấp độ ẩm", 150000, 2, 2, R.drawable.image_2, 1));
        Util.products.add(new Product(3, "Herbal Shampoo", "Dầu gội thảo dược phục hồi tóc hư tổn", 150000, 30, 2, R.drawable.image_3, 1));
        Util.products.add(new Product(4, "Hair Conditioner", "Dầu xả làm mềm tóc và bảo vệ tóc khỏi hư tổn", 100000, 40, 0, R.drawable.image_4, 3));
        Util.products.add(new Product(5, "Collagen Shampoo", "Dầu gội collagen làm chắc khỏe tóc", 200000, 25, 1, R.drawable.image_5, 1));
        Util.products.add(new Product(6, "Argan Hair Oil", "Dầu argan cao cấp dưỡng tóc bóng mượt", 300000, 15, 2, R.drawable.image_6, 2));
        Util.products.add(new Product(7, "Protein Hair Mask", "Mặt nạ tóc cung cấp protein giúp tóc chắc khỏe", 250000, 20, 1, R.drawable.image_7, 3));
        Util.products.add(new Product(8, "Volume Shampoo", "Dầu gội tăng độ phồng cho tóc mỏng", 170000, 45, 2, R.drawable.image_8, 1));
        Util.products.add(new Product(9, "Coconut Hair Oil", "Dầu dừa nguyên chất nuôi dưỡng tóc và da đầu", 130000, 60, 0, R.drawable.image_9, 1));
        Util.products.add(new Product(10, "Organic Shampoo", "Dầu gội hữu cơ thân thiện môi trường", 180000, 35, 0, R.drawable.image_10, 1));
        Util.products.add(new Product(11, "Tea Tree Shampoo", "Dầu gội tràm trà làm sạch da đầu hiệu quả", 170000, 45, 2, R.drawable.image_11, 1));
        Util.products.add(new Product(12, "Natural Conditioner", "Dầu xả tự nhiên giúp tóc mềm mượt", 150000, 40, 1, R.drawable.image_12, 2));
        Util.products.add(new Product(13, "Keratin Shampoo", "Dầu gội keratin tái tạo tóc hư tổn", 220000, 30, 1, R.drawable.image_13, 1));
        Util.products.add(new Product(14, "Anti-Dandruff Shampoo", "Dầu gội trị gàu hiệu quả", 170000, 50, 2, R.drawable.image_14, 2));
        Util.products.add(new Product(15, "Deep Repair Mask", "Mặt nạ phục hồi sâu cho tóc", 250000, 20, 0, R.drawable.image_15, 2));
        Util.products.add(new Product(16, "Herbal Hair Oil", "Dầu thảo dược dưỡng tóc", 300000, 10, 2, R.drawable.image_16, 1));
        Util.products.add(new Product(17, "Volume Conditioner", "Dầu xả tăng độ phồng cho tóc", 150000, 25, 1, R.drawable.image_17, 2));
        Util.products.add(new Product(18, "Daily Care Shampoo", "Dầu gội cho tóc thường dùng hàng ngày", 120000, 50, 0, R.drawable.image_18, 1));
        Util.products.add(new Product(19, "Detangling Spray", "Xịt dưỡng tóc giúp gỡ rối hiệu quả", 200000, 15, 2, R.drawable.image_19, 1));
        Util.products.add(new Product(20, "Smoothening Lotion", "Sản phẩm làm mượt tóc và giảm khô xơ", 180000, 30, 1, R.drawable.image_20, 1));
        Util.products.add(new Product(21, "Brightening Shampoo", "Dầu gội làm sáng và sạch tóc", 160000, 40, 1, R.drawable.image_21, 3));
        Util.products.add(new Product(22, "Herbal Hair Pack", "Gói thảo dược dưỡng tóc", 300000, 15, 2, R.drawable.image_22, 1));
        Util.products.add(new Product(23, "Shampoo and Conditioner Set", "Bộ dầu gội và xả hoàn hảo", 400000, 20, 1, R.drawable.image_23, 2));
        Util.products.add(new Product(24, "Silky Hair Serum", "Serum tóc giúp tóc mượt mà", 220000, 15, 2, R.drawable.image_24, 2));
        Util.products.add(new Product(25, "Collagen Hair Pack", "Mặt nạ collagen nuôi dưỡng tóc", 320000, 10, 0, R.drawable.image_25, 3));
        Util.products.add(new Product(26, "Vitamin Hair Spray", "Xịt dưỡng vitamin cho tóc chắc khỏe", 180000, 35, 1, R.drawable.image_26, 1));
        Util.products.add(new Product(27, "Daily Shampoo", "Dầu gội dùng hằng ngày cho tóc yếu", 150000, 50, 1, R.drawable.image_27, 2));
        Util.products.add(new Product(28, "Detox Shampoo", "Dầu gội thải độc da đầu", 200000, 25, 2, R.drawable.image_28, 1));
        Util.products.add(new Product(29, "Repair Shampoo", "Dầu gội phục hồi tóc hư tổn nặng", 250000, 20, 0, R.drawable.image_29, 2));
        Util.products.add(new Product(30, "Refreshing Hair Mist", "Xịt dưỡng tóc làm dịu mát tức thì", 120000, 30, 1, R.drawable.image_30, 1));
        Util.products.add(new Product(31, "Anti-Frizz Lotion", "Sản phẩm giảm xơ rối cho tóc", 150000, 40, 0, R.drawable.image_31, 2));
        Util.products.add(new Product(32, "Organic Hair Care Set", "Bộ chăm sóc tóc hữu cơ gồm dầu gội và xả", 350000, 25, 1, R.drawable.image_32, 1));
        Util.products.add(new Product(33, "Collagen Shampoo", "Dầu gội collagen giúp tóc bóng mượt và khỏe mạnh", 200000, 20, 2, R.drawable.image_33, 2));
        Util.products.add(new Product(34, "Herbal Repair Shampoo", "Dầu gội thảo dược phục hồi tóc hư tổn", 180000, 30, 1, R.drawable.image_34, 1));
        Util.products.add(new Product(35, "Peppermint Shampoo", "Dầu gội bạc hà làm sạch và mát da đầu", 160000, 50, 0, R.drawable.image_35, 2));
        Util.products.add(new Product(36, "Hair Strengthening Kit", "Bộ sản phẩm giúp tóc chắc khỏe gồm dầu gội và dưỡng", 400000, 15, 2, R.drawable.image_36, 3));
        Util.products.add(new Product(37, "Deep Nourishing Oil", "Dầu dưỡng tóc chuyên sâu giúp tóc mềm mượt", 300000, 20, 1, R.drawable.image_37, 2));
        Util.products.add(new Product(38, "Brightening Shampoo", "Dầu gội làm sáng và sạch tóc cho mọi loại tóc", 170000, 40, 1, R.drawable.image_38, 1));
        Util.products.add(new Product(39, "Silky Hair Care Duo", "Bộ dầu gội và xả cho tóc mượt mà", 320000, 30, 0, R.drawable.image_39, 2));
        Util.products.add(new Product(40, "Coconut Hair Lotion", "Sữa dưỡng tóc chiết xuất từ dừa", 150000, 50, 1, R.drawable.image_40, 1));
        Util.products.add(new Product(41, "Protein Shampoo", "Dầu gội cung cấp protein phục hồi tóc yếu", 220000, 25, 2, R.drawable.image_41, 2));
        Util.products.add(new Product(42, "Natural Shampoo", "Dầu gội tự nhiên, an toàn và thân thiện với môi trường", 180000, 35, 0, R.drawable.image_42, 3));
        Util.products.add(new Product(43, "Shiny Hair Serum", "Serum dưỡng tóc bóng mượt và giảm xơ rối", 250000, 20, 1, R.drawable.image_43, 1));
        Util.products.add(new Product(44, "Herbal Scalp Shampoo", "Dầu gội thảo dược chăm sóc da đầu", 170000, 40, 2, R.drawable.image_44, 1));
        Util.products.add(new Product(45, "Nourishing Shampoo", "Dầu gội dưỡng ẩm cho tóc khô", 190000, 30, 0, R.drawable.image_45, 3));
        Util.products.add(new Product(46, "Moisture Restore Shampoo", "Dầu gội phục hồi độ ẩm cho tóc", 210000, 25, 1, R.drawable.image_46, 1));
        Util.products.add(new Product(47, "Hair Repair Serum", "Serum phục hồi tóc hư tổn", 270000, 20, 2, R.drawable.image_47, 2));
        Util.products.add(new Product(48, "Vitamin C Hair Oil", "Dầu dưỡng tóc vitamin C", 300000, 15, 0, R.drawable.image_48, 1));
    }


}