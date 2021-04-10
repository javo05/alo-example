/**
 * 
 */
package mx.com.alo.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mx.com.alo.example.exception.ResourceNotFoundException;
import mx.com.alo.example.model.Building;
import mx.com.alo.example.model.BuildingDetail;
import mx.com.alo.example.model.Property;
import mx.com.alo.example.repository.BuildingDetailRepository;
import mx.com.alo.example.repository.BuildingRepository;
import mx.com.alo.example.repository.PropertyRepository;
import mx.com.alo.example.view.vo.BuildingUnit;

/**
 * @author javo
 *
 */
@Component
public class BuildingService {

	private PropertyRepository propRepo;
	
	private BuildingRepository buildRepo;
	
	private BuildingDetailRepository detailRepo;

	/**
	 * @param buildRepo
	 */
	public BuildingService(PropertyRepository propRepo, BuildingRepository buildRepo, BuildingDetailRepository detail) {
		this.propRepo = propRepo;
		this.buildRepo = buildRepo;
		this.detailRepo = detail;
	}
	
	/**
	 * Create new building associated to an existing Property
	 * @param devId Property id
	 * @param building Building information
	 * @return Building entity
	 * @throws ResourceNotFoundException If developer is not found
	 */
	public Building createBuilding(Long devId, Building building) throws ResourceNotFoundException {
		
		Property prop = propRepo.findById(devId).orElseThrow(() -> new ResourceNotFoundException("Unable to create porperty, Property does not exist"));
		
		building.setProperty(prop);
		
		return buildRepo.save(building);
	}
	
	/**
	 * Find a specific building given its id. EXAMPLE OF NEXT ITERATION FOR SPLITTING ENTITIES FROM VIEW LAYER************************
	 * @param devId
	 * @return Building entity
	 * @throws ResourceNotFoundException if not found
	 */
	public BuildingUnit getBuilding(Long buildId) throws ResourceNotFoundException {
		
		Building building = buildRepo.findById(buildId).orElseThrow(() -> new ResourceNotFoundException("Unable to Find Building, it does not exist"));
		List<BuildingDetail> details = detailRepo.findAllById(List.of(buildId));
		
		BuildingUnit unit = new BuildingUnit(building, details);
		
		return unit;
	}
	
	/**
	 * Fetch as pages all buildings available
	 * @param pageable 
	 * @return Paginated properties
	 */
	public Page<Building> getAllBuildings(Pageable pageable){
		
		Page<Building> buildings = buildRepo.findAll(pageable);
		
		return buildings;
	}
	
	/**
	 * Fetch as pages all properties' buildings
	 * @param propId Property Id
	 * @param pageable
	 * @return All developer's properties
	 * @throws ResourceNotFoundException If developer does not exist
	 */
	public Page<Building> getAllBuildingsByPropertiId(Long propId, Pageable pageable) throws ResourceNotFoundException{
		
		propRepo.findById(propId).orElseThrow(() -> new ResourceNotFoundException("Unable to fetch porperties, Property does not exist"));
		
		return buildRepo.findByPropertyId(propId, pageable);
		
	}
	
	/**
	 * Update specific information of Building: name and type
	 * @param buildId Building id
	 * @param updated
	 * @return Building entity update
	 * @throws ResourceNotFoundException If building does not exist
	 */
	public Building updateBuilding(Long buildId, Building updated) throws ResourceNotFoundException {
		
		
		Building building = buildRepo.findById(buildId).orElseThrow(() -> new ResourceNotFoundException("Building not found for this id :: "));
		
		building.setName(updated.getName());
		building.setType(updated.getType());
		
		final Building updatedBuilding = buildRepo.save(building);
			
		return updatedBuilding;
	}
	
	public Building updateDetails(Long buildId, List<BuildingDetail> details) throws ResourceNotFoundException {
		
		Building building = buildRepo.findById(buildId).orElseThrow(() -> new ResourceNotFoundException("Building not found for this id :: "));
		
		details.forEach(detail -> {
				detail.setBuilding(building);
				detailRepo.save(detail);
			}
		);
		
		return building;
	}
	
	/**
	 * Delete specific building
	 * @param buildId Building ID
	 * @return true if deleted 
	 * @throws ResourceNotFoundException
	 */
	public Boolean deleteBuilding(Long buildId)
		    throws ResourceNotFoundException {
		
		        Building building = buildRepo.findById(buildId)
		            .orElseThrow(() -> new ResourceNotFoundException("Building not found for this id"));

		        buildRepo.delete(building);
		       
		        return Boolean.TRUE;
		    }

}
