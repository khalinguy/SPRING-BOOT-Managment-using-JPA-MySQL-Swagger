package com.demo.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.District;
import com.demo.module.district.DistrictService;

//list,view,add,update,search,del


@RestController
@RequestMapping(value= "/district")
public class DistrictController {

	@Autowired
	DistrictService districtService;

	//============= LIST ALL DISTRICTS ===============
	@RequestMapping(value = "/alldistricts", method = RequestMethod.GET)
	public ResponseEntity<List<District>> getAllDistricts() {
		return new ResponseEntity<List<District>>(districtService.findAll(), HttpStatus.OK);
	}
	
	//============= SEARCH DISTRICTS =================
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<District> findByID(@PathVariable(name = "id") int id) {
		District district = districtService.findById(id);
		return new ResponseEntity<District>(district,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findbyname/{name}", method = RequestMethod.GET)
	public ResponseEntity<District> findByName(@RequestParam(name = "name") String name) {
		District district = districtService.findByName(name);
		return new ResponseEntity<District>(district,HttpStatus.OK);
	}
	
	//============= CREATE OR UPDATE DISTRICT ===========
	@RequestMapping(value = "/updatedistrict/", method = RequestMethod.POST)
	public ResponseEntity<District> updateUser(District district){
		District update = districtService.updateDistrict(district);
		return new ResponseEntity<District>(update,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createdistrict/", method = RequestMethod.POST)
	public ResponseEntity<District> createDistrict (District district){
		District update = districtService.createDistrict(district);
		return new ResponseEntity<District>(update,HttpStatus.OK);
	}
	
}
