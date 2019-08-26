package org.vgecalumni.Model;

import java.util.List;

public class EduResponse {
    private boolean error;
    private String message;
    private List<Education> data=null;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<Education> getEducation() {
        return data;
    }

    public EduResponse(boolean error, String message, List<Education> data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }
}
