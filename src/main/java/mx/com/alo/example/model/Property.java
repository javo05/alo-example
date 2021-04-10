/**
 * 
 */
package mx.com.alo.example.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author javo
 *
 */
@Entity
@Table(name = "property")
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String addres;
	private String latitude;
	private String longitude;
	@ManyToOne(fetch= FetchType.LAZY, optional = false)
	@JoinColumn(name = "developer_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Developer developer;
	
	public Property() {
		
	}
	
	/**
	 * @param developerId Developer who is going to own the property
	 * @param name Public property name
	 * @param addres
	 * @param latitude
	 * @param longitude
	 */
	public Property(String name, String addres, String latitude, String longitude) {
		this.name = name;
		this.addres = addres;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the addres
	 */
	public String getAddres() {
		return addres;
	}


	/**
	 * @param addres the addres to set
	 */
	public void setAddres(String addres) {
		this.addres = addres;
	}


	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}


	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}	
	
	/**
	 * @return the developer
	 */
	public Developer getDeveloper() {
		return developer;
	}


	/**
	 * @param developer the developer to set
	 */
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	
	

}
