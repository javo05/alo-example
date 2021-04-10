/**
 * 
 */
package mx.com.alo.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mx.com.alo.example.exception.ResourceNotFoundException;
import mx.com.alo.example.model.Developer;
import mx.com.alo.example.model.Property;
import mx.com.alo.example.repository.DeveloperRepository;
import mx.com.alo.example.repository.PropertyRepository;

/**
 * @author javo
 *
 */
@Component
public class PropertyService {

	private PropertyRepository propRepo;
	
	private DeveloperRepository devRepo;

	/**
	 * @param devRepo
	 */
	public PropertyService(PropertyRepository propRepo, DeveloperRepository devRepo) {
		this.propRepo = propRepo;
		this.devRepo = devRepo;
	}
	
	/**
	 * Create new property associated to an existing Developer
	 * @param devId Developer id
	 * @param property Property information
	 * @return Property entity
	 * @throws ResourceNotFoundException If developer is not found
	 */
	public Property createProperty(Long devId, Property property) throws ResourceNotFoundException {
		
		Developer dev = devRepo.findById(devId).orElseThrow(() -> new ResourceNotFoundException("Unable to create porperty, Developer does not exist"));
		
		property.setDeveloper(dev);
		
		return propRepo.save(property);
	}
	
	/**
	 * Find a specific property given its id
	 * @param devId
	 * @return Property entity
	 * @throws ResourceNotFoundException if not found
	 */
	public Property getProperty(Long propId) throws ResourceNotFoundException {
		
		Property property = propRepo.findById(propId).orElseThrow(() -> new ResourceNotFoundException("Property not found for this id :: "));
		
		return property;
	}
	
	/**
	 * Fetch as pages all properties available
	 * @param pageable 
	 * @return Paginated properties
	 */
	public Page<Property> getAllProperties(Pageable pageable){
		
		Page<Property> developers = propRepo.findAll(pageable);
		
		return developers;
	}
	
	/**
	 * Fetch as pages all developer's properties
	 * @param devId Developer Id
	 * @param pageable
	 * @return All developer's properties
	 * @throws ResourceNotFoundException If developer does not exist
	 */
	public Page<Property> getAllDeveloperProperties(Long devId, Pageable pageable) throws ResourceNotFoundException{
		
		devRepo.findById(devId).orElseThrow(() -> new ResourceNotFoundException("Unable to fetch porperties, Developer does not exist"));
		
		return propRepo.findByDeveloperId(devId, pageable);
		
	}
	
	/**
	 * Update specific information of Property: name, address, latitude, longitude
	 * @param propId Property id
	 * @param prop
	 * @return Property entity update
	 * @throws ResourceNotFoundException If property does not exist
	 */
	public Property updateProperty(Long propId,Property prop) throws ResourceNotFoundException {
		
		
		Property property = propRepo.findById(propId).orElseThrow(() -> new ResourceNotFoundException("Property not found for this id :: "));
		
		property.setName(prop.getName());
		property.setAddres(prop.getAddres());
		property.setLatitude(prop.getLatitude());
		property.setLongitude(prop.getLongitude());
		
		final Property updatedDeveloper = propRepo.save(property);
			
		return updatedDeveloper;
	}
	
	/**
	 * Delete specific property
	 * @param devId Property ID
	 * @return true if deleted 
	 * @throws ResourceNotFoundException
	 */
	public Boolean deleteProperty(Long devId)
		    throws ResourceNotFoundException {
		        Property property = propRepo.findById(devId)
		            .orElseThrow(() -> new ResourceNotFoundException("Property not found for this id"));

		        propRepo.delete(property);
		       
		        return Boolean.TRUE;
		    }

}
