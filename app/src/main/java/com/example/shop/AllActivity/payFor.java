package com.example.shop.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLanguage;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.R;
import com.example.shop.model.checkInternetConnect;
import com.example.shop.model.server;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class payFor extends AppCompatActivity {
    EditText ten, sdt, mail, diachi, yeucau;
    TextView xacNhan, troLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_for);
        findID();
        comeback();
        if (checkInternetConnect.haveNetworkConnect(getApplicationContext())) {
            eventButton();
        } else {
            checkInternetConnect.shortToat(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void eventButton() {
        xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = ten.getText().toString().trim();
                String s = sdt.getText().toString().trim();
                String m = mail.getText().toString().trim();
                String d = diachi.getText().toString().trim();
                String y = yeucau.getText().toString();
                if (t.length() > 0 && s.length() > 0 && m.length() > 0 && d.length() > 0 && y.length() >= 0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server.urlCart, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String ID_don_hang) {
                            if (Integer.parseInt(ID_don_hang) > 0) {
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest sr = new StringRequest(Request.Method.POST, server.urlInforCart, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")) {
                                            MainActivity.listCart.clear();
                                            Toast.makeText(payFor.this, "Bạn đã thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                                            Intent inr = new Intent(payFor.this, MainActivity.class);
                                            startActivity(inr);
                                            Toast.makeText(payFor.this, "Mời bạn tiếp tục mua hàng", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(payFor.this, "Giỏ hàng của bạn bị lỗi", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i < MainActivity.listCart.size(); i++) {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("ID_don_hang", ID_don_hang);
                                                jsonObject.put("ID_san_pham", MainActivity.listCart.get(i).getID());
                                                jsonObject.put("ten_san_pham", MainActivity.listCart.get(i).getTenSP());
                                                jsonObject.put("so_luong", MainActivity.listCart.get(i).getSoLuong());
                                                jsonObject.put("gia_san_pham", MainActivity.listCart.get(i).getGiaSP());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> has = new HashMap<>();
                                        has.put("json", jsonArray.toString());
                                        return has;
                                    }
                                };
                                queue.add(sr);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenKH", t);
                            hashMap.put("SDT", s);
                            hashMap.put("email", m);
                            hashMap.put("diaChi", d);
                            hashMap.put("yeucau", y);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                } else {
                    checkInternetConnect.shortToat(getApplicationContext(), "Bạn hãy kiểm tra lại dữ liệu");
                }
            }
        });
    }

    private void comeback() {
        troLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findID() {
        ten = findViewById(R.id.tenKH);
        sdt = findViewById(R.id.sdtKH);
        mail = findViewById(R.id.emailKH);
        diachi = findViewById(R.id.DiachiKH);
        yeucau = findViewById(R.id.yeucauKH);
        xacNhan = findViewById(R.id.finaly);
        troLai = findViewById(R.id.backToContinue);
    }
}