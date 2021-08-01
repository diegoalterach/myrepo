package com.diego.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diego.rest.model.Place;  

@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer>  
{  
}  