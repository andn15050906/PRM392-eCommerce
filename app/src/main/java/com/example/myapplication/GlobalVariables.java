package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.model.Brand;
import com.example.myapplication.model.CartProduct;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderItem;
import com.example.myapplication.model.Store;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariables extends Application {
    private List<Product> products;
    private List<Brand> brands;
    private List<Category> categories;
    private List<Order> orders;
    private List<OrderItem> orderItems;
    private List<Store> stores;
    private Cart cart;
    private Customer customer;

    public GlobalVariables() {
        products = new ArrayList<>();
        brands = new ArrayList<>();
        categories = new ArrayList<>();
        orders = new ArrayList<>();
        orderItems = new ArrayList<>();
        stores = new ArrayList<>();
        cart = new Cart();
        customer = null;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setupData() {
        setupBrands();
        setupCategories();
        setupStore();
    }

    private void setupBrands() {
        brands.add(new Brand(1, "Samsung"));
        brands.add(new Brand(2, "Xiaomi"));
    }

    private void setupCategories() {
        categories.add(new Category(1, "Electronic"));
        categories.add(new Category(2, "Furniture"));
        categories.add(new Category(3, "Clothes"));
        categories.add(new Category(4, "International"));
    }

    private void setupStore() {
        stores.add(new Store(1, "Samsung Official Store VN",
                "0808987654", "samsung@store.vn",
                "1 Pham Van Dong", "Tan Binh", "Ho Chi Minh", "123456", R.drawable.samsung, 1000));
        stores.add(new Store(2, "Xiaomi Off.",
                "0902020202", "xiaomi@store.vn",
                "2 Pham Van Dong", "Tan Binh", "Ho Chi Minh", "123456", R.drawable.xiaomi, 2000));
    }
}
