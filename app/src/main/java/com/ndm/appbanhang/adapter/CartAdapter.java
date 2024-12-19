package com.ndm.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ndm.appbanhang.R;
import com.ndm.appbanhang.enitities.Product;
import com.ndm.appbanhang.utils.CartManager;
import com.ndm.appbanhang.utils.Util;

import java.util.List;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    public interface OnCartChangeListener {
        void onCartChanged();
    }
    private List<Product> productList;
    private Context context;
    private OnCartChangeListener cartChangeListener;

    public CartAdapter(List<Product> productList, Context context, OnCartChangeListener cartChangeListener) {
        this.productList = productList;
        this.context = context;
        this.cartChangeListener = cartChangeListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductPrice.setText(String.valueOf(product.getCost()));
        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));

        Glide.with(context).load(product.getImage()).into(holder.imgProduct);

        holder.btnIncrease.setOnClickListener(v -> {
            // Kiểm tra số lượng trong danh sách gốc
            Product originalProduct = findOriginalProduct(product.getId());
            if (originalProduct != null && product.getQuantity() < originalProduct.getQuantity()) {
                product.setQuantity(product.getQuantity() + 1);
                notifyItemChanged(position);
                if (cartChangeListener != null) cartChangeListener.onCartChanged();
            } else {
                // Hiển thị thông báo nếu vượt quá số lượng cho phép
                 Toast.makeText(context, "Số lượng sản phẩm đã đạt giới hạn!", Toast.LENGTH_SHORT).show();
            }
        });


        holder.btnDecrease.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                notifyItemChanged(position);
            } else {
                productList.remove(position);
                notifyItemRemoved(position);
            }
            if (cartChangeListener != null) cartChangeListener.onCartChanged();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvProductName, tvProductPrice, tvQuantity;
        Button btnIncrease, btnDecrease;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            btnIncrease = itemView.findViewById(R.id.btn_increase_quantity);
            btnDecrease = itemView.findViewById(R.id.btn_decrease_quantity);
        }
    }

    private Product findOriginalProduct(int productId) {
        for (Product p : Util.products) {
            if (p.getId() == productId) {
                return p;
            }
        }
        return null;
    }
}
