/**
 * 
 */
package mx.com.alo.example.controller;

import java.util.HashMap;
import java.util.List;
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
import mx.com.alo.example.model.Building;
import mx.com.alo.example.model.BuildingDetail;
import mx.com.alo.example.service.BuildingService;
import mx.com.alo.example.view.vo.BuildingUnit;

/**
 * @author javo
 *
 */
@RestController
@RequestMapping("/api/v1/developer/{id}/property/{propertyId}/building")
public class BuildingController {

	@Autowired
	private BuildingService buildService;
	
	/**
	 * Creates a new Building owned by one Property. In this version, it does not validate if it already exists
	 * @throws ResourceNotFoundException If developer does not exist
	 */
	@PostMapping("/")
	public ResponseEntity<Building> createBuilding(@PathVariable(value = "propertyId")Long propertyId,
			@Valid @RequestBody Building property) throws ResourceNotFoundException {
		
		Building newProerty = buildService.createBuilding(propertyId, property);
		
		return ResponseEntity.ok().body(newProerty);
	}
	
	/**
	 * Get all buildings related to one property
	 * @param propId Property Id
	 * @param pageable
	 * @return All properties related to one developer
	 * @throws ResourceNotFoundException If developer does not exist
	 */
	@GetMapping("/")
	public Page<Building> getAllPropertyBuildings(@PathVariable(value = "propertyId") Long propId,
			Pageable pageable) throws ResourceNotFoundException {
		
		return buildService.getAllBuildingsByPropertiId(propId, pageable);
		
	}
	
	
	/**
	 * Find specific building
	 * @param buildId Building Id
	 * @return Building as Json
	 * @throws ResourceNotFoundException if not found
	 */
	@GetMapping("/{buildingId}")
    public ResponseEntity < BuildingUnit > getBuildingById(@PathVariable(value = "buildingId") Long buildId)
    throws ResourceNotFoundException {
		
        BuildingUnit unit = buildService.getBuilding(buildId);
        
        return ResponseEntity.ok().body(unit);
    }
	

	/**
	 * Update specific information of Building: name and type
	 * @param buildId
	 * @param build
	 * @return Updated entity as Json
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/{buildingId}")
	public ResponseEntity<Building> updateBuilding(@PathVariable(value = "buildingId") Long buildId,
			@Valid @RequestBody Building build) throws ResourceNotFoundException {
		
		return ResponseEntity.ok().body(buildService.updateBuilding(buildId, build));
		
	}
	
	/**
	 * Update building properties less name and type
	 * @param buildId
	 * @param details
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/{buildingId}/details")
	public ResponseEntity<Building> updateDetails(@PathVariable(value = "buildingId") Long buildId,
			@Valid @RequestBody List<BuildingDetail> details) throws ResourceNotFoundException{
		
		return ResponseEntity.ok().body(buildService.updateDetails(buildId, details));
		
	}
	
	
	/**
	 * Remove one specific building
	 * @param buildId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/{buildingId}")
	public Map < String, Boolean > deleteBuilding(@PathVariable(value = "buildingId") Long buildId)
		    throws ResourceNotFoundException {
		
		Boolean deleted = buildService.deleteBuilding(buildId);
		
		Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", deleted);
        
        return response; 
	}

}
