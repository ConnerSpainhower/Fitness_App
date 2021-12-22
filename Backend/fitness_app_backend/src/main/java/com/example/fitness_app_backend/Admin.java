package com.example.fitness_app_backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin {
	/** Stores the admin's id **/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	/** The admin's username **/
	@Column
    private String name;
	
	/** The admin's password **/
	@Column
    private String password;
	
    
	public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
	
	public Admin() {

	}
    

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

    
    
    
    
    
    
    
}
