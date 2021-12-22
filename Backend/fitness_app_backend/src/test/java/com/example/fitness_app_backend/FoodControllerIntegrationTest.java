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
public class FoodControllerIntegrationTest {
	@Autowired
	FoodController foodController;

	private void printFoodList(String name) {
		System.out.println( name + " list = [");
		foodController.getAllFoods().stream().forEach(System.out::println);
		System.out.println("]");
	}
	
	@Test
	void testFoodController() {
		List<Food> foodList = foodController.getAllFoods();
		Assertions.assertThat(foodList)
			.isNotNull()
			.isEmpty();
		printFoodList("food controller");

	}
	
	@Test
	void testCreateFood() {
		Food food1 = new Food();
		String result = foodController.createFood(food1);
		List<Food> foodList = foodController.getAllFoods();
		Assertions.assertThat(foodList)
			.isNotNull()
			.isNotEmpty()
			.hasSize(1);
		
		printFoodList("create food");
	}
	
	@Test
	void testDeleteFood() {

		Food food1 = new Food();
		food1.setId(1);
		String createResult = foodController.createFood(food1);

		List<Food> foodList = foodController.getAllFoods();
		Assertions.assertThat(foodList)
			.isNotNull()
			.isNotEmpty()
			.hasSize(1);
		
		printFoodList("before delete food");
		
		int idToDelete = foodList.get(0).getId();
		String deleteResult = foodController.deleteFood(idToDelete);
		List<Food> foodList2 = foodController.getAllFoods();
		printFoodList("after delete food");
		Assertions.assertThat(foodList2)
			.isNotNull()
			.isEmpty();
	}
	
}
