package com.diego.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.diego.rest.model.Nationality;
import com.diego.rest.service.INationalityService;

@RestController
@CrossOrigin()
public class NationalityController {
	@Autowired
	private INationalityService service;

	@GetMapping(value = "/nationalities")
	public List<Nationality> getAll() {
		List<Nationality> entities = service.findAll();
		return entities;
	}
	
	@GetMapping("/nationalitybyid/{nationalityid}")  
	public Nationality getById(@PathVariable("nationalityid") int id) {
		return service.getById(id);
	}

}