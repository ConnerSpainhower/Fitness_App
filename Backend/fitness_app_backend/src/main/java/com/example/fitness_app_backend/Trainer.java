package com.example.fitness_app_backend;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Trainers")
public class Trainer {
	/** Stores the trainer's id **/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	/** The trainer's username **/
	@Column
    private String name;
	
	/** The trainer's password **/
	@Column
    private String password;
	
	@OneToOne
	@JsonIgnore
	private User user;

	public Trainer(String name, String password) {
        this.name = name;
        this.password = password;
    }
	
	public Trainer() {
		
	}
	
	// =============================== Getters and Setters for each field ================================== //
	
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
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    @Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
    
    
      
}
