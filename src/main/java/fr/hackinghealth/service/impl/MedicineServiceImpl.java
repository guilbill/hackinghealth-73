package fr.hackinghealth.service.impl;

import fr.hackinghealth.service.MedicineService;
import fr.hackinghealth.domain.Medicine;
import fr.hackinghealth.repository.MedicineRepository;
import fr.hackinghealth.web.rest.dto.MedicineDTO;
import fr.hackinghealth.web.rest.mapper.MedicineMapper;
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
 * Service Implementation for managing Medicine.
 */
@Service
@Transactional
public class MedicineServiceImpl implements MedicineService{

    private final Logger log = LoggerFactory.getLogger(MedicineServiceImpl.class);
    
    @Inject
    private MedicineRepository medicineRepository;
    
    @Inject
    private MedicineMapper medicineMapper;
    
    /**
     * Save a medicine.
     * @return the persisted entity
     */
    public MedicineDTO save(MedicineDTO medicineDTO) {
        log.debug("Request to save Medicine : {}", medicineDTO);
        Medicine medicine = medicineMapper.medicineDTOToMedicine(medicineDTO);
        medicine = medicineRepository.save(medicine);
        MedicineDTO result = medicineMapper.medicineToMedicineDTO(medicine);
        return result;
    }

    /**
     *  get all the medicines.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<MedicineDTO> findAll() {
        log.debug("Request to get all Medicines");
        List<MedicineDTO> result = medicineRepository.findAll().stream()
            .map(medicineMapper::medicineToMedicineDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one medicine by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public MedicineDTO findOne(Long id) {
        log.debug("Request to get Medicine : {}", id);
        Medicine medicine = medicineRepository.findOne(id);
        MedicineDTO medicineDTO = medicineMapper.medicineToMedicineDTO(medicine);
        return medicineDTO;
    }

    /**
     *  delete the  medicine by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Medicine : {}", id);
        medicineRepository.delete(id);
    }
}
