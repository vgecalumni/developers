package com.example.narendra.alumni.Model;

public class User {
    String id, fname, mname,lname, enroll, branch, gender, dob;
    String mob, address, pincode, city, district, state;

    public User(String id,String fname, String mname, String lname, String enroll, String branch, String gender, String dob, String mob, String address, String city, String pincode, String district, String state) {
        this.id=id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.enroll = enroll;
        this.branch = branch;
        this.gender = gender;
        this.dob = dob;
        this.mob = mob;
        this.address = address;
        this.pincode = pincode;
        this.city = city;
        this.district=district;
        this.state = state;
    }

    public String getMob() {
        return mob;
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

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getEnroll() {
        return enroll;
    }

    public String getDistrict() {
        return district;
    }

    public String getBranch() {
        return branch;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
