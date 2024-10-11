package com.example.myapplication.order;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.cart.CheckoutProductListAdapter;
import com.example.myapplication.model.CartProduct;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.Product;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {
    Context context;
    List<Order> orders;
    private String orderId = "Order ID: ";
    private String shipmentDate = "Shipment date: ";
    private String status = "Status: ";
    private String amount = "Amount: ";
    private String price = "Price: $";

    public OrderListAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderListAdapter.OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderListViewHolder(LayoutInflater.from(context).inflate(R.layout.order_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.orderId.setText(orderId + order.getOrderId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.shipmentDate.setText(shipmentDate + order.getShipmentDate().toLocalDate());
        }
        holder.status.setText(status + order.getOrderStatus());
        holder.amount.setText(amount + order.getTotalAmount().toString());
        holder.price.setText(price + order.getTotalPrice().toString());
    }

    @Override
    public int getItemCount() {
        return orders != null ? orders.size() : 0;
    }

    public class OrderListViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, shipmentDate, status, amount, price;

        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.orderListOrderId);
            shipmentDate = itemView.findViewById(R.id.orderListShipmentDate);
            status = itemView.findViewById(R.id.orderListOrderStatus);
            amount = itemView.findViewById(R.id.orderListTotalAmount);
            price = itemView.findViewById(R.id.orderListTotalPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = orders.get(getAdapterPosition()).getOrderId().toString();
                    Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
