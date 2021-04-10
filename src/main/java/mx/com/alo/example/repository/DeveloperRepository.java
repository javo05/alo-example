/**
 * 
 */
package mx.com.alo.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.alo.example.model.Developer;

/**
 * @author javo
 *
 */
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
