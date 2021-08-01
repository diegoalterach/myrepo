package com.diego.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.diego.rest.model.Place;
import com.diego.rest.service.IPlaceService;

@RestController
@CrossOrigin()
public class PlaceController {
	@Autowired
	private IPlaceService service;

	@GetMapping(value = "/places")
	public List<Place> getAll() {
		List<Place> entities = service.findAll();
		return entities;
	}
	
	@GetMapping("/placebyid/{placeid}")  
	public Place getById(@PathVariable("placeid") int id) {
		return service.getById(id);
	}

}