package com.bloodify.backend;

import com.bloodify.backend.dao.UserRepository;
import com.bloodify.backend.model.User;
import com.bloodify.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	UserService userService;

	@Override
	public void run(String... args) throws Exception {
		User guy = new User(1, "Joe", "Biden", "30014120200587",
				"Yo@yo.com", "A", '-', false,
				LocalDate.now(), "strongPass");

		boolean isInserted = userService.saveUser(guy);
		String lastName = userService.findUserByEmail("Yo@yo.com").getLastName();

		boolean signingIn = userService.isUsernameAndPasswordMatching("Yo@yo.com", "strongPass");
		List<User> matchingUsers = userService.getUsersByBloodType("A", '-');

		System.out.println("Insertion was done successfully -->" + isInserted +
				" and lastName is " + lastName + "\nCould Sign in? " + signingIn);
		for(User u : matchingUsers)
			System.out.println(u.getFirstName() + " " + u.getLastName());
	}
}
