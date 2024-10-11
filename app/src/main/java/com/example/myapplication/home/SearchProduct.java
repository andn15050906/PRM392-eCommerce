package com.example.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchProduct extends AppCompatActivity {

    private RecyclerView rcv_search_product;
    private GridLayoutManager grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_product);

        rcv_search_product = findViewById(R.id.rcv_search_product);
        grid = new GridLayoutManager(this,2);
        rcv_search_product.setLayoutManager(grid);
        SearchProductViewAdapter adap = new SearchProductViewAdapter(getSearchProductList());
        rcv_search_product.setAdapter(adap);
    }

    private List<Product> getSearchProductList() {
        String iphone = "" +R.drawable.iphone1;
        List<Product> searchProductList = new ArrayList<>();
        searchProductList.add(new Product(iphone,"Iphone",1000.0f));
        searchProductList.add(new Product(iphone,"Iphone",1000.0f));
        searchProductList.add(new Product(iphone,"Iphone",1000.0f));
        searchProductList.add(new Product(iphone,"Iphone",1000.0f));

        return searchProductList;
    }


}