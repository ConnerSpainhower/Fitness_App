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

@Api(value = "TrainerController")
@RestController
public class TrainerController {
	
	@Autowired
    TrainerRepository trainerRepository;
	
	private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    
    /**
     * Finds all trainers in the database.
     * @return Every trainer's info that is currently stored in the database.
     */
    @ApiOperation(value = "Finds all trainers currently stored in the database.")
    @GetMapping(path = "/trainers")
    List<Trainer> getAllTrainers(){
        return trainerRepository.findAll();
    }
    
    /**
     * Finds a trainer in the database based on their id
     * @param id Trainer id
     * @return The trainer with the given id
     */
    @ApiOperation(value = "Finds a trainer in the database based on their id.")
    @GetMapping(path = "/trainers/{id}")
    public Trainer getTrainerById( @PathVariable int id){
        return trainerRepository.findById(id);
    }
    
    /**
     * Creates a new trainer and stores them as a query in the database. 
     * @param trainer Trainer
     * @return success or fail message
     */
    @ApiOperation(value = "Creates a new trainer and stores it in the database.")
    @PostMapping(path = "/register-trainer")
    public String createTrainer(@RequestBody Trainer trainer){
        if (trainer == null)
            return failure;
        trainerRepository.save(trainer);
        return success;
    }
    
    /**
     * Logs in as a trainer. This method takes a trainer as a parameter (name, password)
     * as default and finds an identical trainer in the database
     * @param trainer Trainer
     * @return the id of the trainer in the database, or a failed message if not found
     */
    @ApiOperation(value = "Log in as a trainer. Takes a trainer as a parameter and finds an identical trainer in the database.")
    @PostMapping(path = "/login-trainer")
    public String getTrainer(@RequestBody Trainer trainer) {
    	int j = (int) trainerRepository.count();
    	for(int i=1; i<=(int)j; i++) {
    		if(!trainerRepository.existsById(i)) {
    			j++;
    		}
    		else {
    			if(trainer.getName().equals(trainerRepository.findById(i).getName())) {
    				if(trainer.getPassword().equals(trainerRepository.findById(i).getPassword())) {
    					return "{ \"id\" : " + trainerRepository.getOne(i).getId() +" }";
    				}
    				else {
    					return "The password entered is incorrect.";
    				}
    			}
    		}
    	}
    	return "There is no trainer with the name " + trainer.getName();
    }
    
    
    
    /**
     * Takes an id and a trainer as a parameter and updates the information of the trainer with that id
     * to the information of the trainer given in the parameter.
     * @param id Trainer id
     * @param request Trainer info
     * @return The new trainer information
     */
    @ApiOperation(value = "Takes an id and a trainer as a parameter and updates the information of the trainer with that id.")
    @PutMapping("/trainers/{id}")
    public Trainer updateTrainer(@PathVariable int id, @RequestBody Trainer request){
        Trainer trainer = trainerRepository.findById(id);
        if(trainer == null)
            return null;
        trainerRepository.save(request);
        return trainerRepository.findById(id);
    }
    
    
    /**
     * Finds a trainer with the given id in the database and deletes that query.
     * @param id Trainer id
     * @return Success message
     */
    @ApiOperation(value = "Finds a trainer with the given id in the database and deletes it.")
    @DeleteMapping(path = "/trainers/{id}")
    public String deleteTrainer(@PathVariable int id){
        trainerRepository.deleteById(id);
        return success;
    }
    
    
    
    
}
