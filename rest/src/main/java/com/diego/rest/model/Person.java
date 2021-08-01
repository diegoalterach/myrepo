package com.diego.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity  
@Table(name = "persons")
public class Person {

	@Id  
	@Column 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column  
	@NotNull
	private String name;
	@Column  
	private Integer sex;
	@Column  
	@NotNull
	private Date birthDate;
	@Column  
	private Integer nationalityId;
	@Column  
	private Integer birthPlaceId;
	@Column(unique=true)
	@NotNull
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;
	@Column(unique=true)
	@NotNull
	@Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
	private String cpf;

	public Person() {
		
	}
	
	public Person(@NotNull final String name, final Integer sex, final Date birthDate, final Integer nationalityId,
			final Integer birthPlaceId, final String email, @NotNull final String cpf) {
		super();
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
		this.nationalityId = nationalityId;
		this.birthPlaceId = birthPlaceId;
		this.email = email;
		this.cpf = cpf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(final Integer sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(final Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public Integer getBirthPlaceId() {
		return birthPlaceId;
	}

	public void setBirthPlaceId(final Integer birthPlaceId) {
		this.birthPlaceId = birthPlaceId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
