package com.bloodify.backend.dao.classes;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bloodify.backend.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.model.entities.Institution;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InstitutionDAOImp implements InstitutionDAO {
    
    // @Autowired
    // InstitutionRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username + " inst");
        // Institution institution = this.findUserByEmail(username);
        // if(institution == null)    throw new UsernameNotFoundException(username + " not found");
        // log.info(institution.getEmail());
        if(!username.equals("foo")) throw new UsernameNotFoundException(username + " not found");
        // UserAuthentication userAuth = new UserAuthentication(user);
        // return userAuth;
        return new User("foo", "foo", List.of(new SimpleGrantedAuthority("Institution")));
    }

    @Override
    public Institution findUserByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }


}
