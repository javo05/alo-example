/**
 * 
 */
package mx.com.alo.example.model;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author javo
 *
 */
@MappedSuperclass
public class History {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String property;
	private String old_value;
	private String new_value;
	private Timestamp modified_at;

	/**
	 * 
	 */
	public History() {
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param property
	 * @param old_value
	 * @param new_value
	 * @param modified_at
	 */
	public History(String property, String old_value, String new_value, Timestamp modified_at) {
		this.property = property;
		this.old_value = old_value;
		this.new_value = new_value;
		this.modified_at = modified_at;
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
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @return the old_value
	 */
	public String getOld_value() {
		return old_value;
	}

	/**
	 * @param old_value the old_value to set
	 */
	public void setOld_value(String old_value) {
		this.old_value = old_value;
	}

	/**
	 * @return the new_value
	 */
	public String getNew_value() {
		return new_value;
	}

	/**
	 * @param new_value the new_value to set
	 */
	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}

	/**
	 * @return the modified_at
	 */
	public Timestamp getModified_at() {
		return modified_at;
	}

	/**
	 * @param modified_at the modified_at to set
	 */
	public void setModified_at(Timestamp modified_at) {
		this.modified_at = modified_at;
	}
	
	

}
