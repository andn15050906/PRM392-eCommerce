package com.example.myapplication.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderItem;
import com.example.myapplication.model.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailActivity extends AppCompatActivity {
    TextView titleTopNav, orderId, shipmentDate, totalAmount, totalPrice;
    RecyclerView orderItemListView;
    Order order;
    List<OrderItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        setupUI();
        updateUI();
    }

    private void setupUI() {
        titleTopNav = (TextView) findViewById(R.id.titleTopNav);
        orderId = (TextView) findViewById(R.id.orderDetailOrderId);
        shipmentDate = (TextView) findViewById(R.id.orderDetailShipmentDate);
        totalAmount = (TextView) findViewById(R.id.orderDetailTotalAmount);
        totalPrice = (TextView) findViewById(R.id.orderDetailTotalPrice);
        orderItemListView = (RecyclerView) findViewById(R.id.orderDetailItemList);

        setupComponent();

    }

    private void updateUI() {
        titleTopNav.setText("Order History");
        orderId.setText("Order ID: " + order.getOrderId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            shipmentDate.setText(order.getShipmentDate().toLocalDate().toString());
        }
        totalAmount.setText("Total amount: " + order.getTotalAmount());
        totalPrice.setText("Total price: $" + order.getTotalPrice().toString());
    }

    private void setupComponent() {
        order = mockOrder();
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int orderid = i%2 == 0 ? 1 : 2;
            String name = i%3 == 0 ? "Nước tẩy trang làm sạch - mềm mịn da L'Oreal Paris 3in1 Micellar Water 400ml" : "Bàn Phím CƠ ZIYOU K3 Luxury Cao Cấp, Phím Gõ Êm Sử Dụng Trục Cơ Red Switch, Led 16,5 Triệu Màu 10 Chế Độ, Bảo Hành 12t";
            String images = i%3 == 0 ? "" + R.drawable.image1 : "" + R.drawable.image2 + "_" + R.drawable.image3;
            String store = "Store " + i;
            list.add(new OrderItem(i+1,
                    orderid,
                    i+1,
                    name,
                    images,
                    10f,
                    10,
                    new Store(1, store, "Phone", "Email", "Street", "City", "State", "123456", R.drawable.samsung, 1000)));
        }
        filterOrderItem();
        OrderDetailListAdapter adapter = new OrderDetailListAdapter(OrderDetailActivity.this, list);
        orderItemListView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        orderItemListView.setAdapter(adapter);
    }

    private void filterOrderItem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list = list.stream().filter(p -> p.getOrderId() == order.getOrderId()).collect(Collectors.toList());
        }
    }

    private Order mockOrder() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Order(1,
                    null,
                    null,
                    "Arrived",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    1000f,
                    100);
        }
        return null;
    }
}