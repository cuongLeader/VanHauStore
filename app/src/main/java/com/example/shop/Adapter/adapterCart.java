package com.example.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shop.AllActivity.MainActivity;
import com.example.shop.AllActivity.cartActivity;
import com.example.shop.R;
import com.example.shop.model.cart_product;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class adapterCart extends BaseAdapter {
    Context context;
    ArrayList<cart_product> myList_cart;

    public adapterCart(Context context, ArrayList<cart_product> myList_cart) {
        this.context = context;
        this.myList_cart = myList_cart;
    }

    @Override
    public int getCount() {
        return myList_cart.size();
    }

    @Override
    public Object getItem(int i) {
        return myList_cart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView cart_name, cart_price, cart_amount;
        ImageView cart_img, cart_down, cart_up;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_oncart, null);
            viewHolder.cart_name = view.findViewById(R.id.cart_name);
            viewHolder.cart_price = view.findViewById(R.id.cart_price);
            viewHolder.cart_amount = view.findViewById(R.id.cart_amount);
            viewHolder.cart_img = view.findViewById(R.id.cart_img);
            viewHolder.cart_down = view.findViewById(R.id.cart_down);
            viewHolder.cart_up = view.findViewById(R.id.cart_up);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        cart_product product = (cart_product) getItem(i);
        viewHolder.cart_name.setText(product.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.cart_price.setText(decimalFormat.format(product.getGiaSP()) + " Đ");
        viewHolder.cart_amount.setText(product.getSoLuong() + "");
        Glide.with(context).load(product.getHinhAnhSP()).into(viewHolder.cart_img);
        int soL = Integer.parseInt(viewHolder.cart_amount.getText().toString());
        if (soL >= 10) {
            viewHolder.cart_up.setVisibility(View.INVISIBLE);
            viewHolder.cart_down.setVisibility(View.VISIBLE);
        } else if (soL <= 1) {
            viewHolder.cart_down.setVisibility(View.INVISIBLE);
            viewHolder.cart_up.setVisibility(View.VISIBLE);
        } else if (soL >= 1) {
            viewHolder.cart_down.setVisibility(View.VISIBLE);
            viewHolder.cart_up.setVisibility(View.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.cart_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slNew = Integer.parseInt(finalViewHolder.cart_amount.getText().toString()) + 1;
                int slht = MainActivity.listCart.get(i).getSoLuong();
                int giaht = MainActivity.listCart.get(i).getGiaSP();
                MainActivity.listCart.get(i).setSoLuong(slNew);
                int giaNew = (giaht * slNew) / slht;
                MainActivity.listCart.get(i).setGiaSP(giaNew);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.cart_price.setText(decimalFormat.format(giaNew) + " Đ");
                cartActivity.eventUltil();
                if (slNew > 9) {
                    finalViewHolder.cart_up.setVisibility(View.INVISIBLE);
                    finalViewHolder.cart_down.setVisibility(View.VISIBLE);
                    finalViewHolder.cart_amount.setText(slNew + "");
                } else {
                    finalViewHolder.cart_up.setVisibility(View.VISIBLE);
                    finalViewHolder.cart_down.setVisibility(View.VISIBLE);
                    finalViewHolder.cart_amount.setText(slNew + "");
                }
            }
        });
        viewHolder.cart_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slNew = Integer.parseInt(finalViewHolder.cart_amount.getText().toString()) - 1;
                int slht = MainActivity.listCart.get(i).getSoLuong();
                int giaht = MainActivity.listCart.get(i).getGiaSP();
                MainActivity.listCart.get(i).setSoLuong(slNew);
                int giaNew = (giaht * slNew) / slht;
                MainActivity.listCart.get(i).setGiaSP(giaNew);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.cart_price.setText(decimalFormat.format(giaNew) + " Đ");
                cartActivity.eventUltil();
                if (slNew < 2) {
                    finalViewHolder.cart_up.setVisibility(View.VISIBLE);
                    finalViewHolder.cart_down.setVisibility(View.INVISIBLE);
                    finalViewHolder.cart_amount.setText(slNew + "");
                } else {
                    finalViewHolder.cart_up.setVisibility(View.VISIBLE);
                    finalViewHolder.cart_down.setVisibility(View.VISIBLE);
                    finalViewHolder.cart_amount.setText(slNew + "");
                }
            }
        });
        return view;
    }
}
