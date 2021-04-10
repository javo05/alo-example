/**
 * 
 */
package mx.com.alo.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import mx.com.alo.example.model.Property;
import mx.com.alo.example.service.PropertyService;

/**
 * @author javo
 *
 */
@RestController
@RequestMapping("/api/v1/developer/{id}/property")
public class PropertyController {

	@Autowired
	private PropertyService propService;
	
	/**
	 * Creates a new Property owned by one Developer. In this version, it does not validate if it already exists
	 * @throws ResourceNotFoundException If developer does not exist
	 */
	@PostMapping("/")
	public ResponseEntity<Property> createProperty(@PathVariable(value = "id")Long developerId,
			@Valid @RequestBody Property property) throws ResourceNotFoundException {
		
		Property newProerty = propService.createProperty(developerId, property);
		
		return ResponseEntity.ok().body(newProerty);
	}
	
	/**
	 * Get all properties related to one developer
	 * @param developerId Developer Id
	 * @param pageable
	 * @return All properties related to one developer
	 * @throws ResourceNotFoundException If developer does not exist
	 */
	@GetMapping("/")
	public Page<Property> getAllDevProperties(@PathVariable(value = "id") Long developerId,
			Pageable pageable) throws ResourceNotFoundException {
		
		return propService.getAllDeveloperProperties(developerId, pageable);
		
	}
	
	
	/**
	 * Find specific property
	 * @param propId Property Id
	 * @return Property as Json
	 * @throws ResourceNotFoundException if not found
	 */
	@GetMapping("/{propertyId}")
    public ResponseEntity < Property > getDeveloperById(@PathVariable(value = "propertyId") Long propId)
    throws ResourceNotFoundException {
		
        Property property = propService.getProperty(propId);
        
        return ResponseEntity.ok().body(property);
    }
	

	
	/**
	 * Update specific information of Property: name, address, latitude, longitude
	 * @param propId
	 * @param prop
	 * @return Updated entity as Json
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/{propertyId}")
	public ResponseEntity<Property> updateProperty(@PathVariable(value = "propertyId") Long propId,
			@Valid @RequestBody Property prop) throws ResourceNotFoundException {
		
		return ResponseEntity.ok(propService.updateProperty(propId, prop));
		
	}
	
	/**
	 * Remove one specific property
	 * @param propId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/{propertyId}")
	public Map < String, Boolean > deleteProperty(@PathVariable(value = "propertyId") Long propId)
		    throws ResourceNotFoundException {
		
		Boolean deleted = propService.deleteProperty(propId);
		
		Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", deleted);
        
        return response; 
	}

}
