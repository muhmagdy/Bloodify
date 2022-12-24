package com.bloodify.backend.UserRequests.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AcceptPostRequest {
    Integer id;
    Double longitude;
    Double latitude;
    Double threshold;
}
