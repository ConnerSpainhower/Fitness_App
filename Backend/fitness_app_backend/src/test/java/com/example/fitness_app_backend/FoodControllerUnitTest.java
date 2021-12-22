package com.example.fitness_app_backend;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FoodControllerUnitTest {
	@InjectMocks
	FoodController foodController;
	
	@Mock
	FoodRepository foodRepoMock;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Nested
	class getAllFoodsTests {
		
		@Test
		public void testGetAllFoodsWhenEmpty() {
			List<Food> foodList = foodController.getAllFoods();
			Assertions.assertThat(foodList).isNotNull();
			Assertions.assertThat(foodList.size()).isEqualTo(0);
		}
		
		@Test
		public void testGetAllFoodsWhen1Entry() {
			List<Food> mockFoodList = new ArrayList<>();
			mockFoodList.add(new Food());
			doReturn(mockFoodList).when(foodRepoMock).findAll();
			
			List<Food> foodList = foodController.getAllFoods();
			Assertions.assertThat(foodList).isNotNull();
			Assertions.assertThat(foodList.size()).isEqualTo(1);
		}
	}
	
	
	@Nested
	class getFoodByIdTests {
		@Test
		public void testGetFoodByIdWhenNoFood() {
			doReturn(null).when(foodRepoMock).findById(1);
			Food result = foodController.getFoodById(1);
			Assertions.assertThat(result).isEqualTo(null);
		}
		
		@Test
		public void testGetFoodByIdWhen1Food() {
			Food food = new Food();
			food.setId(1);
			doReturn(food).when(foodRepoMock).findById(1);
			Food result = foodController.getFoodById(1);
			Assertions.assertThat(result).isNotNull();
			Assertions.assertThat(result).isEqualTo(food);
		}
		
		@Test
		public void testGetFoodByIdWhen2Foods() {
			Food food1 = new Food();
			food1.setId(1);
			Food food2 = new Food();
			food2.setId(2);
			doReturn(food2).when(foodRepoMock).findById(2);
			Food result = foodController.getFoodById(2);
			Assertions.assertThat(result).isNotNull();
			Assertions.assertThat(result).isEqualTo(food2);
		}
		
	}
	
	@Nested
	class deleteFoodTests {
		@Test
		public void testDeleteFood() {
			Food food1 = new Food();
			food1.setId(1);
			String result = foodController.deleteFood(1);
			verify(foodRepoMock).deleteById(1);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"success\"}");
		}
	}
	
	@Nested
	class createFoodTests {
		@Test
		public void testCreatFoodFailure() {
			String result = foodController.createFood(null);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"failure\"}");
		}
		
		@Test
		public void testCreatFoodSuccess() {
			Food food1 = new Food();
			String result = foodController.createFood(food1);
			verify(foodRepoMock).save(food1);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"success\"}");
		}
	}
	
	
}
