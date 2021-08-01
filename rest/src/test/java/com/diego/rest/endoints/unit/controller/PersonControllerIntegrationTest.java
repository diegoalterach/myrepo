package com.diego.rest.endoints.unit.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.diego.rest.RestApplication;
import com.diego.rest.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

	private static final String PERSONS = "/persons";
	private static final String PERSONBYID = "/personbyid/";
	private static final String DELETEPERSON = "/deleteperson/";
	private static final String NEWPERSON = "/newperson";

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@BeforeEach
	public void deleteAll() {
		final HttpHeaders headers = new HttpHeaders();
		final HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(getRootUrl() + "/deleteall", HttpMethod.GET, entity, String.class);
	}

	@Test
	public void testGetAllEmployees() {
		final HttpHeaders headers = new HttpHeaders();
		final HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + PERSONS, HttpMethod.GET, entity,
				String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testSavePerson() {

		final Person fakePerson = new Person();
		fakePerson.setName("test");
		fakePerson.setBirthDate(new Date());
		fakePerson.setSex(0);
		fakePerson.setNationalityId(0);
		fakePerson.setBirthPlaceId(0);
		fakePerson.setEmail("email@email.com");
		fakePerson.setCpf("111.111.111-11");

		final ResponseEntity<Person> postForEntity = restTemplate.postForEntity(getRootUrl() + NEWPERSON, fakePerson,
				Person.class);

		assertNotNull(postForEntity.getBody());
		assertEquals("test", postForEntity.getBody().getName());
	}

	@Test
	public void testSavePerson_DuplicatedCPF() {

		final Person fakePerson = new Person();
		fakePerson.setName("test");
		fakePerson.setBirthDate(new Date());
		fakePerson.setSex(0);
		fakePerson.setNationalityId(0);
		fakePerson.setBirthPlaceId(0);
		fakePerson.setEmail("email@email.com");
		fakePerson.setCpf("111.111.111-11");

		ResponseEntity<Person> postForEntity = restTemplate.postForEntity(getRootUrl() + NEWPERSON, fakePerson,
				Person.class);

		final Person fakePerson2 = new Person();
		fakePerson2.setName("test");
		fakePerson2.setBirthDate(new Date());
		fakePerson2.setSex(0);
		fakePerson2.setNationalityId(0);
		fakePerson2.setBirthPlaceId(0);
		fakePerson2.setEmail("email@email1.com");
		fakePerson2.setCpf("111.111.111-11");

		final ResponseEntity<Person> postForEntity1 = restTemplate.postForEntity(getRootUrl() + NEWPERSON, fakePerson,
				Person.class);

		assertNotNull(postForEntity.getBody().getId());
		assertNull(postForEntity1.getBody().getId());
	}

	@Test
	public void testSavePerson_DuplicatedEmail() {

		final Person fakePerson = new Person();
		fakePerson.setName("test");
		fakePerson.setBirthDate(new Date());
		fakePerson.setSex(0);
		fakePerson.setNationalityId(0);
		fakePerson.setBirthPlaceId(0);
		fakePerson.setEmail("email@email.com");
		fakePerson.setCpf("111.111.111-22");

		final ResponseEntity<Person> postForEntity = restTemplate.postForEntity(getRootUrl() + NEWPERSON, fakePerson,
				Person.class);

		final Person fakePerson2 = new Person();
		fakePerson2.setName("test");
		fakePerson2.setBirthDate(new Date());
		fakePerson2.setSex(0);
		fakePerson2.setNationalityId(0);
		fakePerson2.setBirthPlaceId(0);
		fakePerson2.setEmail("email@email.com");
		fakePerson2.setCpf("111.111.111-11");

		final ResponseEntity<Person> postForEntity1 = restTemplate.postForEntity(getRootUrl() + NEWPERSON, fakePerson,
				Person.class);

		assertNotNull(postForEntity.getBody().getId());
		assertNull(postForEntity1.getBody().getId());
	}

	@Test
	public void testUpdatePerson() {

		final Person fakePerson = new Person();
		fakePerson.setName("test");
		fakePerson.setBirthDate(new Date());
		fakePerson.setSex(0);
		fakePerson.setNationalityId(0);
		fakePerson.setBirthPlaceId(0);
		fakePerson.setEmail("email@email.com");
		fakePerson.setCpf("111.111.111-11");

		final ResponseEntity<Person> postForEntity = restTemplate.postForEntity(getRootUrl() + NEWPERSON, fakePerson,
				Person.class);

		assertNotNull(postForEntity.getBody());
		assertEquals("test", postForEntity.getBody().getName());
		assertEquals("email@email.com", postForEntity.getBody().getEmail());

		final Person fakePersonForUpdate = restTemplate
				.getForObject(getRootUrl() + PERSONBYID + postForEntity.getBody().getId(), Person.class);

		fakePersonForUpdate.setName("testUpdated");
		fakePersonForUpdate.setEmail("email@com.com");

		final ResponseEntity<Person> updatedEntity = restTemplate.postForEntity(getRootUrl() + NEWPERSON,
				fakePersonForUpdate, Person.class);

		assertNotNull(updatedEntity.getBody());
		assertEquals("testUpdated", updatedEntity.getBody().getName());
		assertEquals("email@com.com", updatedEntity.getBody().getEmail());

	}

	@Test
	public void testDeletePerson() {
		final Person fakePerson2 = new Person();
		fakePerson2.setName("test");
		fakePerson2.setBirthDate(new Date());
		fakePerson2.setSex(0);
		fakePerson2.setNationalityId(0);
		fakePerson2.setBirthPlaceId(0);
		fakePerson2.setEmail("email@email.com");
		fakePerson2.setCpf("111.111.111-11");

		final ResponseEntity<Person> postForEntity1 = restTemplate.postForEntity(getRootUrl() + NEWPERSON, fakePerson2,
				Person.class);

		final HttpHeaders headers = new HttpHeaders();
		final HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(getRootUrl() + DELETEPERSON + postForEntity1.getBody().getId(), HttpMethod.GET, entity,
				String.class);

		final Person fakePerson1 = restTemplate
				.getForObject(getRootUrl() + PERSONBYID + postForEntity1.getBody().getId(), Person.class);
		assertNull(fakePerson1.getId());

	}

	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	@Test
	public void testSavePerson_ValidateCPFAndEmail() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		final Person fakePerson = new Person();
		fakePerson.setName("test");
		fakePerson.setBirthDate(new Date());
		fakePerson.setSex(0);
		fakePerson.setNationalityId(0);
		fakePerson.setBirthPlaceId(0);
		fakePerson.setEmail("email@email.com");
		fakePerson.setCpf("111.000.000-12");

		Set<ConstraintViolation<Person>> violations = validator.validate(fakePerson);

		assertTrue(violations.isEmpty());

		// invalid cpf
		fakePerson.setCpf("100.0.0");
		violations = validator.validate(fakePerson);
		assertFalse(violations.isEmpty());

		fakePerson.setCpf("111.000.000-12");
		// invalid email
		fakePerson.setEmail("email.com");
		violations = validator.validate(fakePerson);

		assertFalse(violations.isEmpty());

	}
}