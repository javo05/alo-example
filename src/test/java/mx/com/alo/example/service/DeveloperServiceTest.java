/**
 * 
 */
package mx.com.alo.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mx.com.alo.example.exception.ResourceNotFoundException;
import mx.com.alo.example.model.Developer;

/**
 * @author javo
 *
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class DeveloperServiceTest {

	@Autowired
	private DeveloperService devService;
	
	
	@Test
	public void createDeveloperSuccess() {
		
		Developer newDev = new Developer("Dev Test 1", "Logo 1", true);
		
		Developer savedDeveloper = devService.createDeveloper(newDev);
		
		assertThat(savedDeveloper.getId()).isNotNull();
		
	}
	
	@Test
	public void getDeveloperSuccess() {
		
		Developer newDev =  devService.createDeveloper(new Developer("Dev Test 2", "Logo 2", true));
		
		Long devId = newDev.getId();
		
		try {
			
			Developer foundDev = devService.getDeveloper(devId);
			assertThat(foundDev).isNotNull();
			assertThat(foundDev.getId()).isNotNull();
			
		} catch (ResourceNotFoundException e) {

			assertThat(e).isNull();

		}
	}
	
	@Test
	public void getDeveloperFail() {
		
		Developer newDev =  devService.createDeveloper(new Developer("Dev Test 3", "Logo 3", true));
		assertThat(newDev).isNotNull();
		
		try {
			
			Developer foundDev = devService.getDeveloper(0L);
			assertThat(foundDev).isNull();
			assertThat(foundDev.getId()).isNull();
			
		} catch (ResourceNotFoundException e) {

			assertThat(e).isNotNull();

		}
	}
	
	@Test
	public void disableDeveloperSusbscription() {
		
		Developer newDev =  devService.createDeveloper(new Developer("Dev Test 4", "Logo 4", true));
		assertThat(newDev).isNotNull();
		
		newDev.setEnabled(false);
		
		try {
			Developer updatedDev = devService.updateDeveloper(newDev.getId(), newDev);
			
			assertThat(updatedDev.getId()).isEqualTo(newDev.getId());
			assertThat(updatedDev.getEnabled()).isEqualTo(false);
			
		} catch (ResourceNotFoundException e) {

			assertThat(e).isNull();

		}
		
	}

}
