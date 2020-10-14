package com.example.shop.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface G_pr {
    @GET("getSanPham.php")
    Call<List<new_product>> getAllNewPr();
}
