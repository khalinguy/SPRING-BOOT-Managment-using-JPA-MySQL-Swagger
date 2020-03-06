package com.demo.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.DTO.UserDTO;
import com.demo.entity.User;
import com.demo.module.user.UserService;

//list,view,add,update,search,del


@RestController
@RequestMapping(value= "/user")
public class UserController {

	@Autowired
	UserService userService;

	//============= LIST ALL USERS ===============
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAllUser() {
		return new ResponseEntity<List<UserDTO>>(userService.findAll(), HttpStatus.OK);
	}
	
	//============= SEARCH BY PAGES ===============
	
	@RequestMapping(value = "/bypages", method = RequestMethod.GET)
	public Page<User> getUserByPage(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
            @RequestParam(value = "size", required = false, defaultValue ="1") int size, 
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy)
			 {
		
		
		return userService.findAllBySearch(page, size, sortBy);
	}
	
	//============= SEARCH USERS =================
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findByID(@PathVariable(name = "id") int id) {
		UserDTO user = userService.findById(id);
		return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findbyname/{name}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findByName(@RequestParam(name = "name") String name) {
		UserDTO user = userService.findByName(name);
		return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
	}
	
	//============= CREATE USERS ===========
	@RequestMapping(value = "/create/{user}", method = RequestMethod.POST,  consumes = {"application/json"})
	public ResponseEntity<User> createUser(@RequestBody UserDTO user){
		User create = userService.createUser(user);
		return new ResponseEntity<User>(create,HttpStatus.OK);
	}
	//============= UPDATE STUDENTS ===========
	@RequestMapping(value = "/update/", method = RequestMethod.POST)
	public ResponseEntity<User> updateUser(UserDTO user){
		User update = userService.updateUser(user);
		return new ResponseEntity<User>(update,HttpStatus.OK);
	}
	
	//============ DELETE USERS =============
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteUserByID(@PathVariable(name = "id")int id){
		userService.deleteUserById(id);
		return "Deleted successfully";
	}
	
}