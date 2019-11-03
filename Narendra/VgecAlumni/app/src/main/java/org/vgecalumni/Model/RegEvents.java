package org.vgecalumni.Model;

public class RegEvents {
    String event, photo, food;

    public RegEvents(String event, String photo, String food) {
        this.event = event;
        this.photo = photo;
        this.food = food;
    }

    public String getEvent() {
        return event;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFood() {
        return food;
    }
}
