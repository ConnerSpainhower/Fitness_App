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

@Api(value = "AdminController")
@RestController
public class AdminController {
	@Autowired
    AdminRepository adminRepository;
	
	
	/** To be displayed when an HTTP request fails or succeeds **/
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    
    /**
     * Finds all admins in the database.
     * @return Every admin's info that is currently stored in the database.
     */
    @ApiOperation(value = "Find all admins currently in the database.")
    @GetMapping(path = "/admins")
    List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }
    
    /**
     * Finds an admin in the database based on their id
     * @param id Admin id
     * @return The admin with the given id
     */
    @ApiOperation(value = "Finds an admin in the databased based on the id given.")
    @GetMapping(path = "/admins/{id}")
    public Admin getAdminById( @PathVariable int id){
        return adminRepository.findById(id);
    }
    
    
    
    
    /**
     * Creates a new admin and stores them as a query in the database. 
     * @param admin Admin
     * @return success or fail message
     */
    @ApiOperation(value = "Creates a new admin and stores it in the database.")
    @PostMapping(path = "/register-admin")
    public String createAdmin(@RequestBody Admin admin){
        if (admin == null)
            return failure;
        adminRepository.save(admin);
        return success;
    }
    
    
    
    
    
    
    /**
     * Logs in as an admin. This method takes an admin as a parameter (name, password)
     * finds an identical admin in the database
     * @param admin Admin
     * @return the id of the admin in the database, or a failed message if not found
     */
    @ApiOperation(value = "Logs in as an admin, Takes an admin as a parameter and finds an identical admin in the database.")
    @PostMapping(path = "/login-admin")
    public String getAdmin(@RequestBody Admin admin) {
    	int j = (int) adminRepository.count();
    	for(int i=1; i<=(int)j; i++) {
    		if(!adminRepository.existsById(i)) {
    			j++;
    		}
    		else {
    			if(admin.getName().equals(adminRepository.findById(i).getName())) {
    				if(admin.getPassword().equals(adminRepository.findById(i).getPassword())) {
    					return "{ \"id\" : " + adminRepository.getOne(i).getId() +" }";
    				}
    				else {
    					return "The password entered is incorrect.";
    				}
    			}
    		}
    	}
    	return "There is no admin with the name " + admin.getName();
    }

    
    /**
     * Takes an id and an admin as a parameter and updates the information of the admin with that id
     * to the information of the admin given in the parameter.
     * @param id Admin id
     * @param request Admin info
     * @return The new admin information
     */
    @ApiOperation(value = "Takes an id and an admin as a parameter and updates the information of that admin.")
    @PutMapping("/admins/{id}")
    public Admin updateAdmin(@PathVariable int id, @RequestBody Admin request){
        Admin admin = adminRepository.findById(id);
        if(admin == null)
            return null;
        adminRepository.save(request);
        return adminRepository.findById(id);
    }
    
    /**
     * Finds an admin with the given id in the database and deletes that query.
     * @param id Admin id
     * @return Success message
     */
    @ApiOperation(value = "Finds an admin with the given id in the database and deletes it.")
    @DeleteMapping(path = "/admins/{id}")
    public String deleteAdmin(@PathVariable int id){
        adminRepository.deleteById(id);
        return success;
    }
    
    
    
}
