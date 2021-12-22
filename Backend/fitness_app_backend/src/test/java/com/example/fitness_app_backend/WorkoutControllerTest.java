package com.example.fitness_app_backend;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class WorkoutControllerTest {

	@InjectMocks
	WorkoutController workoutController;
	
	@Mock
	WorkoutRepository workoutRepo;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllWorkouts() {
		List<Workout> workoutList = workoutController.getAllWorkouts();
		Assertions.assertThat(workoutList).isNotNull();
		Assertions.assertThat(workoutList.size()).isEqualTo(0);
	}
}
