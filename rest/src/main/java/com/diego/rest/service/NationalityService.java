package com.diego.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.rest.model.Nationality;
import com.diego.rest.repository.NationalityRepository;

@Service
public class NationalityService implements INationalityService {
	
	@Autowired
	NationalityRepository repository; 
	
	@Override
	public List<Nationality> findAll() {

		final List<Nationality> entities = new ArrayList<>();  
		repository.findAll().forEach(entity -> entities.add(entity));  
		return entities;
	}

	@Override
	public Nationality getById(int id) {
		return repository.findById(Integer.valueOf(id)).get();
	}
}