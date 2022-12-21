package com.bloodify.backend.UserRequests.controller.request.entity;

import lombok.Data;

@Data
public class PostResponse<T> {
    private boolean status;
    private T responseBody;

    public PostResponse(boolean status) {
        this.status = status;
    }

    public PostResponse(boolean status, T responseBody) {
        this.status = status;
        this.responseBody = responseBody;
    }
}
