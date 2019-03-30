package org.vgecalumni;

import java.util.ArrayList;

public class EventItemPovider {

    private String image;
    private String name;

    public EventItemPovider(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public EventItemPovider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<EventItemPovider> getData() {

        String name[] = {
                "Event 1",
                "Event 2",
                "Event 3",
                "Event 4",
                "Event 5"
        };

        ArrayList<EventItemPovider> itemList = new ArrayList<>();

        for (String aName : name) {
            itemList.add(new EventItemPovider(aName));
        }

        return itemList;
    }
}
