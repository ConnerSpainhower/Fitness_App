package com.example.fitness_app_backend;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserControllerUnitTest {
	@InjectMocks
	UserController userController;

	@Mock
	UserRepository userRepoMock;

	@Mock
	FoodRepository foodRepoMock;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	class CreateUserTests {
		@Test
		public void testCreateUserFailure() {
			String result = userController.createUser(null);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"failure\"}");
		}

		@Test
		public void testCreateUserSuccess() {
			User user = new User();
			String result = userController.createUser(user);
			verify(userRepoMock).save(user);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"success\"}");
		}
	}

	@Nested
	class GetUserTests {
		@Test
		public void testGetUserSuccess() {
			User userBob = new User("Bob", "Password", 0);
			userBob.setId(10);
			doReturn(1L).when(userRepoMock).count();
			doReturn(true).when(userRepoMock).existsById(1);
			doReturn(userBob).when(userRepoMock).findById(1);
			doReturn(userBob).when(userRepoMock).getOne(1);
			String result = userController.getUser(userBob);
			Assertions.assertThat(result).isEqualTo("{ \"id\" : " + 10 + " }");
		}

		@Test
		public void testGetUserWrongUser() {
			User userBob = new User("Bob", "Password", 10);
			userBob.setId(10);
			User userBobby = new User("Bobby", "Password", 10);
			doReturn(1L).when(userRepoMock).count();
			doReturn(true).when(userRepoMock).existsById(1);
			doReturn(userBobby).when(userRepoMock).findById(1);
			doReturn(userBob).when(userRepoMock).getOne(1);
			String result = userController.getUser(userBob);
			Assertions.assertThat(result).isEqualTo("There is no user with the name Bob");
		}
	}

	@Nested
	class UpdateUserTests {
		@Test
		public void testUpdateUserNullCase() {
			doReturn(null).when(userRepoMock).findById(0);
			User result = userController.updateUser(0, null);
			Assertions.assertThat(result).isEqualTo(null);
		}

		@Test
		public void testUpdateUserNotNullCase() {
			doReturn(new User()).when(userRepoMock).findById(0);
			User request = new User();
			doReturn(request).when(userRepoMock).findById(0);
			User result = userController.updateUser(0, request);
			verify(userRepoMock).save(request);
			Assertions.assertThat(result).isEqualTo(request);
		}

	}

	@Nested
	class AddFoodToUserTests {
		@Test
		public void testAddFoodToUserBothFailure() {
			doReturn(null).when(userRepoMock).findById(0);
			doReturn(null).when(foodRepoMock).findById(0);
			String result = userController.addFoodToUser(0, 0);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"failure\"}");

		}

		@Test
		public void testAddFoodToUserUserIdFailure() {
			doReturn(null).when(userRepoMock).findById(0);
			doReturn(new Food()).when(foodRepoMock).findById(0);
			String result = userController.addFoodToUser(0, 0);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"failure\"}");
		}

		@Test
		public void testAddFoodToUserFoodIdFailure() {
			doReturn(new User()).when(userRepoMock).findById(0);
			doReturn(null).when(foodRepoMock).findById(0);
			String result = userController.addFoodToUser(0, 0);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"failure\"}");
		}
	}

}
