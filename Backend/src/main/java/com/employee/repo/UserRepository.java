package com.employee.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
}