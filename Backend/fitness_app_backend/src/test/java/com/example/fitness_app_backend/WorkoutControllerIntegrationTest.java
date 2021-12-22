package com.example.fitness_app_backend;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.Assertions;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WorkoutControllerIntegrationTest {

	@Autowired
	WorkoutController workoutController;

	private void printWorkoutList(String name) {
		System.out.println( name + " List = [");
		workoutController.getAllWorkouts().stream().forEach(System.out::println);
		System.out.println("]");
	}

	
	@Test
	void testWorkoutController() {
		List<Workout> workoutList = workoutController.getAllWorkouts();
		Assertions.assertThat(workoutList)
			.isNotNull()
			.isEmpty();
		printWorkoutList("workout controller");

	}

	@Test
	void testCreateWorkout() {
		Workout workout1 = new Workout();
		String result = workoutController.createWorkout(workout1);
		List<Workout> workoutList = workoutController.getAllWorkouts();
		Assertions.assertThat(workoutList)
			.isNotNull()
			.isNotEmpty()
			.hasSize(1);
		
		printWorkoutList("create workout");
	}

	@Test
	void testDeleteWorkout() {

		Workout workout1 = new Workout();
		workout1.setId(1);
		String createResult = workoutController.createWorkout(workout1);

		List<Workout> workoutList = workoutController.getAllWorkouts();
		Assertions.assertThat(workoutList)
			.isNotNull()
			.isNotEmpty()
			.hasSize(1);
		
		printWorkoutList("before delete workout");
		
		String deleteResult = workoutController.deleteWorkout(1);
		List<Workout> workoutList2 = workoutController.getAllWorkouts();
		Assertions.assertThat(workoutList2)
			.isNotNull()
			.isEmpty();
			

		
		printWorkoutList("after delete workout");

	}

}
