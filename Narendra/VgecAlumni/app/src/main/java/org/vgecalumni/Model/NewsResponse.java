package org.vgecalumni.Model;

import java.util.List;

public class NewsResponse {
    private boolean error;
    private String message;
    private List<News> data;

    public NewsResponse(boolean error, String message, List<News> data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<News> getNews() {
        return data;
    }
}
