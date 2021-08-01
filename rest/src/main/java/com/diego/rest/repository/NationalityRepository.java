package com.diego.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diego.rest.model.Nationality;  

@Repository
public interface NationalityRepository extends CrudRepository<Nationality, Integer>  
{  
}  