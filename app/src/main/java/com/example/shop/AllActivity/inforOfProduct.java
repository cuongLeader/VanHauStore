package com.example.shop.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.model.cart_product;
import com.example.shop.model.new_product;

import java.text.DecimalFormat;

public class inforOfProduct extends AppCompatActivity {
    ImageView back, cart, image_pr;
    TextView name_pr, price_pr, infor, tocart, amountCart;
    Spinner amount_pr;
    static int IDSP = 0;
    static int price = 0;
    static String name = "";
    static String img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_of_product);
        find();
        b();
        getInformation();
        openCart();
        catchEventSpiner();
    }

    private void catchEventSpiner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, soluong);
        amount_pr.setAdapter(arrayAdapter);
    }


    private void find() {
        amountCart = findViewById(R.id.amountOfCart);
        int al = 0;
        for (int i = 0; i < MainActivity.listCart.size(); i++) {
            al += MainActivity.listCart.get(i).getSoLuong();
        }
        amountCart.setText(al + "");
        back = findViewById(R.id.back);
        cart = findViewById(R.id.cart);
        image_pr = findViewById(R.id.imgs);
        name_pr = findViewById(R.id.name);
        price_pr = findViewById(R.id.price);
        amount_pr = findViewById(R.id.amount);
        tocart = findViewById(R.id.toCart);
        infor = findViewById(R.id.infor);
    }

    void openCart() {
        tocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MainActivity.listCart.size() > 0) {
                    int sl = Integer.parseInt(amount_pr.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.listCart.size(); i++) {
                        if (MainActivity.listCart.get(i).getID() == IDSP) {
                            MainActivity.listCart.get(i).setSoLuong(MainActivity.listCart.get(i).getSoLuong() + sl);
                            if (MainActivity.listCart.get(i).getSoLuong() >= 10) {
                                MainActivity.listCart.get(i).setSoLuong(10);
                            }
                            MainActivity.listCart.get(i).setGiaSP(price * MainActivity.listCart.get(i).getSoLuong());
                            exists = true;
                        }
                    }
                    if (exists == false) {
                        int soLuong = Integer.parseInt(amount_pr.getSelectedItem().toString());
                        int giaMoi = soLuong * price;
                        MainActivity.listCart.add(new cart_product(IDSP, name, img, giaMoi, soLuong));
                    }
                } else {
                    int soLuong = Integer.parseInt(amount_pr.getSelectedItem().toString());
                    int giaMoi = soLuong * price;
                    MainActivity.listCart.add(new cart_product(IDSP, name, img.toString(), giaMoi, soLuong));
                }
                Intent intent = new Intent(inforOfProduct.this, cartActivity.class);
                startActivity(intent);
            }

        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inforOfProduct.this, cartActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getInformation() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        name_pr.setText(intent.getStringExtra("name"));
        img = intent.getStringExtra("img");
        IDSP = intent.getIntExtra("ID", 0);
        Glide.with(this).load(intent.getStringExtra("img")).into(image_pr);
        price = intent.getIntExtra("price", 0);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        price_pr.setText(decimalFormat.format(price) + " Đ");
        name_pr.setText(intent.getStringExtra("name"));
        infor.setText(intent.getStringExtra("infor"));
    }

    private void b() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}