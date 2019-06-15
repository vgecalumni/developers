package org.vgecalumni.Model;

public class UserResponse {
    private boolean error;
    private String message;
    private User data;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return data;
    }

    public UserResponse(boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.data = user;
    }
}
