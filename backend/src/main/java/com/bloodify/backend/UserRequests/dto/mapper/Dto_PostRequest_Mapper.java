package com.bloodify.backend.UserRequests.dto.mapper;

import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import org.springframework.stereotype.Service;

@Service
public class Dto_PostRequest_Mapper {

    public PostDto mapToPostDto(PostRequest postRequest){
        BloodType type = BloodTypeFactory.getFactory().generateFromString(postRequest.getBloodType());
        return new PostDto(postRequest.getUserEmail(), postRequest.getInstitutionID(),
                postRequest.getRequiredBags(), type);
    }
}
