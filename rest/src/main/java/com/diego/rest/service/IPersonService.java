package com.diego.rest.service;

import java.util.List;

import com.diego.rest.model.Person;

public interface IPersonService {
	List<Person> findAll();
	Person saveOrUpdate(Person per);
	void deleteById(int id);
	Person getById(int Id);
	void deleteAll();

} 
