package com.bloodify.backend;

import com.bloodify.backend.model.User;
import com.bloodify.backend.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	UserDAO userService;

	@Override
	public void run(String... args) throws Exception {
		User guy = new User("Joe", "Biden", "30014120200587",
				"Yo@yo.com", "A", '-', false,
				LocalDate.now(), "strongPass");

		User guy2 = new User("Bla", "BlaBla", "30094120200587",
				"as@as.com", "O", '+', false,
				LocalDate.now(), "Strrrii");

		boolean isInserted = userService.saveUser(guy);
		boolean isInserted2 = userService.saveUser(guy2);
//		String lastName = userService.findUserByEmail("Yo@yo.com").getLastName();

//		boolean signingIn = userService.isUsernameAndPasswordMatching("Yo@yo.com", "strongPass");
//		List<User> matchingUsers = userService.getUsersByBloodType("A", '-');

		System.out.println("User1 --> " + isInserted + "\nUser2 --> " + isInserted2);
//				" and lastName is " + lastName + "\nCould Sign in? " + signingIn);
//		for(User u : matchingUsers)
//			System.out.println(u.getFirstName() + " " + u.getLastName());
	}
}
