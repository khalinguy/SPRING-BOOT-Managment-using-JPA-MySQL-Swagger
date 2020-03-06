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

import com.demo.entity.Commune;
import com.demo.module.commune.CommuneService;

//list,view,add,update,search,del


@RestController
@RequestMapping(value= "/commune")
public class CommuneController {

	@Autowired
	CommuneService communetService;

	//============= LIST ALL COMMUNES ===============
	@RequestMapping(value = "/allcommunes", method = RequestMethod.GET)
	public ResponseEntity<List<Commune>> getAllCommunes() {
		return new ResponseEntity<List<Commune>>(communetService.findAll(), HttpStatus.OK);
	}
	
	//============= SEARCH COMMUNES =================
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Commune> findByID(@PathVariable(name = "id") int id) {
		Commune commune = communetService.findById(id);
		return new ResponseEntity<Commune>(commune,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findbyname/{name}", method = RequestMethod.GET)
	public ResponseEntity<Commune> findByName(@RequestParam(name = "name") String name) {
		Commune district = communetService.findByName(name);
		return new ResponseEntity<Commune>(district,HttpStatus.OK);
	}
	
	//============= CREATE AND UPDATE COMMUNE ===========
	@RequestMapping(value = "/updateCommune/", method = RequestMethod.POST)
	public ResponseEntity<Commune> updateCommune(Commune commune){
		Commune update = communetService.updateCommune(commune);
		return new ResponseEntity<Commune>(update,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createCommune/", method = RequestMethod.POST)
	public ResponseEntity<Commune> createCommune(Commune commune){
		Commune create = communetService.createCommune(commune);
		return new ResponseEntity<Commune>(create,HttpStatus.OK);
	}

}
