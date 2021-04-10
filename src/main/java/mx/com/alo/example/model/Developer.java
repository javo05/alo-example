/**
 * 
 */
package mx.com.alo.example.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author javo
 * Developer abstraction to send data to DB
 */
@Entity
@Table(name = "developer")
public class Developer {
	
	private long id;
	private String name;
	private String logo;
	private Boolean enabled;
	
	public Developer() {
		
	}

	
	
	/**
	 * @param name
	 * @param logo
	 * @param enabled
	 */
	public Developer(String name, String logo, Boolean enabled) {
		this.name = name;
		this.logo = logo;
		this.enabled = enabled;
	}



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}



	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}



	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	

}
