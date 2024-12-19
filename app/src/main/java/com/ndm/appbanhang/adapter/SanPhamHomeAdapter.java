package com.ndm.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.ndm.appbanhang.R;
import com.ndm.appbanhang.activity.ProductDetailActivity;
import com.ndm.appbanhang.enitities.Product;

import java.util.List;

public class SanPhamHomeAdapter extends RecyclerView.Adapter<SanPhamHomeAdapter.SanPhamHome> {

    Context context;
    List<Product> array;

    public SanPhamHomeAdapter(Context context, List<Product> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public SanPhamHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_home, parent, false);
        return new SanPhamHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamHome holder, int position) {
        Product product = array.get(position);
        if (product == null) {
            return;
        } else {
            holder.txtten.setText(product.getName());
            holder.txtgia.setText(String.valueOf(product.getCost()));

            int img = product.getImage();

            if (img != 0) {
                Glide.with(context)
                        .load(img)
                        .into(holder.imghinhanh);
            } else {
                Glide.with(context)
                        .load(R.drawable.ic_home)
                        .into(holder.imghinhanh);
            }


            if (product.getIsNew() == 1) {
                holder.ic_new.setVisibility(View.VISIBLE);
            } else {
                holder.ic_new.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("product", product);
                    context.startActivity(intent);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if (array == null) {
            return 0;
        }
        return array.size();
    }

    public class SanPhamHome extends RecyclerView.ViewHolder {
        TextView txtgia, txtten;
        ImageView imghinhanh, ic_new;

        public SanPhamHome(@NonNull View itemView) {
            super(itemView);
            txtgia = itemView.findViewById(R.id.tv_gia_sp);
            txtten = itemView.findViewById(R.id.tv_ten_sp);
            imghinhanh = itemView.findViewById(R.id.itemsp_image);
            ic_new = itemView.findViewById(R.id.ic_new);
        }
    }
}
