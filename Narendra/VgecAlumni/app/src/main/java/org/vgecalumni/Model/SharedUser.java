package org.vgecalumni.Model;

public class SharedUser {
    String id, fname, mname, lname;
    String address, district, pincode, city, state;
    String pic, intro, gen, dob;

    public SharedUser(String id, String fname, String mname, String lname, String address,
                      String district, String pincode, String city, String state, String pic, String intro, String gen, String dob) {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.address = address;
        this.district = district;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.pic = pic;
        this.intro = intro;
        this.gen = gen;
        this.dob = dob;
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

    public String getIntro() {
        return intro;
    }

    public String getGen() {
        return gen;
    }

    public String getPic() {
        return pic;
    }

    public String getDob() {
        return dob;
    }
}
