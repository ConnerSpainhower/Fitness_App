package com.example.fitness_app_backend;

import static org.mockito.Mockito.*;







import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {
	
	@InjectMocks
	FoodController foodController;
	
	@InjectMocks
	UserController userController;
	
	@Mock
	FoodRepository foodRepo;
	
	@Mock
	UserRepository userRepo;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUserController() {
		User u = new User("Sean", "password123", 1000);
		u.setId(1);
		
		when(userRepo.findById(1)).thenReturn(u);
		
		assertEquals(userController.getUserById(1).getId(), 1);
		
	}
	
	// test that the getAllUsers function returns an empty List when there are 
	// no users in the database
	@Test
	public void testGetAllUsers() {
		List<User> userList = userController.getAllUsers();
		assertEquals(userList.size(), 0);
		
	}
	
	@Test
	public void testDeleteUser() {
		User user = new User("Sean Balogh", "password123", 0);
		user.setId(2);
		
		when(userRepo.findById(2)).thenReturn(user);
		userController.createUser(user);
		
		assertEquals(userController.deleteUser(2), "{\"message\":\"success\"}");
		
	}
	
	// tests that getUserById gets the correct user from the database
	@Test
	public void testGetUserById() {
		when(userRepo.findById(1)).thenReturn(new User("Sean", "password", 1000));
		User user = userController.getUserById(1);
		
		assertEquals("Sean", user.getName());
		assertEquals("password", user.getPassword());
		assertEquals(1000, user.getCalories());
	}
	
	// tests when a user tries to login with a user that does not exist
	// in the database
	@Test
	public void testLoginNoSuchUser() {
			
		assertEquals("There is no user with the name Sean", userController.getUser(new User("Sean", "password123", 0)));
		
	}
	
	
	// Tests when a user tries to login with the wrong password. user is the 
	// actual user, user2 is the user with the wrong password
	@Test
	public void testLoginWrongPassword() {
		User user = new User("Sean", "password123", 0);
		User user2 = new User("Sean", "password12", 0);
		user.setId(3);
		user2.setId(4);
		
		when(userRepo.findById(3)).thenReturn(user);
		when(userRepo.findById(4)).thenReturn(user2);
		userController.createUser(user);
		userController.createUser(user2);
		when(userRepo.count()).thenReturn((long) 2);
		when(userRepo.getOne(3)).thenReturn(user);
		when(userRepo.getOne(4)).thenReturn(user2);
		when(userRepo.existsById(3)).thenReturn(true);
		when(userRepo.existsById(4)).thenReturn(true);
		
		String output = userController.getUser(user2);
		assertEquals("The password entered is incorrect.", output);
		
		
	}
	
	@Test
	public void testAddFoodToUser() {
		User user = new User("Sean", "password123", 0);
		user.setId(1);
		Food food = new Food("Banana", 100);
		food.setId(1);
		
		when(userRepo.findById(1)).thenReturn(user);
		when(foodRepo.findById(1)).thenReturn(food);
		userController.createUser(user);
		foodController.createFood(food);
		
		String output = userController.addFoodToUser(1, 1);
		
		assertEquals("{\"message\":\"success\"}", output);
		assertEquals(100, user.getCalories());
	}

}
