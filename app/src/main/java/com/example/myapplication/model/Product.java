package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Product implements Parcelable {
    @SerializedName("id")
    private Integer productId;
    @SerializedName("name")
    private String productName;
    @SerializedName("price")
    private Float price;
    private String images;
    @SerializedName("modelyear")
    private LocalDateTime modelYear;
    @SerializedName("description")
    private String description;
    @SerializedName("stock")
    private Integer stock;
    private Brand brand;
    private Category category;
    private Store store;

    public Product(Integer productId, String productName, Float price, String images, LocalDateTime modelYear, String description, Integer stock, Brand brand, Category category, Store store) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.images = images;
        this.modelYear = modelYear;
        this.description = description;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.store = store;
    }

    public Product(String images, String productName, Float price) {
        this.productName = productName;
        this.price = price;
        this.images = images;
    }

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            productId = null;
        } else {
            productId = in.readInt();
        }
        productName = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readFloat();
        }
        images = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            stock = null;
        } else {
            stock = in.readInt();
        }
        brand = in.readParcelable(Brand.class.getClassLoader());
        category = in.readParcelable(Category.class.getClassLoader());
        store = in.readParcelable(Store.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public LocalDateTime getModelYear() {
        return modelYear;
    }

    public void setModelYear(LocalDateTime modelYear) {
        this.modelYear = modelYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (productId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(productId);
        }
        parcel.writeString(productName);
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(price);
        }
        parcel.writeString(images);
        parcel.writeString(description);
        if (stock == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(stock);
        }
        parcel.writeParcelable(brand, i);
        parcel.writeParcelable(category, i);
        parcel.writeParcelable(store, i);
    }
}
