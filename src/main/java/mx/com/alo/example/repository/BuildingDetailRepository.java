/**
 * 
 */
package mx.com.alo.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.alo.example.model.BuildingDetail;

/**
 * @author javo
 *
 */
@Repository
public interface BuildingDetailRepository extends JpaRepository<BuildingDetail, Long> {


}
