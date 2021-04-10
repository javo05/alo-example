package mx.com.alo.example.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.alo.example.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	Page<Property> findByDeveloperId(Long devId, Pageable pageable);
	
	Page<Property> findAll(Pageable pageable);

}
