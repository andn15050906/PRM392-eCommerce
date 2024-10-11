package com.example.myapplication.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityOrderBinding;
import com.example.myapplication.model.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OrderActivity extends AppCompatActivity {
    RecyclerView orderListView;
    ImageView back;
    TextView emptyOrderHistoryNotice, topnav;
    List<Order> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setupUI();
        setupOnClick();
    }

    private void setupUI() {
        back = (ImageView) findViewById(R.id.imageView3);
        orderListView = findViewById(R.id.orderListView);
        emptyOrderHistoryNotice = (TextView) findViewById(R.id.textViewEmptyOrderList);
        topnav = (TextView) findViewById(R.id.titleTopNav);

        list = mockOrderList();
//        list = new ArrayList<>();
        OrderListAdapter adapter = new OrderListAdapter(OrderActivity.this, list);
        orderListView.setAdapter(adapter);
        orderListView.setLayoutManager(new LinearLayoutManager(this));
        checkEmptyOrderHistory(list);

        topnav.setText("Order History");
    }

    private void setupOnClick() {

    }

    private void checkEmptyOrderHistory(List<Order> list) {
        if (list.isEmpty()) {
            orderListView.setVisibility(View.GONE);
            emptyOrderHistoryNotice.setVisibility(View.VISIBLE);
        }
        else {
            orderListView.setVisibility(View.VISIBLE);
            emptyOrderHistoryNotice.setVisibility(View.GONE);
        }
    }

    private List<Order> mockOrderList() {
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(mockOrder());
        }
        return list;
    }

    private Order mockOrder() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Order(ThreadLocalRandom.current().nextInt(0, 100),
                    null,
                    null,
                    "Arrived",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    100f,
                    10);
        }
        return null;
    }
}