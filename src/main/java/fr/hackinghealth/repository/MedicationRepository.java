package fr.hackinghealth.repository;

import fr.hackinghealth.domain.Medication;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Medication entity.
 */
public interface MedicationRepository extends JpaRepository<Medication,Long> {

}
