package com.diego.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diego.rest.model.Person;  

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>  
{  
}  