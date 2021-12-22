package com.example.fitness_app_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "WorkoutController")
@RestController
public class WorkoutController {
	
	@Autowired
	WorkoutRepository workoutRepository;
	
	/** To be displayed when an HTTP request fails or succeeds **/
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	
	/**
	 * Returns all workouts currently stored in the database.
	 * @return list of workouts
	 */
	@ApiOperation(value = "Finds all workouts currently stored in the database.")
	@GetMapping(path = "/workouts")
	public List<Workout> getAllWorkouts(){
		return workoutRepository.findAll();
	}
	
	/**
	 * Returns the workout in the database with the id passed in as a
	 * parameter.
	 * @param id Workout id
	 * @return the workout with the given id
	 */
	@ApiOperation(value = "Finds the workout stored in the database that matches the given id.")
	@GetMapping(path = "/workouts/{id}")
	public Workout getWorkoutById(@PathVariable int id) {
		return workoutRepository.findById(id);
	}
	
	/**
	 * Takes in a workout object as a parameter and creates a new workout in the
	 * database with the given workout's attributes.
	 * @param workout Given workout
	 * @return Success or fail message
	 */
	@ApiOperation(value = "Takes in a Workout object as a parameter and creates a new workout in the database.")
	@PostMapping(path = "/workouts")
	public String createWorkout(@RequestBody Workout workout) {
		if(workout == null) {
			return failure;
		}
		workoutRepository.save(workout);
		return success;
	}
	
	/**
	 * Takes in a workout id as a parameter and searches the database for the workout
	 * with that id, and deletes it from the database.
	 * @param id Workout id
	 * @return Success message
	 */
	@ApiOperation(value = "Finds a workout in the database with a matching id and deletes it.")
	@DeleteMapping(path = "/workouts/{id}")
    public String deleteWorkout(@PathVariable int id){
        workoutRepository.deleteById(id);
        return success;
    }

}
