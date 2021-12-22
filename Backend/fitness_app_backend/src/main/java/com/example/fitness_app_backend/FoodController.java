package com.example.fitness_app_backend;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "FoodController")
@RestController
public class FoodController {
	
	@Autowired
	FoodRepository foodRepository;
	
	/** To be displayed when an HTTP request fails or succeeds **/
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	
	/**
	 * Gets all the food entries in the database and returns them in a List.
	 * @return all foods currently in the database
	 */
	@ApiOperation(value = "Gets all the food entries currently in the database.")
	@GetMapping(path = "/foods")
	List<Food> getAllFoods(){
		return foodRepository.findAll();
	}
	
	/**
	 * Takes a food id as a parameter and searches the database for a food with 
	 * a matching id and returns it.
	 * @param id Food id
	 * @return the food with the given id
	 */
	@ApiOperation(value = "Takes a food id as a parameter and searches the database for a matching food.")
	@GetMapping(path = "/foods/{id}")
	public Food getFoodById(@PathVariable int id) {
		return foodRepository.findById(id);
	}
	
	/**
	 * Takes in a Food object as a parameter and creates a new food entity in the 
	 * database with the attributes of the given Food object.
	 * @param food the Food to create
	 * @return Success or fail message
	 */
	@ApiOperation(value = "Takes in a Food object as a parameter and creates a new Food entity in the database.")
	@PostMapping(path = "/foods")
	public String createFood(@RequestBody Food food) {
		if(food == null) {
			return failure;
		}
		foodRepository.save(food);
		return success;
	}
	
	/**
	 * Takes in a food id as a parameter and searches the database for a matching id, 
	 * deleting that entry.
	 * @param id Food id
	 * @return Success message
	 */
	@ApiOperation(value = "Takes in a food id as a parameter and searches the database for a matching id, deleting that entry.")
	@DeleteMapping(path = "/foods/{id}")
    public String deleteFood(@PathVariable int id){
        foodRepository.deleteById(id);
        return success;
    }
	

}
