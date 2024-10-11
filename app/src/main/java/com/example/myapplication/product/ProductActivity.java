package com.example.myapplication.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.GlobalVariables;
import com.example.myapplication.R;
import com.example.myapplication.ShopProfileActivity;
import com.example.myapplication.ViewCart;
import com.example.myapplication.model.Brand;
import com.example.myapplication.model.CartProduct;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductActivity extends AppCompatActivity {
    List<Integer> listImages;
    Product product;

    ViewPager2 productImageContainer;
    ProductImageAdapter productImageAdapter;

    TextView productName, productPrice, productModelYear, stock, brand, category, description, storeName, storeProducts, storeState;
    ImageView storeImage, back, cart;
    Button addToCart;

    View store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupComponent();
        if (product != null) {
            setupUI();
            setupOnclick();
            setupProductImageContainer();
            updateUI();
        }
    }

    private void setupComponent() {
        if (listImages == null) {
            listImages = new ArrayList<>();
        }
        product = getProductFromIntent();
    }

    private void setupUI() {
        productImageContainer = (ViewPager2) findViewById(R.id.productImageContainer);
        productImageAdapter = new ProductImageAdapter(ProductActivity.this, product.getImages());
        productImageContainer.setAdapter(productImageAdapter);

        productName = (TextView) findViewById(R.id.detailProductName);
        productPrice = (TextView) findViewById(R.id.detailProductPrice);
        productModelYear = (TextView) findViewById(R.id.detailProductModelYear);
        stock = (TextView) findViewById(R.id.detailProductStock);
        brand = (TextView) findViewById(R.id.detailProductBrand);
        category = (TextView) findViewById(R.id.detailProductCategory);
        description = (TextView) findViewById(R.id.detailProductDescription);
        storeName = (TextView) findViewById(R.id.detailProductStoreName);
        storeProducts = (TextView) findViewById(R.id.detailProductStoreProducts);
        storeState = (TextView) findViewById(R.id.detailProductStoreState);

        storeImage = (ImageView) findViewById(R.id.detailProductStoreImage);
        back = (ImageView) findViewById(R.id.detailProductBackIcon);
        cart = (ImageView) findViewById(R.id.detailProductCartIcon);

        addToCart = (Button) findViewById(R.id.productAddToCart);

        store = findViewById(R.id.detailProductStore);
    }

    private void setupOnclick() {
        back.setOnClickListener(view -> finish());
        cart.setOnClickListener(view -> {
            Intent intent = new Intent(this, ViewCart.class);
            startActivity(intent);
        });
        store.setOnClickListener(view -> {
            Intent intent = new Intent(this, ShopProfileActivity.class);
            intent.putExtra("storeId", product.getStore().getStoreId());
            startActivity(intent);
        });
        addToCart.setOnClickListener(view -> {
            Integer image=0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                image = Arrays.stream(product.getImages().split("_"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()).get(0);
            }
            CartProduct cartProduct = new CartProduct(product.getProductId(),
                    product.getProductName(), product.getPrice(), 1, product.getStore(), image);
            ((GlobalVariables) this.getApplication()).getCart().addToCart(cartProduct);
            Toast.makeText(this, "Add to cart successfully", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupProductImageContainer() {
        productImageContainer.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void updateUI() {
        productName.setText(product.getProductName());
        productPrice.setText(product.getPrice().toString() + " USD");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            productModelYear.setText(String.valueOf(product.getModelYear().getYear()));
        }
        stock.setText(product.getStock().toString());
        brand.setText(product.getBrand().getBrandName());
        category.setText(product.getCategory().getCategoryName());
        description.setText(product.getDescription());
        storeName.setText(product.getStore().getStoreName());
        storeProducts.setText(product.getStore().getStoreProducts().toString() + " products");
        storeState.setText(product.getStore().getStoreState());
        storeImage.setImageResource(product.getStore().getStoreImage());
    }

    private Product getProductFromIntent() {
        Intent intent = getIntent();
        int productId = intent.getIntExtra("productId", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            product = ((GlobalVariables) this.getApplication()).getProducts().stream().filter(p -> p.getProductId() == productId).findAny().orElse(product = null);
        }
        return product;
    }
}