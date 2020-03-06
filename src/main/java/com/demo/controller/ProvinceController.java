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

import com.demo.entity.Province;
import com.demo.module.province.ProvinceService;

//list,view,add,update,search,del


@RestController
@RequestMapping(value= "/province")
public class ProvinceController {

	@Autowired
	ProvinceService provinceService;

	//============= LIST ALL PROVINCE ===============
	@RequestMapping(value = "/allprovinces", method = RequestMethod.GET)
	public ResponseEntity<List<Province>> getAllProvinces() {
		return new ResponseEntity<List<Province>>(provinceService.findAll(), HttpStatus.OK);
	}
	
	//============= SEARCH PROVINCE =================
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Province> findByID(@PathVariable(name = "id") int id) {
		Province province = provinceService.findById(id);
		return new ResponseEntity<Province>(province,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findbyname/{name}", method = RequestMethod.GET)
	public ResponseEntity<Province> findByName(@RequestParam(name = "name") String name) {
		Province province = provinceService.findByName(name);
		return new ResponseEntity<Province>(province,HttpStatus.OK);
	}
	//============= CREATE PROVINCE ===========
	@RequestMapping(value = "/createprovince/", method = RequestMethod.POST)
	public ResponseEntity<Province> createProvince(Province province){
		Province create = provinceService.createProvince(province);
		return new ResponseEntity<Province>(create,HttpStatus.OK);
	}
	//============= UPDATE PROVINCE ============
	@RequestMapping(value = "/updateprovince/", method = RequestMethod.POST)
	public ResponseEntity<Province> updateProvince(Province province){
		Province update = provinceService.updateProvince(province);
		return new ResponseEntity<Province>(update,HttpStatus.OK);
	}
}
