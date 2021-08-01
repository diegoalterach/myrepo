package com.diego.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diego.rest.model.Person;
import com.diego.rest.service.IPersonService;

@RestController
@CrossOrigin()
public class PersonController {
	@Autowired
	private IPersonService service;

	@GetMapping(value = "/persons")
	public List<Person> getAll() {
		List<Person> persons = service.findAll();
		return persons;
	}
	
	@GetMapping("/personbyid/{personid}")  
	public Person getById(@PathVariable("personid") int id) {
		return service.getById(id);
	}
		
	@PostMapping("/newperson")
    @ResponseBody
	public Person createPerson(@RequestBody Person person) {
		return service.saveOrUpdate(person);
	}
	
	@GetMapping("/deleteperson/{personid}")  
	public void getDeleteById(@PathVariable("personid") int id) {
		service.deleteById(id);
	}
	@GetMapping("/deleteall")  
	public void getDeleteAll() {
		service.deleteAll();
	}
}