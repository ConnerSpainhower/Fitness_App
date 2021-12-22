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


public class WorkoutControllerUnitTest {

	@InjectMocks
	WorkoutController workoutController;
	
	@Mock
	WorkoutRepository workoutRepoMock;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Nested
	class AllWorkoutsTests {
		
		@Test
		public void testGetAllWorkoutsWhenEmpty() {
			List<Workout> workoutList = workoutController.getAllWorkouts();
			Assertions.assertThat(workoutList).isNotNull();
			Assertions.assertThat(workoutList.size()).isEqualTo(0);
		}
		
		@Test
		public void testGetAllWorkoutsWhen1Entry() {
			List<Workout> mockList = new ArrayList<>();
			mockList.add(new Workout());
			
			doReturn(mockList).when(workoutRepoMock).findAll();
			
			List<Workout> workoutList = workoutController.getAllWorkouts();
			Assertions.assertThat(workoutList).isNotNull();
			Assertions.assertThat(workoutList.size()).isEqualTo(1);
		}
		
	}
	
	@Nested
	class GetWorkoutByIdTests {
		@Test
		public void testGetWorkoutByIdWhenNoWorkout() {
			
			doReturn(null).when(workoutRepoMock).findById(1);
			Workout result = workoutController.getWorkoutById(1);
			Assertions.assertThat(result).isEqualTo(null);
		}
		
		@Test
		public void testGetWorkoutByIdWhen1Workout() {
			Workout workout = new Workout();
			workout.setId(1);
			
			doReturn(workout).when(workoutRepoMock).findById(1);
			Workout result = workoutController.getWorkoutById(1);
			Assertions.assertThat(result).isNotNull();
			Assertions.assertThat(result).isEqualTo(workout);
		}
		
		@Test
		public void testGetWorkoutByIdWhen2Workouts() {
			Workout workout1 = new Workout();
			workout1.setId(1);
			Workout workout2 = new Workout();
			workout1.setId(2);
			
			doReturn(workout2).when(workoutRepoMock).findById(2);
			Workout result = workoutController.getWorkoutById(2);
			Assertions.assertThat(result).isNotNull();
			Assertions.assertThat(result).isEqualTo(workout2);
		}
	}
	
	@Nested
	class deleteWorkoutTests {
		@Test
		public void testDeleteWorkout() {
			Workout workout1 = new Workout();
			workout1.setId(1);
			String result = workoutController.deleteWorkout(1);
			verify(workoutRepoMock).deleteById(1);
			
		}
		
	}
	
	@Nested
	class createWorkoutTests {
		@Test
		public void testCreatWorkoutFailure() {
			String result = workoutController.createWorkout(null);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"failure\"}");
		}
		
		@Test
		public void testCreatWorkoutSuccess() {
			Workout workout1 = new Workout();
			String result = workoutController.createWorkout(workout1);
			verify(workoutRepoMock).save(workout1);
			Assertions.assertThat(result).isEqualTo("{\"message\":\"success\"}");
		}
	}
}


