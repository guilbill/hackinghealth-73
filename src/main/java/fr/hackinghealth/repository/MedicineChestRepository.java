package fr.hackinghealth.repository;

import fr.hackinghealth.domain.MedicineChest;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MedicineChest entity.
 */
public interface MedicineChestRepository extends JpaRepository<MedicineChest,Long> {

}
