package com.diego.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.rest.model.Person;
import com.diego.rest.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	PersonRepository personRepository; 
	
	@Override
	public List<Person> findAll() {

		final List<Person> persons = new ArrayList<Person>();  
		personRepository.findAll().forEach(person -> persons.add(person));  
		return persons;
	}

	@Override
	public Person saveOrUpdate(Person per) {
		return personRepository.save(per);
	}

	@Override
	public void deleteById(int id) {
		personRepository.deleteById(id);
	}

	@Override
	public Person getById(int id) {
		return personRepository.findById(Integer.valueOf(id)).get();
	}

	@Override
	public void deleteAll() {
		personRepository.deleteAll();

	}

}