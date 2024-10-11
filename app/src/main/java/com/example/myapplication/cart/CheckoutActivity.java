package com.example.myapplication.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Brand;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    RecyclerView checkoutProductList;
    TextView topnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        setupUI();
    }

    private void setupUI() {
        checkoutProductList = (RecyclerView) findViewById(R.id.checkoutProductList);
        topnav = (TextView) findViewById(R.id.titleTopNav);

        topnav.setText("Checkout");
        CheckoutProductListAdapter adapter = new CheckoutProductListAdapter(this, mockProducts());
        checkoutProductList.setAdapter(adapter);
        checkoutProductList.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Product> mockProducts() {
        String split = "_";
        String images = "" + R.drawable.image1 + split + R.drawable.image2 + split + R.drawable.image3 + split + R.drawable.image4 + split + R.drawable.image5;
        Product product;
        List<Product> list = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            product = new Product(1,
                    "Product 1",
                    15f,
                    images,
                    LocalDateTime.now(),
                    "This is the product\n" +
                            "you have always wanted",
                    100,
                    new Brand(1, "Gucci"),
                    new Category(1, "Meme"),
                    new Store(1, "Samsung Official Store VN",
                            "0808987654", "samsung@store.vn",
                            "1 Pham Van Dong", "Tan Binh", "Ho Chi Minh", "123456", R.drawable.samsung, 1000));
            for (int i = 0; i < 10; i++) {
                list.add(product);
            }
        }
        return list;
    }
}