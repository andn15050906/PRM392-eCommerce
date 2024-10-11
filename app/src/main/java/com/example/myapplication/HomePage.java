package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.home.SearchProduct;
import com.example.myapplication.model.Product;
import com.example.myapplication.network.RetrofitClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private Button btnAll,btnSamsung,btnXiaomi,btnAdd;
    private GridLayoutManager grid;
    private EditText searchBar;

    ProductListViewAdapter productListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        bindView();
        setupOnclick();
        setupListView();

        ((GlobalVariables) this.getApplication()).setupData();
        getProductListFromAPI();
    }

    private void bindView() {
        searchBar = findViewById(R.id.searchBar);
        btnAll = findViewById(R.id.allBtn);
        btnSamsung = findViewById(R.id.samsungBtn);
        btnXiaomi = findViewById(R.id.xiaomiBtn);
        btnAdd = findViewById(R.id.btnAdd);
        rcvProduct = findViewById(R.id.rcv_product);
    }

    private void setupOnclick() {
        searchBar.setOnClickListener(view -> openSearchPage());
        btnAll.setOnClickListener(view -> filter("all"));
        btnSamsung.setOnClickListener(view -> filter("Samsung"));
        btnXiaomi.setOnClickListener(view -> filter("Xiaomi"));
        btnAdd.setOnClickListener(view -> openCart());
    }

    private void setupListView() {
        productListViewAdapter = new ProductListViewAdapter(new ArrayList<>());
        rcvProduct.setAdapter(productListViewAdapter);
        grid = new GridLayoutManager(this, 2);
        rcvProduct.setLayoutManager(grid);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void openCart() {
        Intent intent = new Intent(this,ViewCart.class );
        startActivity(intent);
    }

    public void openSearchPage(){
        Intent intent = new Intent(HomePage.this, SearchProduct.class);
        startActivity(intent);
    }

    private void filter(String filter) {
        if (grid == null){
            return;
        }
        List<Product> list = new ArrayList<>();
        if (filter.equals("all")) {
            list = ((GlobalVariables) this.getApplication()).getProducts();
        }
        else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            list = ((GlobalVariables) this.getApplication()).getProducts().stream().filter(p -> p.getBrand().getBrandName().equals(filter)).collect(Collectors.toList());
        }
        updateListView(list);
    }

    private void getProductListFromAPI() {
        Call<List<Product>> call = RetrofitClient.getInstance().getApi().getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                for (Product p : list) {
                    setupProduct(p);
                }
                ((GlobalVariables) HomePage.this.getApplication()).setProducts(list);
                updateListView(list);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                getProductListFromAPI();
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateListView(List<Product> list) {
        productListViewAdapter.update(list);
        productListViewAdapter.notifyDataSetChanged();
    }

    private void setupProduct(Product product) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            product.setModelYear(LocalDateTime.now());
        }
        product.setImages(String.valueOf(R.drawable.chair));
        product.setBrand(((GlobalVariables) this.getApplication()).getBrands().get(new Random().nextInt(((GlobalVariables) this.getApplication()).getBrands().size())));
        product.setCategory(((GlobalVariables) this.getApplication()).getCategories().get(new Random().nextInt(((GlobalVariables) this.getApplication()).getCategories().size())));
        if (product.getBrand().getBrandName().equals("Samsung")) {
            product.setStore(((GlobalVariables) this.getApplication()).getStores().get(0));
        } else {
            product.setStore(((GlobalVariables) this.getApplication()).getStores().get(1));
        }
    }
}