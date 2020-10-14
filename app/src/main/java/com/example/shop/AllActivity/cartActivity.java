package com.example.shop.AllActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shop.Adapter.adapterCart;
import com.example.shop.Adapter.adapterProduct;
import com.example.shop.R;
import com.example.shop.model.cart_product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class cartActivity extends AppCompatActivity {

    ListView listView;
    adapterCart cart;
    ImageView back;
    TextView thongBao;
    TextView thanhToan;
    static TextView TongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        findID();
        back();
        CheckData();
        eventUltil();
        catchOnItemListView();
        thanhtoan();
    }

    private void thanhtoan() {
        thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartActivity.this,payFor.class);
                startActivity(intent);
            }
        });
    }

    private void catchOnItemListView() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(cartActivity.this);
                builder.setTitle("xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MainActivity.listCart.size() <= 0) {
                            thongBao.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.listCart.remove(position);
                            cart.notifyDataSetChanged();
                            eventUltil();
                            if (MainActivity.listCart.size() <= 0) {
                                thongBao.setVisibility(View.VISIBLE);
                            } else {
                                thongBao.setVisibility(View.INVISIBLE);
                                cart.notifyDataSetChanged();
                                eventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cart.notifyDataSetChanged();
                        eventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void eventUltil() {
        long tongTien = 0;
        for (int i = 0; i < MainActivity.listCart.size(); i++) {
            tongTien += MainActivity.listCart.get(i).getGiaSP();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        TongTien.setText(decimalFormat.format(tongTien) + " Đ");
    }

    private void CheckData() {
        if (MainActivity.listCart.size() <= 0) {
            cart.notifyDataSetChanged();
            thongBao.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            cart.notifyDataSetChanged();
            thongBao.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    private void back() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findID() {
        listView = findViewById(R.id.List_cart);
        thongBao = findViewById(R.id.WhenCartEmpty);
        thanhToan = findViewById(R.id.toSign);
        TongTien = findViewById(R.id.allToPay);
        back = findViewById(R.id.back);
        cart = new adapterCart(cartActivity.this, (ArrayList<cart_product>) MainActivity.listCart);
        listView.setAdapter(cart);
    }
}