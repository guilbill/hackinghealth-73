package fr.hackinghealth.service;

import fr.hackinghealth.domain.Medicine;
import fr.hackinghealth.web.rest.dto.MedicineDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Medicine.
 */
public interface MedicineService {

    /**
     * Save a medicine.
     * @return the persisted entity
     */
    public MedicineDTO save(MedicineDTO medicineDTO);

    /**
     *  get all the medicines.
     *  @return the list of entities
     */
    public List<MedicineDTO> findAll();

    /**
     *  get the "id" medicine.
     *  @return the entity
     */
    public MedicineDTO findOne(Long id);

    /**
     *  delete the "id" medicine.
     */
    public void delete(Long id);
}
