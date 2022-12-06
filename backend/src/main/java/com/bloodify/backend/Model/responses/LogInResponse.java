package com.bloodify.backend.model.responses;

public class LogInResponse {
    private boolean status;
    private String message;
    private LoginResponseBody data;
    

    public LogInResponse(boolean status, String message, LoginResponseBody data) {
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
    public LoginResponseBody getData() {
        return data;
    }
    public void setData(LoginResponseBody data) {
        this.data = data;
    }

    
    
}
