package com.diego.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.rest.model.Place;
import com.diego.rest.repository.PlaceRepository;

@Service
public class PlaceService implements IPlaceService {
	
	@Autowired
	PlaceRepository repository; 
	
	@Override
	public List<Place> findAll() {

		final List<Place> entities = new ArrayList<>();  
		repository.findAll().forEach(entity -> entities.add(entity));  
		return entities;
	}

	@Override
	public Place getById(int id) {
		return repository.findById(Integer.valueOf(id)).get();
	}
}