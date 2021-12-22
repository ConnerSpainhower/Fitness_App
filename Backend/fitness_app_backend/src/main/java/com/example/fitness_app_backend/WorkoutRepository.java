package com.example.fitness_app_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
	
	
    Workout findById(int id);

    @Transactional
    void deleteById(int id);
    
    
    
}
