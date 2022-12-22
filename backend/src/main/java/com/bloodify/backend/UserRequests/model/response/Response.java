package com.bloodify.backend.UserRequests.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    boolean state;
    String message;
    Object responseBody;
}
