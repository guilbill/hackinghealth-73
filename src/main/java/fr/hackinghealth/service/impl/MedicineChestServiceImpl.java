package fr.hackinghealth.service.impl;

import fr.hackinghealth.service.MedicineChestService;
import fr.hackinghealth.domain.MedicineChest;
import fr.hackinghealth.domain.MedicineChestLog;
import fr.hackinghealth.repository.MedicineChestLogRepository;
import fr.hackinghealth.repository.MedicineChestRepository;
import fr.hackinghealth.web.rest.dto.MedicineChestDTO;
import fr.hackinghealth.web.rest.mapper.MedicineChestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
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
    
    @Inject
    private MedicineChestLogRepository chestLogRepository;
    
    /**
     * Save a medicineChest.
     * @return the persisted entity
     */
    public MedicineChestDTO save(MedicineChestDTO medicineChestDTO) {
        log.debug("Request to save MedicineChest : {}", medicineChestDTO);
        MedicineChest medicineChest = medicineChestMapper.medicineChestDTOToMedicineChest(medicineChestDTO);
        
        logMedicineChest(medicineChestDTO, medicineChest);
        
        medicineChest = medicineChestRepository.save(medicineChest);
        MedicineChestDTO result = medicineChestMapper.medicineChestToMedicineChestDTO(medicineChest);
        return result;
    }

	private void logMedicineChest(MedicineChestDTO medicineChestDTO, MedicineChest medicineChest) {
		if (medicineChestDTO.getId() != null) {
        	 MedicineChestLog log = buildLog(medicineChest);
             chestLogRepository.save(log);
        }
	}

	private MedicineChestLog buildLog(MedicineChest medicineChest) {
		MedicineChestLog log = new MedicineChestLog();
        log.setMedicineChest(medicineChest);
        log.setOpen(medicineChest.getOpen());
        log.setDate(ZonedDateTime.now());
		return log;
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
