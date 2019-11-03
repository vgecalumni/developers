package org.vgecalumni.Model;

public class CentralEvent {

    private String id, name, imageURL, description, date;

    public CentralEvent(String id, String name, String imageURL, String description, String date) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
