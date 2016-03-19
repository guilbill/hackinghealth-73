package fr.hackinghealth.service.impl;

import fr.hackinghealth.service.MedicineChestLogService;
import fr.hackinghealth.domain.MedicineChestLog;
import fr.hackinghealth.repository.MedicineChestLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing MedicineChestLog.
 */
@Service
@Transactional
public class MedicineChestLogServiceImpl implements MedicineChestLogService{

    private final Logger log = LoggerFactory.getLogger(MedicineChestLogServiceImpl.class);
    
    @Inject
    private MedicineChestLogRepository medicineChestLogRepository;
    
    /**
     * Save a medicineChestLog.
     * @return the persisted entity
     */
    public MedicineChestLog save(MedicineChestLog medicineChestLog) {
        log.debug("Request to save MedicineChestLog : {}", medicineChestLog);
        MedicineChestLog result = medicineChestLogRepository.save(medicineChestLog);
        return result;
    }

    /**
     *  get all the medicineChestLogs.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<MedicineChestLog> findAll() {
        log.debug("Request to get all MedicineChestLogs");
        List<MedicineChestLog> result = medicineChestLogRepository.findAll();
        return result;
    }

    /**
     *  get one medicineChestLog by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public MedicineChestLog findOne(Long id) {
        log.debug("Request to get MedicineChestLog : {}", id);
        MedicineChestLog medicineChestLog = medicineChestLogRepository.findOne(id);
        return medicineChestLog;
    }

    /**
     *  delete the  medicineChestLog by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedicineChestLog : {}", id);
        medicineChestLogRepository.delete(id);
    }
}
