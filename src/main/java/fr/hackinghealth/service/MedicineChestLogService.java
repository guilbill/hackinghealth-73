package fr.hackinghealth.service;

import fr.hackinghealth.domain.MedicineChestLog;

import java.util.List;

/**
 * Service Interface for managing MedicineChestLog.
 */
public interface MedicineChestLogService {

    /**
     * Save a medicineChestLog.
     * @return the persisted entity
     */
    public MedicineChestLog save(MedicineChestLog medicineChestLog);

    /**
     *  get all the medicineChestLogs.
     *  @return the list of entities
     */
    public List<MedicineChestLog> findAll();

    /**
     *  get the "id" medicineChestLog.
     *  @return the entity
     */
    public MedicineChestLog findOne(Long id);

    /**
     *  delete the "id" medicineChestLog.
     */
    public void delete(Long id);
}
