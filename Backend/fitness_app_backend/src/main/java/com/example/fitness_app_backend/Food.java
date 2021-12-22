package com.example.fitness_app_backend;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Foods")
public class Food {
	
	/** The Food's unique id in the database **/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	/** The name of the food **/
	@Column
    private String foodName;
	
	/** The amount of calories the food is worth **/
	@Column
    private int foodCalories;
	
	/** The user this food is linked to. Many foods can link to one user **/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	public Food(String foodName, int foodCalories) {
		this.foodName = foodName;
		this.foodCalories = foodCalories;
	}
	
	public Food() {
		
	}

	// =============================== Getters and Setters for each field ================================== //
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodCalories() {
		return foodCalories;
	}

	public void setFoodCalories(int foodCalories) {
		this.foodCalories = foodCalories;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		String foodStr = "Food [id=" + id + ", foodName=" + foodName + ", foodCalories=" + foodCalories + ", userId=";
		if(user==null) {
			foodStr = foodStr + "null" + "]";
			return foodStr;
		}
		return  foodStr + user.getId() + "]";
	}
	

}
