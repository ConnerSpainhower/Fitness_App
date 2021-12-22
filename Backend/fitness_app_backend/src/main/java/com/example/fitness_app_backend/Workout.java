package com.example.fitness_app_backend;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "workouts")
public class Workout {
	
	/** The workout's unique id in the database **/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	/** The name of the workout **/
	@Column
    private String workoutName;
	
	/** A brief description of the workout **/
	@Column
    private String workoutDescription;
	
	/** True if the workout has been completed, false otherwise **/
    @Column
    private boolean isCompleted;
    
    /** The user this workout is linked to. Many foods can link to one user **/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	public Workout(String workoutName, String workoutDescription, boolean isCompleted) {
        this.workoutName = workoutName;
        this.workoutDescription= workoutDescription;
        this.isCompleted = false;
        
    }

	public Workout() {
    	
    }
    
    // =============================== Getters and Setters for each field ================================== //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public String getWorkoutDescription() {
		return workoutDescription;
	}

	public void setWorkoutDescription(String workoutDescription) {
		this.workoutDescription = workoutDescription;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
