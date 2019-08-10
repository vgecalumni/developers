package org.vgecalumni.Model;

public class News {
    String date, desc, link;
    int category;

    public News(String date, String desc, String link, int category) {
        this.date = date;
        this.desc = desc;
        this.link = link;
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public int getCategory() {
        return category;
    }
}
