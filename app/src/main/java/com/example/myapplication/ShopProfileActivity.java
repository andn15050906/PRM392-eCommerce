package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.model.Store;

public class ShopProfileActivity extends AppCompatActivity {
    Store store;
    TextView storeName, storeProducts, storeAddress;
    ImageView storeImage;
    Button showAllProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);

        setupComponent();
        setupUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void setupComponent() {
        Integer storeId = getIntent().getIntExtra("storeId", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            store = ((GlobalVariables) this.getApplication()).getStores().stream().filter(p -> p.getStoreId().equals(storeId)).findAny().orElse(store = null);
        }
    }

    private void setupUI() {
        storeName = (TextView) findViewById(R.id.storeName);
        storeProducts = (TextView) findViewById(R.id.productsNumber);
        storeAddress = (TextView) findViewById(R.id.shopAddress);
        storeImage = (ImageView) findViewById(R.id.shopImage);
        showAllProducts = (Button) findViewById(R.id.showAllProducts);
    }

    private void updateUI() {
        storeName.setText(store.getStoreName());
        storeProducts.setText(store.getStoreProducts().toString());
        storeAddress.setText(store.getStoreStreet() + ", " + store.getStoreCity() + ", " + store.getStoreState());
        storeImage.setImageResource(store.getStoreImage());
        showAllProducts.setVisibility(View.INVISIBLE);
    }
}