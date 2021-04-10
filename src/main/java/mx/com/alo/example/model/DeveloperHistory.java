/**
 * 
 */
package mx.com.alo.example.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "developer_history")
public class DeveloperHistory extends History {
	
	@ManyToOne(fetch= FetchType.LAZY, optional = false)
	@JoinColumn(name = "developer_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Developer developer;

	/**
	 * 
	 */
	public DeveloperHistory() {
	}

	

	/**
	 * @param property
	 * @param old_value
	 * @param new_value
	 * @param modified_at
	 */
	public DeveloperHistory(String property, String old_value, String new_value, Timestamp modified_at, Developer developer) {
		super(property, old_value, new_value, modified_at);
		this.developer = developer;
	}


}
