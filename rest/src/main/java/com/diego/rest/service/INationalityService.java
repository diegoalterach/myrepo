package com.diego.rest.service;

import java.util.List;

import com.diego.rest.model.Nationality;

public interface INationalityService {
	List<Nationality> findAll();
	Nationality getById(int Id);
} 
