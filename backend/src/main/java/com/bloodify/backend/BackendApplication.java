package com.bloodify.backend;

import com.bloodify.backend.dao.UserDAOIFace;
import com.bloodify.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	RandomGenerations random = new RandomGenerations();

	List<String> firstNames = new ArrayList<>();
	List<String> lastNames = new ArrayList<>();
	List<String> bloodTypes = new ArrayList<>();
	List<String> emails = new ArrayList<>();
	List<String> passwords = new ArrayList<>();
	List<String> nationalIDs = new ArrayList<>();
	List<Boolean> haveDiseases = new ArrayList<>();
	List<LocalDate> lastDonates = new ArrayList<>();

	@Autowired
	UserDAOIFace userService;

	@Override
	public void run(String... args) throws Exception {



//		RandomGenerations random = new RandomGenerations();
//
//		int n = 100;
//		for(int i=0; i<n; i++) {
//			firstNames.add(random.generateName(4, 10));
//			lastNames.add(random.generateName(4, 10));
//			nationalIDs.add(random.generateNationalID());
//			emails.add(random.generateEmail(10, 30));
//			bloodTypes.add(random.generateBloodType());
//			bloodSigns.add(random.generateBloodSign());
//			haveDiseases.add(random.generateDiseases());
//			lastDonates.add(random.generateDate(1980, 2022));
//			passwords.add(random.generatePassword(15));
//		}
//
//		for(int i=0; i<n; i++) {
//			User user = new User(
//					firstNames.get(i), lastNames.get(i), nationalIDs.get(i), emails.get(i), bloodTypes.get(i),
//					bloodSigns.get(i), haveDiseases.get(i), lastDonates.get(i), passwords.get(i)
//			);
//			userService.saveUser(user);
//		}





		User guy = new User("Joe", "Biden", "30014120200587",
				"Yo@yo.com", "A-", false,
				LocalDate.now(), "strongPass");

		User guy2 = new User("Bla", "BlaBla", "30094120200587",
				"as@as.com", "O+", false,
				LocalDate.now(), "Strrrii");

		boolean isInserted = userService.saveUser(guy);
		boolean isInserted2 = userService.saveUser(guy2);
		String lastName = userService.findUserByNationalID("30094120200587").getLastName();
//
////		boolean signingIn = userService.isUsernameAndPasswordMatching("Yo@yo.com", "strongPass");
////		List<User> matchingUsers = userService.getUsersByBloodType("A", '-');
//
		System.out.println("User1 --> " + isInserted + "\nUser2 --> " + isInserted2 +
				" and lastName is " + lastName );
// + "\nCould Sign in? " + signingIn);
////		for(User u : matchingUsers)
////			System.out.println(u.getFirstName() + " " + u.getLastName());
	}
}
