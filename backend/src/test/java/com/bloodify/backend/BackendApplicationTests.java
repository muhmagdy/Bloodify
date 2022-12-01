package com.bloodify.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
//@ComponentScan
class BackendApplicationTests implements CommandLineRunner {

	@Autowired
	TestUserDAO test;

	@Test
	void contextLoads() {
	}

	@Override
	public void run(String... args) throws Exception {
		test.insertValues();
	}
}
