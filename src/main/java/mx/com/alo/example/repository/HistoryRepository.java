/**
 * 
 */
package mx.com.alo.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.alo.example.model.History;

/**
 * @author javo
 *
 */
@Repository
public interface HistoryRepository<T extends History> extends JpaRepository<T, Long> {

}
