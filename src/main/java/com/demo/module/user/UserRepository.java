package com.demo.module.user;



import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {	
	
}
