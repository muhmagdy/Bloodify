package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Accept;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.UserRequests.service.interfaces.AcceptDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AcceptDaoImp implements AcceptDao {
    @Autowired
    AcceptRepository acceptRepository;

    public List<Accept> getAcceptByUser(User user) {
        try {
            return this.acceptRepository.findByUser(user);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Accept> getAcceptByPost(Post post) {
        try{
            return this.acceptRepository.findByPost(post);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}

