package fr.hackinghealth.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.hackinghealth.domain.Medicine;
import fr.hackinghealth.service.MedicineService;
import fr.hackinghealth.web.rest.util.HeaderUtil;
import fr.hackinghealth.web.rest.dto.MedicineDTO;
import fr.hackinghealth.web.rest.mapper.MedicineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Medicine.
 */
@RestController
@RequestMapping("/api")
public class MedicineResource {

    private final Logger log = LoggerFactory.getLogger(MedicineResource.class);
        
    @Inject
    private MedicineService medicineService;
    
    @Inject
    private MedicineMapper medicineMapper;
    
    /**
     * POST  /medicines -> Create a new medicine.
     */
    @RequestMapping(value = "/medicines",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineDTO> createMedicine(@Valid @RequestBody MedicineDTO medicineDTO) throws URISyntaxException {
        log.debug("REST request to save Medicine : {}", medicineDTO);
        if (medicineDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("medicine", "idexists", "A new medicine cannot already have an ID")).body(null);
        }
        MedicineDTO result = medicineService.save(medicineDTO);
        return ResponseEntity.created(new URI("/api/medicines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("medicine", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medicines -> Updates an existing medicine.
     */
    @RequestMapping(value = "/medicines",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineDTO> updateMedicine(@Valid @RequestBody MedicineDTO medicineDTO) throws URISyntaxException {
        log.debug("REST request to update Medicine : {}", medicineDTO);
        if (medicineDTO.getId() == null) {
            return createMedicine(medicineDTO);
        }
        MedicineDTO result = medicineService.save(medicineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("medicine", medicineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medicines -> get all the medicines.
     */
    @RequestMapping(value = "/medicines",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<MedicineDTO> getAllMedicines() {
        log.debug("REST request to get all Medicines");
        return medicineService.findAll();
            }

    /**
     * GET  /medicines/:id -> get the "id" medicine.
     */
    @RequestMapping(value = "/medicines/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineDTO> getMedicine(@PathVariable Long id) {
        log.debug("REST request to get Medicine : {}", id);
        MedicineDTO medicineDTO = medicineService.findOne(id);
        return Optional.ofNullable(medicineDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /medicines/:id -> delete the "id" medicine.
     */
    @RequestMapping(value = "/medicines/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        log.debug("REST request to delete Medicine : {}", id);
        medicineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("medicine", id.toString())).build();
    }
}
