package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Staff implements Parcelable {
    private Integer staffId;
    private String staffFirstName;
    private String staffLastName;
    private String staffPhone;
    private String staffEmail;
    private Store store;
    private Staff staff;

    public Staff(Integer staffId, String staffFirstName, String staffLastName, String staffPhone, String staffEmail, Store store, Staff staff) {
        this.staffId = staffId;
        this.staffFirstName = staffFirstName;
        this.staffLastName = staffLastName;
        this.staffPhone = staffPhone;
        this.staffEmail = staffEmail;
        this.store = store;
        this.staff = staff;
    }

    protected Staff(Parcel in) {
        if (in.readByte() == 0) {
            staffId = null;
        } else {
            staffId = in.readInt();
        }
        staffFirstName = in.readString();
        staffLastName = in.readString();
        staffPhone = in.readString();
        staffEmail = in.readString();
        store = in.readParcelable(Store.class.getClassLoader());
        staff = in.readParcelable(Staff.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (staffId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(staffId);
        }
        dest.writeString(staffFirstName);
        dest.writeString(staffLastName);
        dest.writeString(staffPhone);
        dest.writeString(staffEmail);
        dest.writeParcelable(store, flags);
        dest.writeParcelable(staff, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Staff> CREATOR = new Creator<Staff>() {
        @Override
        public Staff createFromParcel(Parcel in) {
            return new Staff(in);
        }

        @Override
        public Staff[] newArray(int size) {
            return new Staff[size];
        }
    };

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
