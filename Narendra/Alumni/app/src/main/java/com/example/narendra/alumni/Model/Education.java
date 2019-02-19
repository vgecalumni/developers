package com.example.narendra.alumni.Model;

public class Education{
    String degree,stream,inst,join,end,tag;

    public Education(String degree, String stream, String inst, String join, String end, String tag) {
        this.degree = degree;
        this.stream = stream;
        this.inst = inst;
        this.join = join;
        this.end = end;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public String getDegree() {
        return degree;
    }

    public String getStream() {
        return stream;
    }

    public String getInst() {
        return inst;
    }

    public String getJoin() {
        return join;
    }

    public String getEnd() {
        return end;
    }
}
