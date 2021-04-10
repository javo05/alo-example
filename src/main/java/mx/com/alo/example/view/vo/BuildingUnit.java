/**
 * 
 */
package mx.com.alo.example.view.vo;

import java.util.List;

import mx.com.alo.example.model.Building;
import mx.com.alo.example.model.BuildingDetail;

/**
 * @author javo
 *
 */
public class BuildingUnit {
	
	private Building building;
	
	private List<BuildingDetail> details;

	/**
	 * 
	 */
	public BuildingUnit() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param building
	 * @param details
	 */
	public BuildingUnit(Building building, List<BuildingDetail> details) {
		this.building = building;
		this.details = details;
	}

	/**
	 * @return the building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}

	/**
	 * @return the details
	 */
	public List<BuildingDetail> getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(List<BuildingDetail> details) {
		this.details = details;
	}
	
	

}
