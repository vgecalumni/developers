package org.vgecalumni.Model;

public class Experience {
    String company, desig, desc, join, end, tag;

    public String getCompany() {
        return company;
    }

    public String getDesig() {
        return desig;
    }

    public String getDesc() {
        return desc;
    }

    public String getJoin() {
        return join;
    }

    public String getEnd() {
        return end;
    }

    public String getTag() {
        return tag;
    }

    public Experience(String company, String desig, String desc, String join, String end, String tag) {
        this.company = company;
        this.desig = desig;
        this.desc = desc;
        this.join = join;
        this.end = end;
        this.tag = tag;
    }
}
