package fr.hackinghealth.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.hackinghealth.domain.MedicineChestLog;
import fr.hackinghealth.service.MedicineChestLogService;
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
 * REST controller for managing MedicineChestLog.
 */
@RestController
@RequestMapping("/api")
public class MedicineChestLogResource {

    private final Logger log = LoggerFactory.getLogger(MedicineChestLogResource.class);
        
    @Inject
    private MedicineChestLogService medicineChestLogService;
    
    /**
     * POST  /medicineChestLogs -> Create a new medicineChestLog.
     */
    @RequestMapping(value = "/medicineChestLogs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineChestLog> createMedicineChestLog(@Valid @RequestBody MedicineChestLog medicineChestLog) throws URISyntaxException {
        log.debug("REST request to save MedicineChestLog : {}", medicineChestLog);
        if (medicineChestLog.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("medicineChestLog", "idexists", "A new medicineChestLog cannot already have an ID")).body(null);
        }
        MedicineChestLog result = medicineChestLogService.save(medicineChestLog);
        return ResponseEntity.created(new URI("/api/medicineChestLogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("medicineChestLog", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medicineChestLogs -> Updates an existing medicineChestLog.
     */
    @RequestMapping(value = "/medicineChestLogs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineChestLog> updateMedicineChestLog(@Valid @RequestBody MedicineChestLog medicineChestLog) throws URISyntaxException {
        log.debug("REST request to update MedicineChestLog : {}", medicineChestLog);
        if (medicineChestLog.getId() == null) {
            return createMedicineChestLog(medicineChestLog);
        }
        MedicineChestLog result = medicineChestLogService.save(medicineChestLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("medicineChestLog", medicineChestLog.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medicineChestLogs -> get all the medicineChestLogs.
     */
    @RequestMapping(value = "/medicineChestLogs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<MedicineChestLog> getAllMedicineChestLogs() {
        log.debug("REST request to get all MedicineChestLogs");
        return medicineChestLogService.findAll();
            }

    /**
     * GET  /medicineChestLogs/:id -> get the "id" medicineChestLog.
     */
    @RequestMapping(value = "/medicineChestLogs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineChestLog> getMedicineChestLog(@PathVariable Long id) {
        log.debug("REST request to get MedicineChestLog : {}", id);
        MedicineChestLog medicineChestLog = medicineChestLogService.findOne(id);
        return Optional.ofNullable(medicineChestLog)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /medicineChestLogs/:id -> delete the "id" medicineChestLog.
     */
    @RequestMapping(value = "/medicineChestLogs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMedicineChestLog(@PathVariable Long id) {
        log.debug("REST request to delete MedicineChestLog : {}", id);
        medicineChestLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("medicineChestLog", id.toString())).build();
    }
}
