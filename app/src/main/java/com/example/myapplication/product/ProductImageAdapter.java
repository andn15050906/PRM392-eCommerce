package com.example.myapplication.product;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductImageAdapter extends RecyclerView.Adapter<ProductImageAdapter.ViewHolder> {
    Context context;
    List<Integer> listImages;
    String separator = "_";

    public ProductImageAdapter(Context context, String image) {
        this.context = context;
        convertStringImageToList(image);
    }

    private void convertStringImageToList(String image) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            listImages = Arrays.stream(image.split(separator))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_images_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(listImages.get(position));
    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productImage);
        }
    }
}
