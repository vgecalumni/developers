package com.example.narendra.alumni.Model;

public class SharedUser {
    String id ,fname, mname,lname;
    String address, district, pincode, city, state;

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public String getPincode() {
        return pincode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getId() {
        return id;
    }

    public SharedUser(String id, String fname, String mname, String lname, String address, String district, String pincode, String city, String state) {
        this.id=id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.address = address;
        this.district = district;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
    }
}
