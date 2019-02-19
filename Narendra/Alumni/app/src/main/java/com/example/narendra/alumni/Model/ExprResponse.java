package com.example.narendra.alumni.Model;

import java.util.List;

public class ExprResponse {
    private boolean error;
    private String message;
    private List<Experience> data;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<Experience> getExperience() {
        return data;
    }

    public ExprResponse(boolean error, String message, List<Experience> dat) {

        this.error = error;
        this.message = message;
        this.data = data;
    }
}
