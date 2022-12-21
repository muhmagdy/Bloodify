package com.bloodify.backend.UserRequests.model.mapper;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.exceptions.InstitutionNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
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
        User user = userDAO.findUserByEmail(dto.getUserEmail());
        Institution institution = institutionDAO.findInstitutionByID(dto.getInstitutionID());
        if (user == null) throw new UserNotFoundException();
        if (institution == null) throw new InstitutionNotFoundException();
        return new Post(user, institution, dto.getRequiredBags(),
                LocalDateTime.now(), dto.getBloodType().toString());
    }
}
