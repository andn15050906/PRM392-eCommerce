package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable {
    private Integer customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerEmail;
    private String customerStreet;
    private String customerCity;
    private String customerZipCode;

    public Customer(Integer customerId, String customerFirstName, String customerLastName, String customerPhone, String customerEmail, String customerStreet, String customerCity, String customerZipCode) {
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerStreet = customerStreet;
        this.customerCity = customerCity;
        this.customerZipCode = customerZipCode;
    }

    public Customer() {

    }

    protected Customer(Parcel in) {
        if (in.readByte() == 0) {
            customerId = null;
        } else {
            customerId = in.readInt();
        }
        customerFirstName = in.readString();
        customerLastName = in.readString();
        customerPhone = in.readString();
        customerEmail = in.readString();
        customerStreet = in.readString();
        customerCity = in.readString();
        customerZipCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (customerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(customerId);
        }
        dest.writeString(customerFirstName);
        dest.writeString(customerLastName);
        dest.writeString(customerPhone);
        dest.writeString(customerEmail);
        dest.writeString(customerStreet);
        dest.writeString(customerCity);
        dest.writeString(customerZipCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerStreet() {
        return customerStreet;
    }

    public void setCustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerZipCode() {
        return customerZipCode;
    }

    public void setCustomerZipCode(String customerZipCode) {
        this.customerZipCode = customerZipCode;
    }
}
