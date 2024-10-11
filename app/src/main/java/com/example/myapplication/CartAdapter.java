package com.example.myapplication;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Cart;
import com.example.myapplication.model.CartProduct;
import com.example.myapplication.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder>{
    private List<CartProduct> productList;
    private final String separator = "_";
    private Context mCtx;
    Cart cart;

    public CartAdapter(Context mCtx,List<CartProduct> productList, Cart cart) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.cart = cart;
    }

    public void updateList(List<CartProduct> list) {
        productList = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_cart, null);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        CartProduct product = productList.get(position);
        if (product == null){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.img.setImageResource(product.getImage());
        }
        holder.productName.setText(product.getCartProductName());
        holder.numberItem.setText(product.getAmount().toString());
        holder.productPrice.setText(product.getPrice().toString());
        holder.plus.setOnClickListener(view -> {
            product.setAmount(product.getAmount() + 1);
//            cart.setList(productList);
            updateCart();
            cart.updateDetails();
            holder.numberItem.setText(product.getAmount().toString());
        });

        holder.minus.setOnClickListener(view -> {
            product.setAmount(product.getAmount() - 1);
            if (product.getAmount() <= 0) {
                productList.remove(product);
                CartAdapter.this.notifyDataSetChanged();
            }
//            cart.setList(productList);
            updateCart();
            cart.updateDetails();
            holder.numberItem.setText(product.getAmount().toString());
        });
    }

    @Override
    public int getItemCount() {
        if (productList != null){
            return productList.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView img, plus, minus;
        private TextView productName, productPrice, numberItem;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            productName = itemView.findViewById(R.id.textViewName);
            productPrice = itemView.findViewById(R.id.price);
            numberItem = itemView.findViewById(R.id.numberItem);
            plus = itemView.findViewById(R.id.plusCartBtn);
            minus = itemView.findViewById(R.id.minusCartBtn);
        }
    }

    public abstract void updateCart();
}
