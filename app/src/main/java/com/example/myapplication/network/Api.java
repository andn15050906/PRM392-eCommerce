package com.example.myapplication.network;

import com.example.myapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://640f344e4ed25579dc48ebbc.mockapi.io/";
//    String BASE_URL = "http://demo8940942.mockable.io/";
    @GET("products")
    Call<List<Product>> getProducts();

}
