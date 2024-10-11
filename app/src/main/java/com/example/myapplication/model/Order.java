package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;

public class Order implements Parcelable {
    private Integer orderId;
    private Customer customer;
    private Staff staff;
    private String orderStatus;
    private LocalDateTime orderDate;
    private LocalDateTime payDate;
    private LocalDateTime shipmentDate;
    private Float totalPrice;
    private Integer totalAmount;

    public Order(Integer orderId, Customer customer, Staff staff, String orderStatus, LocalDateTime orderDate, LocalDateTime payDate, LocalDateTime shipmentDate, Float totalPrice, Integer totalAmount) {
        this.orderId = orderId;
        this.customer = customer;
        this.staff = staff;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.payDate = payDate;
        this.shipmentDate = shipmentDate;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
    }

    protected Order(Parcel in) {
        if (in.readByte() == 0) {
            orderId = null;
        } else {
            orderId = in.readInt();
        }
        customer = in.readParcelable(Customer.class.getClassLoader());
        staff = in.readParcelable(Staff.class.getClassLoader());
        orderStatus = in.readString();
        if (in.readByte() == 0) {
            totalPrice = null;
        } else {
            totalPrice = in.readFloat();
        }
        if (in.readByte() == 0) {
            totalAmount = null;
        } else {
            totalAmount = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (orderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(orderId);
        }
        dest.writeParcelable(customer, flags);
        dest.writeParcelable(staff, flags);
        dest.writeString(orderStatus);
        if (totalPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(totalPrice);
        }
        if (totalAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalAmount);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDateTime payDate) {
        this.payDate = payDate;
    }

    public LocalDateTime getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(LocalDateTime shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}
