package org.vgecalumni.Model;

public class EventDetails {

    private String title, description;

    public EventDetails(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
