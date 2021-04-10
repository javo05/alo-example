package mx.com.alo.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AloExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AloExampleApplication.class, args);
	}
	
	/**
	 * 
	 * ****Developers
	 * Create developer  	post -> /api/v1/developer			Done
	 * Get all developers  	get -> /api/v1/developer			Done
	 * Get developer	 	get -> /api/v1/developer/{id}		Done
	 * Update developer 	put -> /api/v1/developer/{id}		Done
	 * Delete developer  	delete -> /api/v1/developer/{id}	Done
	 * 
	 * 
	 * ****Properties
	 * 
	 * Add property				post -> /api/v1/developer/{id}/property				Done
	 * Get all dev's props		get -> /api/v1/developer/{id}/property				Done
	 * get property				get -> /api/v1/developer/{id}/property/{id}			Done
	 * Update property			put -> /api/v1/developer/{id}/property/{id}			Done
	 * Delete property			delete -> /api/v1/developer/{id}/property/{id}		Done
	 * 
	 * ****Buildings
	 * 
	 * Add building			post -> /api/v1/developer/{id}/property/{id}/building				Done
	 * Get all building		get -> /api/v1/developer/{id}/property/{id}/building				Done
	 * Get building			get -> /api/v1/developer/{id}/property/{id}/building/{id}			Done
	 * Update building		put -> /api/v1/developer/{id}/property/{id}/building/{id}			Done
	 * Update details		put -> /api/v1/developer/{id}/property/{id}/building/{id}/details	Done
	 * Delete building		delete -> /api/v1/developer/{id}/property/{id}/building/{id}		Done
	 * 
	 */
	
	/**
	 * 
	 * Missing:
	 * 
	 * Error handling
	 * Security: 
	 * 	Prevention of getting not owned information
	 * Fat Jar
	 * Volatile dB
	 * 
	 * 
	 * 
	 * select * from developer;

select * from developer_history;

select * from property;

select * from building;

select * from building_details;
	 * 
	 * 
	 */

}
