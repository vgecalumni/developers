package org.vgecalumni.Model;

public class Portfolio {

    String event_image_url, event_name, drive_link;

    public Portfolio(String event_image_url, String event_name, String drive_link) {
        this.event_image_url = event_image_url;
        this.event_name = event_name;
        this.drive_link = drive_link;
    }

    public String getEvent_image_url() {
        return event_image_url;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getDrive_link() {
        return drive_link;
    }
}
