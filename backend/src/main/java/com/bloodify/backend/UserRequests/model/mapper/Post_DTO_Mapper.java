package com.bloodify.backend.UserRequests.model.mapper;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.model.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Post_DTO_Mapper {
    @Autowired
    UserDAO userDAO;
    @Autowired
    InstitutionDAO institutionDAO;

    public Post map_to_Post(PostDto dto){
        return new Post(userDAO.findUserByEmail(dto.getUserEmail()), institutionDAO.findInstitutionByID(dto.getInstitutionID()),
                dto.getRequiredBags(), LocalDateTime.now(), dto.getBloodType().toString());
    }
}
