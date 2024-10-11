package com.example.myapplication.home;

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

public class SearchProductViewAdapter extends RecyclerView.Adapter<SearchProductViewAdapter.SearchProductViewHolder> {

    String separator = "_";

    private List<Product> searchProductList;
    public SearchProductViewAdapter(List<Product> searchProductList) {this.searchProductList = searchProductList;}

    @NonNull
    @Override
    public SearchProductViewAdapter.SearchProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new SearchProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchProductViewHolder holder, int position) {
        Product product = searchProductList.get(position);
        if (product == null){
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.img.setImageResource(Arrays.stream(product.getImages().split(separator))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()).get(0));
            holder.productName.setText(product.getProductName());
            holder.productPrice.setText(product.getPrice().toString());
        }

    }

    @Override
    public int getItemCount() {
        if (searchProductList != null){
            return searchProductList.size();
        }
        return 0;
    }

    public class SearchProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView productName;
        private TextView productPrice;

        public SearchProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.itemImage);
            productName = itemView.findViewById(R.id.itemName);
            productPrice = itemView.findViewById(R.id.itemPrice);


        }
    }
}
