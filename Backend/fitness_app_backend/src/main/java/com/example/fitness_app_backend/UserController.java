package com.example.fitness_app_backend;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserController")
@RestController
public class UserController {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	TrainerRepository trainerRepository;
	
	@Autowired
	WorkoutRepository workoutRepository;
	
	/** To be displayed when an HTTP request fails or succeeds **/
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    
    /**
     * Finds all users in the database.
     * @return Every user's info that is currently stored in the database.
     */
    @ApiOperation(value = "Finds all users currently in the database.")
    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
    

    /**
     * Finds a user in the database based on their id
     * @param id User id
     * @return The user with the given id
     */
    @ApiOperation(value = "Finds a user in the database based on a given id.")
    @GetMapping(path = "/users/{id}")
    public User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }
    
    /**
     * Creates a new user and stores them as a query in the database. Calories is set to 0 by default.
     * @param user User
     * @return success or fail message
     */
    @ApiOperation(value = "Creates a new user and stores it in the database.")
    @PostMapping(path = "/register")
    public String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        return success;
    }
    
    /**
     * Logs in as a user. This method takes a user as a parameter (name, password, and calories which is set to 0
     * as default and finds an identical user in the database
     * @param user User
     * @return the id of the student in the database, or a failed message if not found
     */
    @ApiOperation(value = "Log in as a user. This method takes a user as a parameter, and finds an identical user in the database.")
    @PostMapping(path = "/login")
    public String getUser(@RequestBody User user) {
    	int j = (int) userRepository.count();
    	for(int i=1; i<=(int)j; i++) {
    		if(!userRepository.existsById(i)) {
    			j++;
    		}
    		else {
    			if(user.getName().equals(userRepository.findById(i).getName())) {
    				if(user.getPassword().equals(userRepository.findById(i).getPassword())) {
    					return "{ \"id\" : " + userRepository.getOne(i).getId() +" }";
    				}
    				else {
    					return "The password entered is incorrect.";
    				}
    			}
    		}
    	}
    	return "There is no user with the name " + user.getName();
    }

    /**
     * Takes an id and a user as a parameter and updates the information of the user with that id
     * to the information of the user given in the parameter.
     * @param id User id
     * @param request User info
     * @return The new user information
     */
    @ApiOperation(value = "Takes an id and a user as a parameter and updates the information of the user with that id.")
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }
    
    /**
     * Finds a user with the given id in the database and deletes that query.
     * @param id User id
     * @return Success message
     */
    @ApiOperation(value = "Finds a user with the given id in the database and deletes it.")
    @DeleteMapping(path = "/users/{id}")
    public String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }
    
    /**
     * Adds a food to a given user's profile. Takes in a user Id and a foodId
     * and adds the food with the given id to the user with the giver user id.
     * @param userId User id
     * @param foodId Food id
     * @return Success or failure message
     */
    @ApiOperation(value = "Adds a food to a given user's profile.")
    @PutMapping("/users/{userId}/foods/{foodId}")
    public String addFoodToUser(@PathVariable int userId, @PathVariable int foodId) {
    	User user = userRepository.findById(userId);
    	Food food = foodRepository.findById(foodId);
    	if(user == null || food == null) {
    		return failure;
    	}
    	food.setUser(user);
    	user.getFoods().add(food);
    	user.setCalories(user.getCalories() + food.getFoodCalories());
    	userRepository.save(user);
    	return success;
    }
    
    /**
     * Assigns a trainer to a user. Takes a userId and a trainerId as parameters and
     * assigns the user with the given userId to the trainer with the given trainerId
     * @param userId User id
     * @param trainerId Trainer id
     * @return Success or failure message
     */
    @ApiOperation(value = "Assigns a trainer to a user. This will be their personal trainer.")
    @PutMapping("/users/{userId}/trainers/{trainerId}")
    public String assignTrainerToUser(@PathVariable int userId, @PathVariable int trainerId) {
    	User user = userRepository.findById(userId);
    	Trainer trainer = trainerRepository.findById(trainerId);
    	if(user == null || trainer == null) {
    		return failure;
    	}
    	trainer.setUser(user);
    	user.setTrainer(trainer);
    	userRepository.save(user);
    	return success;
    	
    }
    
    /**
     * Allows a trainer to assign workouts to their user that they are assigned to. Takes a
     * userId and a workoutId as parameters and assigns the workout with the given id to the
     * user with the given userId
     * @param userId User id
     * @param workoutId Workout id
     * @return Success or failure message
     */
    @ApiOperation(value = "Assigns a workout to a user. Allows a trainer to suggest workouts for their user.")
    @PutMapping("/users/{userId}/workouts/{workoutId}")
    public String assignWorkoutToUser(@PathVariable int userId, @PathVariable int workoutId) {
    	User user = userRepository.findById(userId);
    	Workout workout = workoutRepository.findById(workoutId);
    	if(user == null || workout == null) {
    		return failure;
    	}
    	workout.setUser(user);
    	user.getMyWorkouts().add(workout);
    	userRepository.save(user);
    	return success;
    }
    
}
