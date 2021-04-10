/**
 * 
 */
package mx.com.alo.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mx.com.alo.example.exception.ResourceNotFoundException;
import mx.com.alo.example.model.Building;
import mx.com.alo.example.model.BuildingDetail;
import mx.com.alo.example.model.Developer;
import mx.com.alo.example.model.Property;

/**
 * @author javo
 *
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class BuildingServiceTest {
	
	@Autowired
	private DeveloperService devService;
	
	@Autowired
	private BuildingService buildService;
	
	@Autowired
	private PropertyService propService;
	
	private static Developer devForTest;
	
	private static Property propForTesst;
	
	@BeforeAll
	public void insertProperty() {
		 devForTest =  devService.createDeveloper(new Developer("Dev Test for property", "Logo dev property", true));
		 assertThat(devForTest).isNotNull();
		 
		 try {
			 
			propForTesst = propService.createProperty(devForTest.getId(), 
					 new Property("Property for  buildings test", "Address for buildings test", "-123456", "678900.1"));
			assertThat(propForTesst).isNotNull();
			
		} catch (ResourceNotFoundException e) {

			assertThat(e).isNull();

		}
	}

	/**
	 * 
	 */
	public BuildingServiceTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	@Order(1)
	public void createBuildingWithoutDetails() {
		
		try {
			Building building = buildService.createBuilding(propForTesst.getId(), 
					new Building("Test 1 Building", "Type test 1"));
			assertThat(building).isNotNull();
			assertThat(building.getId()).isGreaterThan(0);
			assertThat(building.getName()).isEqualTo("Test 1 Building");
			
		} catch (ResourceNotFoundException e) {

			assertThat(e).isNull();

		}
	}

	@Test
	@Order(2)
	public void createSecondBuildingWithoutDetails() {
		
		try {
			Building building = buildService.createBuilding(propForTesst.getId(), 
					new Building("Test 2 Building", "Type test 2"));
			assertThat(building).isNotNull();
			assertThat(building.getId()).isGreaterThan(1);
			assertThat(building.getName()).isEqualTo("Test 2 Building");
			
		} catch (ResourceNotFoundException e) {

			assertThat(e).isNull();

		}
	}
	
	@Test
	@Order(3)
	public void createThirdBuildingWithDetails() {
		
		try {
			Building building = buildService.createBuilding(propForTesst.getId(), 
					new Building("Test 3 Building", "Type test 3"));
			assertThat(building).isNotNull();
			assertThat(building.getId()).isGreaterThan(2);
			assertThat(building.getName()).isEqualTo("Test 3 Building");
			
			List<BuildingDetail> details = new ArrayList<>();
			details.add(new BuildingDetail("m2", "120"));
			details.add(new BuildingDetail("bedrooms", "3"));
			details.add(new BuildingDetail("price", "120000"));
			details.add(new BuildingDetail("currency", "MXN"));
			
			assertThat(buildService.updateDetails(building.getId(), details)).isNotNull();
			
		} catch (ResourceNotFoundException e) {

			assertThat(e).isNull();

		}
	}

}
