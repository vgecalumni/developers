package org.vgecalumni.Model;

public class User {
    String id, fname, mname, lname, enroll, branch, gender, dob;
    String email, mob, address, pincode, city, district, state;
    String pic, intro;

    public User(String id, String fname, String mname, String lname, String enroll, String branch,
                String gender, String dob, String email, String mob, String address, String city,
                String pincode, String district, String state, String pic, String intro) {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.enroll = enroll;
        this.branch = branch;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.mob = mob;
        this.address = address;
        this.pincode = pincode;
        this.city = city;
        this.district = district;
        this.state = state;
        this.pic = pic;
        this.intro = intro;
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

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPic() {
        return pic;
    }

    public String getIntro() {
        return intro;
    }
}
