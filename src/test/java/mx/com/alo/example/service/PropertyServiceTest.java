package mx.com.alo.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import mx.com.alo.example.exception.ResourceNotFoundException;
import mx.com.alo.example.model.Developer;
import mx.com.alo.example.model.Property;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class PropertyServiceTest {

	@Autowired
	private DeveloperService devService;
	@Autowired
	private PropertyService propService;
	
	private static Developer devForTest;
	
	@BeforeAll
	public void insertDeveloper() {
		 devForTest =  devService.createDeveloper(new Developer("Dev Test for property", "Logo dev property", true));
		 assertThat(devForTest).isNotNull();
	}
	
	@Test
	@Order(1)
	public void createProperty() {
		
		try {
			Property newProperty = propService.createProperty(devForTest.getId(), new Property("Property for test's developers", "Test Address 1", "-123456", "678901.0"));
			
			assertThat(newProperty).isNotNull();
			assertThat(newProperty.getId()).isGreaterThan(0);
			assertThat(newProperty.getName()).isEqualTo("Property for test's developers");
			
		} catch (ResourceNotFoundException e) {
			
			assertThat(e).isNull();

		}
	}
	
	@Test
	@Order(2)
	public void createSecondProperty() {
		
		try {
			Property newProperty = propService.createProperty(devForTest.getId(), new Property("Property for test's developers 2", "Test Address 2", "-123456", "678901.0"));
			
			assertThat(newProperty).isNotNull();
			assertThat(newProperty.getId()).isGreaterThan(0);
			assertThat(newProperty.getName()).isEqualTo("Property for test's developers 2");
			
		} catch (ResourceNotFoundException e) {
			
			assertThat(e).isNull();

		}
	}
	
	@Test
	@Order(3)
	public void getAllPropertiesOfDeveloper() {
		
		Pageable pageable = PageRequest.of(0, 10);
		
		try {
			
			Page<Property> page = propService.getAllDeveloperProperties(devForTest.getId(), pageable);
			assertThat(page.isEmpty()).isEqualTo(false);
			assertThat(page.getContent().size()).isEqualTo(2);
			
		} catch (ResourceNotFoundException e) {
			
			assertThat(e).isNull();

		}
		
	}

}
