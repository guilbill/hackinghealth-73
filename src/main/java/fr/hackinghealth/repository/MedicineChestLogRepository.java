package fr.hackinghealth.repository;

import fr.hackinghealth.domain.MedicineChestLog;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MedicineChestLog entity.
 */
public interface MedicineChestLogRepository extends JpaRepository<MedicineChestLog,Long> {

}
