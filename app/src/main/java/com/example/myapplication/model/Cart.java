package com.example.myapplication.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart implements Parcelable {
    private List<CartProduct> list;
    private Float totalPrice;
    private Integer totalProducts;

    public Cart() {
        list = new ArrayList<>();
        totalPrice = 0f;
        totalProducts = 0;
    }

    protected Cart(Parcel in) {
        list = in.createTypedArrayList(CartProduct.CREATOR);
        if (in.readByte() == 0) {
            totalPrice = null;
        } else {
            totalPrice = in.readFloat();
        }
        if (in.readByte() == 0) {
            totalProducts = null;
        } else {
            totalProducts = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(list);
        if (totalPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(totalPrice);
        }
        if (totalProducts == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalProducts);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public List<CartProduct> getList() {
        return list;
    }

    public void setList(List<CartProduct> list) {
        this.list = list;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Integer totalProducts) {
        this.totalProducts = totalProducts;
    }

    public void updateDetails() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            totalProducts = list.stream().collect(Collectors.summingInt(CartProduct::getAmount));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            totalPrice = list.stream().map(p -> p.totalPrice()).reduce(0f, Float::sum);
        }
    }

    public void addToCart(CartProduct product) {
        CartProduct productInCart = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            productInCart = list.stream().filter(p -> p.getCartProductId() == product.getCartProductId()).findAny().orElse(productInCart=null);
        }
        if (productInCart == null) {
            list.add(product);
            return;
        }
        productInCart.setAmount(productInCart.getAmount() + 1);
        updateDetails();
    }
}
