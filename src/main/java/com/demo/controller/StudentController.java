package com.demo.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.DTO.StudentDTO;
import com.demo.entity.Student;
import com.demo.module.student.StudentService;


@RestController
@RequestMapping(value= "/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	//============= LIST ALL STUDENTS ===============
	@RequestMapping(value = "/allstudents", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getAllUser() {
		return new ResponseEntity<List<StudentDTO>>(studentService.findAll(), HttpStatus.OK);
	}
	
	//============= SEARCH BY PAGES ===============
	
		@RequestMapping(value = "/bypages", method = RequestMethod.GET)
		public Page<Student> getStudentByPage(
				@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
	            @RequestParam(value = "size", required = false, defaultValue ="1") int size, 
	            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy)
				 {
			
			
			return studentService.findAllBySearch(page, size, sortBy);
		}
	//============= SEARCH STUDENTS =================
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> findByID(@PathVariable(name = "id") int id) {
		StudentDTO user = studentService.findById(id);
		return new ResponseEntity<StudentDTO>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findbyname/{name}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> findByName(@RequestParam(name = "name") String name) {
		StudentDTO student = studentService.findByName(name);
		return new ResponseEntity<StudentDTO>(student,HttpStatus.OK);
	}
	
	//============= CREATE STUDENTS ===========
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public ResponseEntity<Student> createStudent(StudentDTO student){
		Student create = studentService.createUser(student);
		return new ResponseEntity<Student>(create,HttpStatus.OK);
	}
	//============= UPDATE STUDENTS ===========
	@RequestMapping(value = "/update/", method = RequestMethod.POST)
	public ResponseEntity<Student> updateStudent(StudentDTO student){
		Student update = studentService.updateUser(student);
		return new ResponseEntity<Student>(update,HttpStatus.OK);
	}
	//============ DELETE STUDENTS =============
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteUserByID(@PathVariable(name = "id")int id){
		studentService.deleteUserById(id);
		return "Deleted successfully";
	}
	
}