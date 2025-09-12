package com.example.jwt.jwt_auth;

import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.DtoUserIU;
import com.example.jwt.jwt_auth.services.IUserService;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {JwtAuthApplication.class})
class JwtAuthApplicationTests {

	@Autowired
	private IUserService userService;

	@BeforeEach
	public void beforeEach() {

	}

	@Test
	public void testAddUser() {
		Faker faker = new Faker();
		DtoUserIU newUser = new DtoUserIU();
		newUser.setUsername(faker.name().fullName());
		newUser.setEmail(faker.internet().emailAddress());
		newUser.setPassword(faker.internet().password(8, 16, true, true));
		DtoUser savedUser = userService.addUser(newUser);
		Assertions.assertNotNull(savedUser);
	}


	/*
	@Test
	public void testGetAllUsers() {
		List<DtoUser> allUsers = userService.getAllUsers();
		Assertions.assertNotNull(allUsers);
		System.out.println(allUsers);
	}

	@Test
	public void testFindUserById() {
		DtoUser user = userService.findUserById(1L);
		Assertions.assertNotNull(user);
	}
	*/
	@AfterEach
	public void afterEach() {

	}
}
