package com.bloodify.backend.model.responses;

public class UserLogInResponse {
    private boolean status;
    private String message;
    private UserLoginResponseBody data;
    

    public UserLogInResponse(boolean status, String message, UserLoginResponseBody data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public UserLoginResponseBody getData() {
        return data;
    }
    public void setData(UserLoginResponseBody data) {
        this.data = data;
    }

    
    
}
