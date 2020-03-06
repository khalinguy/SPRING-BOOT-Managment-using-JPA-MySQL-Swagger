package com.demo.module.student;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
		
}
