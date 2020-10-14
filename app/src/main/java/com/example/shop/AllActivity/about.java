package com.example.shop.AllActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.shop.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//
public class about extends AppCompatActivity {
    private GoogleMap mMap;
    TextView amountCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.about);
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
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.accessories:
                        startActivity(new Intent(getApplicationContext(), accessories.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.about:
                        return true;
                }
                return false;
            }
        });
        amountCart = findViewById(R.id.amountOfCart);
        int al = 0;
        for (int i = 0; i<MainActivity.listCart.size();i++)
        {
            al +=MainActivity.listCart.get(i).getSoLuong();
        }
        amountCart.setText(al + "");
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        //add : Đai Mễ Nam Từ Liêm, Hà Nội, Việt Nam
//        LatLng myLocate = new LatLng(20.985913, 105.776763);
//        mMap.addMarker(new MarkerOptions()
//                .position(myLocate)
//                .title("Đai Mễ Nam Từ Liêm, Hà Nội, Việt Nam"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocate));
//    }
}