package com.diego.rest.service;

import java.util.List;

import com.diego.rest.model.Place;

public interface IPlaceService {
	List<Place> findAll();
	Place getById(int Id);
} 
