package com.bloodify.backend;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bloodify.backend.dao.classes.UserDAOImp;
import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.model.entities.User;


@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);


    }
    @Autowired
    UserDAO userDAO;

    @Override
    public void run(String... args) throws Exception {
        

        boolean rv = userDAO.saveUser(new User("Joe",
                                "Biden",
                                 "12345678912345", "joe@gmail.com", "A+",
                                  false, LocalDate.now(), "uncle sam"));
        System.out.println(rv);
        
    }
}
