package fr.hackinghealth.service;

import fr.hackinghealth.domain.MedicineChest;
import fr.hackinghealth.web.rest.dto.MedicineChestDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing MedicineChest.
 */
public interface MedicineChestService {

    /**
     * Save a medicineChest.
     * @return the persisted entity
     */
    public MedicineChestDTO save(MedicineChestDTO medicineChestDTO);

    /**
     *  get all the medicineChests.
     *  @return the list of entities
     */
    public List<MedicineChestDTO> findAll();

    /**
     *  get the "id" medicineChest.
     *  @return the entity
     */
    public MedicineChestDTO findOne(Long id);

    /**
     *  delete the "id" medicineChest.
     */
    public void delete(Long id);
}
