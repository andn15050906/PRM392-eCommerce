package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.Cart;

import java.util.ArrayList;

public class ViewCart extends AppCompatActivity {
    RecyclerView recyclerView ;
    TextView totalItemFee, totalDeliveryFee, totalTaxFee, totalFee, checkout;

    Cart cart;
    CartAdapter cartAdapter;

    private final Float deliveryFee = 5f;
    private final Float taxPercent = 0.1f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        setupUI();
        setupOnclick();
        setupComponent();
        setupListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void setupOnclick() {
        checkout.setOnClickListener(view -> {
            cart.setList(new ArrayList<>());
            cart.updateDetails();
            ((GlobalVariables) getApplication()).setCart(cart);
            updateView();
            Toast.makeText(this, "Checkout successfully", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupUI() {
        recyclerView = (RecyclerView) findViewById(R.id.rcv_product);
        totalItemFee = (TextView) findViewById(R.id.totalItemFee);
        totalDeliveryFee = (TextView) findViewById(R.id.totalDeliveryFee);
        totalTaxFee = (TextView) findViewById(R.id.totalTaxFee);
        totalFee = (TextView) findViewById(R.id.totalFee);
        checkout = (TextView) findViewById(R.id.checkout);
    }

    private void setupListView() {
        cartAdapter = new CartAdapter(this, cart.getList(), cart) {
            @Override
            public void updateCart() {
                ((GlobalVariables) getApplication()).setCart(cart);
                updateView();
            }
        };
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupComponent() {
        cart = ((GlobalVariables) this.getApplication()).getCart();
        cart.updateDetails();
    }

    private void updateView() {
        setupComponent();
        cartAdapter.updateList(cart.getList());
        cartAdapter.notifyDataSetChanged();

        Float taxFee = cart.getTotalPrice() * taxPercent;
        totalItemFee.setText(cart.getTotalPrice().toString() + "$");
        totalDeliveryFee.setText(deliveryFee.toString() + "$");
        totalTaxFee.setText(taxFee.toString() + "$");
        totalFee.setText(String.valueOf(cart.getTotalPrice() + taxFee));
    }
}