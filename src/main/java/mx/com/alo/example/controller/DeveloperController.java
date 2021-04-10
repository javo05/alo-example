/**
 * 
 */
package mx.com.alo.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.alo.example.exception.ResourceNotFoundException;
import mx.com.alo.example.model.Developer;
import mx.com.alo.example.service.DeveloperService;

/**
 * @author javo
 *
 */
@RestController
@RequestMapping("/api/v1/developer")
public class DeveloperController {

	@Autowired
	private DeveloperService devService;
	
	/**
	 * Creates a new Developer. In this version, it does not validate if it already exists
	 */
	@PostMapping("/")
	public Developer createDeveloper(@Valid @RequestBody Developer developer) {
		return devService.createDeveloper(developer);
	}
	
	/**
	 * Find specific developer
	 * @param devId
	 * @return Developer as Json
	 * @throws ResourceNotFoundException if not found
	 */
	@GetMapping("/{id}")
    public ResponseEntity < Developer > getDeveloperById(@PathVariable(value = "id") Long devId)
    throws ResourceNotFoundException {
		
        Developer developer = devService.getDeveloper(devId);
        
        return ResponseEntity.ok().body(developer);
    }
	
	/**
	 * Get all developers
	 * @return Array of developers
	 */
	@GetMapping("/")
	public ResponseEntity<List<Developer>> getAllDevelopers() {
		
		List<Developer> developers = devService.getAllDevelopers();
		
		return ResponseEntity.ok().body(developers);
		
	}

	
	/**
	 * Update specific information of Developer: logo, name, start date and end date
	 * @param developerId
	 * @param dev
	 * @return Updated entity as Json
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Developer> updateDeveloper(@PathVariable(value = "id") Long developerId,
			@Valid @RequestBody Developer dev) throws ResourceNotFoundException {
		
		return ResponseEntity.ok(devService.updateDeveloper(developerId, dev));
		
	}
	
	@DeleteMapping("/{id}")
	public Map < String, Boolean > deleteDeveloper(@PathVariable(value = "id") Long devId)
		    throws ResourceNotFoundException {
		
		Boolean deleted = devService.deleteDeveloper(devId);
		
		Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", deleted);
        
        return response; 
	}

}
