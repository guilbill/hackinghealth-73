package fr.hackinghealth.repository;

import fr.hackinghealth.domain.Medicine;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Medicine entity.
 */
public interface MedicineRepository extends JpaRepository<Medicine,Long> {

}
