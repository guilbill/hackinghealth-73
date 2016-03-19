package fr.hackinghealth.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.hackinghealth.domain.Medication;
import fr.hackinghealth.repository.MedicationRepository;
import fr.hackinghealth.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Medication.
 */
@RestController
@RequestMapping("/api")
public class MedicationResource {

    private final Logger log = LoggerFactory.getLogger(MedicationResource.class);
        
    @Inject
    private MedicationRepository medicationRepository;
    
    /**
     * POST  /medications -> Create a new medication.
     */
    @RequestMapping(value = "/medications",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Medication> createMedication(@Valid @RequestBody Medication medication) throws URISyntaxException {
        log.debug("REST request to save Medication : {}", medication);
        if (medication.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("medication", "idexists", "A new medication cannot already have an ID")).body(null);
        }
        Medication result = medicationRepository.save(medication);
        return ResponseEntity.created(new URI("/api/medications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("medication", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medications -> Updates an existing medication.
     */
    @RequestMapping(value = "/medications",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Medication> updateMedication(@Valid @RequestBody Medication medication) throws URISyntaxException {
        log.debug("REST request to update Medication : {}", medication);
        if (medication.getId() == null) {
            return createMedication(medication);
        }
        Medication result = medicationRepository.save(medication);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("medication", medication.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medications -> get all the medications.
     */
    @RequestMapping(value = "/medications",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Medication> getAllMedications() {
        log.debug("REST request to get all Medications");
        return medicationRepository.findAll();
            }

    /**
     * GET  /medications/:id -> get the "id" medication.
     */
    @RequestMapping(value = "/medications/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Medication> getMedication(@PathVariable Long id) {
        log.debug("REST request to get Medication : {}", id);
        Medication medication = medicationRepository.findOne(id);
        return Optional.ofNullable(medication)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /medications/:id -> delete the "id" medication.
     */
    @RequestMapping(value = "/medications/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        log.debug("REST request to delete Medication : {}", id);
        medicationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("medication", id.toString())).build();
    }
}
