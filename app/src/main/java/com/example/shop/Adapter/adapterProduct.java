package com.example.shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.model.new_product;
import com.example.shop.model.onClickItem;
import com.facebook.shimmer.Shimmer;

import java.text.DecimalFormat;
import java.util.List;

public class adapterProduct extends RecyclerView.Adapter<adapterProduct.MyViewHolder> {
    Context context;
    List<new_product> list;
    onClickItem onClickItem;

    public void setItemClick(onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_pr, price_pr,ID_pr;
        ImageView image_pr;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_pr = (ImageView) itemView.findViewById(R.id.image_pr);
            name_pr = (TextView) itemView.findViewById(R.id.name_pr);
            price_pr = (TextView) itemView.findViewById(R.id.price_pr);
            ID_pr = itemView.findViewById(R.id.ID_pr);

        }
    }

    public adapterProduct(Context context, List<new_product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final new_product newProduct = list.get(position);
        //initialize shimmer
        Shimmer shimmer = new Shimmer.ColorHighlightBuilder()
                .setBaseColor(Color.parseColor("#F3F3F3"))
                .setBaseAlpha(1)
                .setHighlightColor(Color.parseColor("#E7E7E7"))
                .setHighlightAlpha(1)
                .setDropoff(50)
                .build();

        holder.name_pr.setText(newProduct.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.price_pr.setText(decimalFormat.format(newProduct.getGiaSP()) + " Đ");
        holder.ID_pr.setText(newProduct.getID()+"");
        Glide.with(context).load(newProduct.getHinhAnhSP()).into(holder.image_pr);
        holder.name_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickName(newProduct.getID(),newProduct.getTenSP(),newProduct.getHinhAnhSP(),newProduct.getGiaSP(),newProduct.getMoTa());
            }
        });
        holder.image_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.ClickImage(newProduct.getID(),newProduct.getTenSP(),newProduct.getHinhAnhSP(),newProduct.getGiaSP(),newProduct.getMoTa());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
