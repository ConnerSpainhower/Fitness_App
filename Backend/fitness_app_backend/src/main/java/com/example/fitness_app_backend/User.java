package com.example.fitness_app_backend;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
	
	/** Stores the user's id **/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	/** The user's username **/
	@Column
    private String name;
	
	/** The user's password **/
	@Column
    private String password;
    
	/** The amount of calories for a user. Starts at 0. Positive number means calorie surplus, negative means deficit **/
    @Column
    private int calories;
    
    /** The list of foods the user has eaten today. User can add foods to track their calories in the app **/
    @OneToMany(mappedBy = "user")
    private List<Food> foods;
    
    @OneToMany(mappedBy = "user")
    private List<Workout> myWorkouts;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    
    public User(String name, String password, int calories) {
        this.name = name;
        this.password = password;
        this.calories = calories;
        foods = new ArrayList<>();
        myWorkouts = new ArrayList<>();
    }
    
    public User() {
    	foods = new ArrayList<>();
    	myWorkouts = new ArrayList<>();
    }


    // =============================== Getters and Setters for each field ================================== //

    public List<Workout> getMyWorkouts() {
		return myWorkouts;
	}

	public void setMyWorkouts(List<Workout> myWorkouts) {
		this.myWorkouts = myWorkouts;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
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

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}
	
	public Trainer getTrainer() {
		return trainer;
	}
	
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", calories=" + calories + "]";
	}


}
