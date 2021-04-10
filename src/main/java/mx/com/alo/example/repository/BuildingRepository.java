package mx.com.alo.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.alo.example.model.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
	
	Page<Building> findByPropertyId(Long propId, Pageable pageable);
		
	Page<Building> findAll(Pageable pageable);

}
