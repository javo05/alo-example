/**
 * 
 */
package mx.com.alo.example.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.com.alo.example.exception.ResourceNotFoundException;
import mx.com.alo.example.model.Developer;
import mx.com.alo.example.model.DeveloperHistory;
import mx.com.alo.example.repository.DeveloperRepository;
import mx.com.alo.example.repository.HistoryRepository;

/**
 * @author javo
 *
 */
@Component
public class DeveloperService {

	private DeveloperRepository devRepo;
	
	private HistoryRepository<DeveloperHistory> historyRepo;

	/**
	 * @param devRepo
	 */
	public DeveloperService(DeveloperRepository devRepo, HistoryRepository<DeveloperHistory> historyRepo) {
		this.devRepo = devRepo;
		this.historyRepo = historyRepo;
	}
	
	public Developer createDeveloper(Developer developer) {
		
		Developer newDeveloper = devRepo.save(developer);
		DeveloperHistory history = new DeveloperHistory("enabled", "false", "true", new Timestamp(Calendar.getInstance().getTime().getTime()), newDeveloper);
		
		historyRepo.save(history);
		
		return newDeveloper;
	}
	
	/**
	 * Find a specific developer given its id
	 * @param devId
	 * @return Developer entity
	 * @throws ResourceNotFoundException if not found
	 */
	public Developer getDeveloper(Long devId) throws ResourceNotFoundException {
		
		Developer developer = devRepo.findById(devId).orElseThrow(() -> new ResourceNotFoundException("Developer not found for this id :: "));
		
		return developer;
	}
	
	public List<Developer> getAllDevelopers(){
		
		List<Developer> developers = devRepo.findAll();
		
		return developers;
	}
	
	/**
	 * Update specific information of Developer: logo, name, start date and end date
	 * @param developerId
	 * @param dev
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Developer updateDeveloper(Long developerId,Developer dev) throws ResourceNotFoundException {
		
			Developer developer = devRepo.findById(developerId).orElseThrow(() -> new ResourceNotFoundException("Developer not found for this id :: "));
			
			this.saveHistory(developer, dev);
			
			developer.setLogo(dev.getLogo());
			developer.setName(dev.getName());
			developer.setEnabled(dev.getEnabled());
			
			final Developer updatedDeveloper = devRepo.save(developer);
			
			
			return updatedDeveloper;
	}
	
	/**
	 * Delete specific developer
	 * @param devId Developer ID
	 * @return true if deleted 
	 * @throws ResourceNotFoundException
	 */
	public Boolean deleteDeveloper(Long devId)
		    throws ResourceNotFoundException {
		        Developer developer = devRepo.findById(devId)
		            .orElseThrow(() -> new ResourceNotFoundException("Developer not found for this id"));

		        devRepo.delete(developer);
		       
		        return Boolean.TRUE;
		    }
	
	private void saveHistory(Developer saved, Developer modified) {
		
		List<DeveloperHistory> elements = getHistoryElements(saved, modified);
		
		if (elements.size() > 0) {
			historyRepo.saveAll(elements);
		}
	}

	private List<DeveloperHistory> getHistoryElements(Developer saved, Developer modified) {
		
		List<DeveloperHistory> elements = new ArrayList<>();
		
		if (saved.getEnabled() != modified.getEnabled()) {
			elements.add(new DeveloperHistory("enabled", saved.getEnabled().toString(), modified.getEnabled().toString(), 
					new Timestamp(Calendar.getInstance().getTime().getTime()), saved));
		}
		if (!saved.getLogo().equals(modified.getLogo())) {
			elements.add(new DeveloperHistory("logo", saved.getLogo(), modified.getLogo(), 
					new Timestamp(Calendar.getInstance().getTime().getTime()), saved));
		}
		if (!saved.getName().equals(modified.getName())) {
			elements.add(new DeveloperHistory("name", saved.getName(), modified.getName(), 
					new Timestamp(Calendar.getInstance().getTime().getTime()), saved));
		}
		
		return elements;
	}

}
