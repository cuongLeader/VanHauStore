package com.example.shop.AllActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.shop.Adapter.adapterProduct;
import com.example.shop.R;
import com.example.shop.model.G_pr;
import com.example.shop.model.cart_product;
import com.example.shop.model.checkInternetConnect;
import com.example.shop.model.new_product;
import com.example.shop.model.onClickItem;
import com.example.shop.model.server;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    TextView amountCart;
    LinearLayout border;
    List<new_product> myList;
    adapterProduct adapterProduct;
    ImageView btnCart;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    ShimmerFrameLayout shimmerFrameLayout;
    public static List<cart_product> listCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = new ArrayList<>();
        nestedScrollView = findViewById(R.id.Scroll_view);
        progressBar = findViewById(R.id.progressBar);
        shimmerFrameLayout = findViewById(R.id.shimmer_layout);
        recyclerView = findViewById(R.id.rcl);
        if (checkInternetConnect.haveNetworkConnect(getApplicationContext())) {
            findID();
        } else {
            checkInternetConnect.shortToat(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.phone:
                        startActivity(new Intent(getApplicationContext(), phone.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.laptop:
                        startActivity(new Intent(getApplicationContext(), laptop.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.accessories:
                        startActivity(new Intent(getApplicationContext(), accessories.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), about.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        actionViewFlipper();
        // getNewPr();
    }

    private void actionViewFlipper() {
        List<String> advertise = new ArrayList<>();
        advertise.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/Z_Fold2_5G_Banner_8.9_v2.png");
        advertise.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/iWalk_Air_Dou_2_Banner_Homepage_11.9.jpg");
        advertise.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/Vivo_Chung_banner_12.9.2020.png");
        advertise.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/IPHONE_7_PLUS_128GB_10.9.2020.png");
        advertise.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/T_m_i_m_khai_tr_ng_Laptop_ASUS_Vivobook_11.9.png");
        for (int i = 0; i < advertise.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(MainActivity.this).load(advertise.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3999);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_advertise_in_right);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_advertise_out_right);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }

    private void findID() {
        amountCart = findViewById(R.id.amountOfCart);
        border = findViewById(R.id.borderOfamount);
        getJsonData(4);
        viewFlipper = findViewById(R.id.viewFlipper);
        putDataInRecy();
        shimmerFrameLayout.startShimmer();
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    progressBar.setVisibility(View.VISIBLE);
                    putDataInRecy();
                }
            }
        });
        cart();
        if (listCart != null) {

        } else {
            listCart = new ArrayList<>();
        }
        int al = 0;
        for (int i = 0; i < MainActivity.listCart.size(); i++) {
            al += MainActivity.listCart.get(i).getSoLuong();
        }
        amountCart.setText(al + "");
    }

    private void cart() {
        btnCart = findViewById(R.id.cart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, cartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void putDataInRecy() {

        adapterProduct = new adapterProduct(getApplicationContext(), myList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(adapterProduct);
        clickItem();
    }

    public void clickItem() {
        adapterProduct.setItemClick(new onClickItem() {
            @Override
            public void ClickImage(int ID, String name, String img, int price, String infor) {
                Intent intent = new Intent(MainActivity.this, inforOfProduct.class);
                intent.putExtra("ID", ID);
                intent.putExtra("name", name);
                intent.putExtra("img", img);
                intent.putExtra("price", price);
                intent.putExtra("infor", infor);
                startActivity(intent);

            }

            @Override
            public void onClickName(int ID, String name, String img, int price, String infor) {
                Intent intent = new Intent(MainActivity.this, inforOfProduct.class);
                intent.putExtra("ID", ID);
                intent.putExtra("name", name);
                intent.putExtra("img", img);
                intent.putExtra("price", price);
                intent.putExtra("infor", infor);
                startActivity(intent);
            }
        });
    }

    public void getJsonData(int a) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        G_pr service = retrofit.create(G_pr.class);
        Call<List<new_product>> call = service.getAllNewPr();
        call.enqueue(new Callback<List<new_product>>() {
            @Override
            public void onResponse(Call<List<new_product>> call, Response<List<new_product>> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                shimmerFrameLayout.setVisibility(View.GONE);
                getData(response.body(), a);
            }

            @Override
            public void onFailure(Call<List<new_product>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                shimmerFrameLayout.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(List<new_product> list, int x) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIDLoaiSanPham() == x) {
                myList.add(list.get(i));
            }
        }
    }
}