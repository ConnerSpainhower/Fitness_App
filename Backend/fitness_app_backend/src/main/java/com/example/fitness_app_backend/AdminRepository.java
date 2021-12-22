package com.example.fitness_app_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	 Admin findById(int id);

	 @Transactional
	 void deleteById(int id);
	    
}
