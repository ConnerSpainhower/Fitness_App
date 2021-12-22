package com.example.fitness_app_backend;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class UserControllerIntegrationTest {
	
	@Autowired
	UserController userController;
	
	@Autowired
	FoodController foodController;

	private void printUserList(String name) {
		System.out.println(name + " list = [");
		userController.getAllUsers().stream().forEach(System.out::println);
		System.out.println("]");
	}

	@Test
	void testGetAllUsers() {
		List<User> userList = userController.getAllUsers();
		Assertions.assertThat(userList).isNotNull().isEmpty();
		printUserList("getAllUsers");
	}

	@Test
	void testCreateUser() {
		User user1 = new User();
		String result = userController.createUser(user1);
		List<User> userList = userController.getAllUsers();
		Assertions.assertThat(userList).isNotNull().isNotEmpty().hasSize(1);

		printUserList("create user");
	}

	@Test
	void testGetUserById() {
		User user1 = new User();
		String result = userController.createUser(user1);
		List<User> userList = userController.getAllUsers();
		int id = userList.get(0).getId();
		User user2 = userController.getUserById(id);
		Assertions.assertThat(user2).isEqualTo(user1);

		printUserList("getUserById");
	}

	@Test
	void testGetUser() {
		User userBob = new User("Bob", "Password", 0);
		String createUserResult = userController.createUser(userBob);
		List<User> userList = userController.getAllUsers();
		int id = userList.get(0).getId();
		String result = userController.getUser(userBob);
		Assertions.assertThat(result).isEqualTo("{ \"id\" : " + id + " }");
		printUserList("getUser");
	}

	@Test
	void testDeleteUser() {
		User user1 = new User();
		String createResult = userController.createUser(user1);
		List<User> userList1 = userController.getAllUsers();
		Assertions.assertThat(userList1).isNotNull().isNotEmpty().hasSize(1);

		printUserList("beforeDeleteUser");
		int id = userList1.get(0).getId();
		String deleteResult = userController.deleteUser(id);
		List<User> userList2 = userController.getAllUsers();
		Assertions.assertThat(userList2).isNotNull().isEmpty();
		printUserList("afterDeleteUser");

	}
	
	@Test
	void testUpdateUser() {
		User user1 = new User();
		user1.setName("abc");
		String result = userController.createUser(user1);
		List<User> userList = userController.getAllUsers();
		int id = userList.get(0).getId();
		Assertions.assertThat(userList).isNotNull().isNotEmpty().hasSize(1);
		printUserList("beforeUpdateUser");
		
		User request = userList.get(0);
		request.setName("xyz");
		User resultUser = userController.updateUser(id, request);
		Assertions.assertThat(resultUser.toString()).isEqualTo(request.toString());
		printUserList("afterUpdateUser");
	}
	
	@Test
	void testAddFoodToUser() {
		User userJeb = new User("Jeb", "Password", 0);
		Food apple = new Food("apple", 100);
		String createdUser = userController.createUser(userJeb);
		String createdFood = foodController.createFood(apple);
		List<User> userList = userController.getAllUsers();
		List<Food> foodList = foodController.getAllFoods();
		int userId = userList.get(0).getId();
		int foodId = foodList.get(0).getId();
		String addedFoodToUser = userController.addFoodToUser(userId, foodId);
		List<User> userList2 = userController.getAllUsers();
		List<Food> foodList2 = foodController.getAllFoods();
		User userAfterUpdate = userList2.get(0);
		Food foodAfterUpdate = foodList2.get(0);
		Assertions.assertThat(foodAfterUpdate.getUser().getId()).isEqualTo(userAfterUpdate.getId());
		Assertions.assertThat(userAfterUpdate.getCalories()).isEqualTo(foodAfterUpdate.getFoodCalories());
	}

}
