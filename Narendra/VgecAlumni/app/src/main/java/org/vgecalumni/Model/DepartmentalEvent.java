package org.vgecalumni.Model;

import java.util.ArrayList;
import java.util.List;

public class DepartmentalEvent {

    private String name;
    private List<String> eventIDList;
    private List<String> eventNameList;
    private List<String> eventImageUrlList;

    public DepartmentalEvent(String name) {
        this.name = name;
        eventIDList = new ArrayList<>();
        eventNameList = new ArrayList<>();
        eventImageUrlList = new ArrayList<>();
    }

    public void addEvent(String id, String name, String imageURL) {
        eventIDList.add(id);
        eventNameList.add(name);
        eventImageUrlList.add(imageURL);
    }

    public String getName() {
        return name;
    }

    public List<String> getEventIDList() {
        return eventIDList;
    }

    public List<String> getEventNameList() {
        return eventNameList;
    }

    public List<String> getEventImageUrlList() {
        return eventImageUrlList;
    }
}
