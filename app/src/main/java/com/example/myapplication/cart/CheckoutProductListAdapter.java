package com.example.myapplication.cart;

import static java.io.File.separator;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Product;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutProductListAdapter extends RecyclerView.Adapter<CheckoutProductListAdapter.CheckoutViewHolder> {
    Context context;
    List<Product> products;

    public CheckoutProductListAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckoutViewHolder(LayoutInflater.from(context).inflate(R.layout.checkout_product_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {
        Product product = products.get(position);
        String separator = "_";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.imageView.setImageResource(Arrays.stream(product.getImages().split(separator))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()).get(0));
        }
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText("$"+product.getPrice());
        holder.productAmount.setText("x" + product.getStock());
        holder.productTotalText.setText("Total amount (" + product.getStock() + " items):");
        holder.productTotalPrice.setText("$" + product.getStock() * product.getPrice());
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView productName, productPrice, productAmount, productTotalText, productTotalPrice;

        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.checkoutProductImage);
            productName = itemView.findViewById(R.id.checkoutProductName);
            productPrice = itemView.findViewById(R.id.checkoutProductPrice);
            productAmount = itemView.findViewById(R.id.checkoutProductAmount);
            productTotalText = itemView.findViewById(R.id.checkoutProductTotalText);
            productTotalPrice = itemView.findViewById(R.id.checkoutProductTotalPrice);
        }
    }
}
