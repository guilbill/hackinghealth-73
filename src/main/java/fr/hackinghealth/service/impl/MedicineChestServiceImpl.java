package fr.hackinghealth.service.impl;

import fr.hackinghealth.service.MedicineChestService;
import fr.hackinghealth.domain.MedicineChest;
import fr.hackinghealth.repository.MedicineChestRepository;
import fr.hackinghealth.web.rest.dto.MedicineChestDTO;
import fr.hackinghealth.web.rest.mapper.MedicineChestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MedicineChest.
 */
@Service
@Transactional
public class MedicineChestServiceImpl implements MedicineChestService{

    private final Logger log = LoggerFactory.getLogger(MedicineChestServiceImpl.class);
    
    @Inject
    private MedicineChestRepository medicineChestRepository;
    
    @Inject
    private MedicineChestMapper medicineChestMapper;
    
    /**
     * Save a medicineChest.
     * @return the persisted entity
     */
    public MedicineChestDTO save(MedicineChestDTO medicineChestDTO) {
        log.debug("Request to save MedicineChest : {}", medicineChestDTO);
        MedicineChest medicineChest = medicineChestMapper.medicineChestDTOToMedicineChest(medicineChestDTO);
        medicineChest = medicineChestRepository.save(medicineChest);
        MedicineChestDTO result = medicineChestMapper.medicineChestToMedicineChestDTO(medicineChest);
        return result;
    }

    /**
     *  get all the medicineChests.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<MedicineChestDTO> findAll() {
        log.debug("Request to get all MedicineChests");
        List<MedicineChestDTO> result = medicineChestRepository.findAll().stream()
            .map(medicineChestMapper::medicineChestToMedicineChestDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one medicineChest by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public MedicineChestDTO findOne(Long id) {
        log.debug("Request to get MedicineChest : {}", id);
        MedicineChest medicineChest = medicineChestRepository.findOne(id);
        MedicineChestDTO medicineChestDTO = medicineChestMapper.medicineChestToMedicineChestDTO(medicineChest);
        return medicineChestDTO;
    }

    /**
     *  delete the  medicineChest by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicineChest : {}", id);
        medicineChestRepository.delete(id);
    }
}
