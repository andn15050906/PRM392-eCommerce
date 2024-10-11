package com.example.myapplication.order;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.OrderItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailListAdapter extends RecyclerView.Adapter<OrderDetailListAdapter.OrderDetailListViewHolder> {
    Context context;
    List<OrderItem> orderItems;

    private String amount = "Total amount: ";
    private String price = "Total price: $";

    public OrderDetailListAdapter(Context context, List<OrderItem> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public OrderDetailListAdapter.OrderDetailListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderDetailListViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailListAdapter.OrderDetailListViewHolder holder, int position) {
        OrderItem item = orderItems.get(position);
        String separator = "_";

        holder.storeName.setText(item.getStore().getStoreName());
        holder.itemName.setText(item.getProductName());
        holder.amount.setText("x" + item.getAmount().toString());
        holder.price.setText("$" + item.getPrice().toString());
        holder.totalAmount.setText(amount + item.getAmount().toString());
        Float total = item.getPrice() * item.getAmount();
        holder.totalPrice.setText(price + total.toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.image.setImageResource(Arrays.stream(item.getImages().split(separator))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()).get(0));
        }
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class OrderDetailListViewHolder extends RecyclerView.ViewHolder{
        TextView storeName, itemName, amount, price, totalAmount, totalPrice;
        ImageView image;
        public OrderDetailListViewHolder(@NonNull View itemView) {
            super(itemView);

            storeName = itemView.findViewById(R.id.orderItemStoreName);
            itemName = itemView.findViewById(R.id.orderItemName);
            amount = itemView.findViewById(R.id.orderItemAmount);
            price = itemView.findViewById(R.id.orderItemPrice);
            totalAmount = itemView.findViewById(R.id.orderItemAmount2);
            totalPrice = itemView.findViewById(R.id.orderItemTotalPrice);
            image = itemView.findViewById(R.id.orderItemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = itemName.getText() + " was clicked";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
