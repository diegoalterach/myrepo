package com.diego.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity  
@Table(name = "nationalities")
public class Nationality {

	@Id  
	@Column 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column  
	@NotNull
	private String name;

	Nationality(){
		
	}

	public Nationality(Integer id, @NotNull String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
