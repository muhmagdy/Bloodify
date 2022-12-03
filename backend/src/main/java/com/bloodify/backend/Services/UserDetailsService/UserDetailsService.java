// package com.bloodify.backend.services.UserDetailsService;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.bloodify.backend.dao.interfaces.UserDAO;
// import com.bloodify.backend.model.entities.User;
// import com.bloodify.backend.model.entities.UserAuthentication;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// public abstract class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

//     abstract UserDAO getDao();

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         log.info(username);
//         User user = getDao().findUserByEmail(username);
//         if(user == null)    throw new UsernameNotFoundException(username + " not found");
//         log.info(user.getEmail());
//         // if(!username.equals("foo")) throw new UsernameNotFoundException(username + " not found");
//         UserAuthentication userAuth = new UserAuthentication(user);
//         return userAuth;
//         // return new User("foo", "foo", List.of());
//     }
    
// }
