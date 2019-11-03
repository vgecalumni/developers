package org.vgecalumni.Model;

public class RegEventResponse {
    private boolean error;
    private String message;
    private RegEvents data;

    public RegEventResponse(boolean error, String message, RegEvents re) {
        this.error = error;
        this.message = message;
        this.data = re;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public RegEvents getRegEvents() {
        return data;
    }
}
