package fr.hackinghealth.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.hackinghealth.domain.MedicineChest;
import fr.hackinghealth.service.MedicineChestService;
import fr.hackinghealth.web.rest.util.HeaderUtil;
import fr.hackinghealth.web.rest.dto.MedicineChestDTO;
import fr.hackinghealth.web.rest.mapper.MedicineChestMapper;
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
 * REST controller for managing MedicineChest.
 */
@RestController
@RequestMapping("/api")
public class MedicineChestResource {

    private final Logger log = LoggerFactory.getLogger(MedicineChestResource.class);
        
    @Inject
    private MedicineChestService medicineChestService;
    
    @Inject
    private MedicineChestMapper medicineChestMapper;
    
    /**
     * POST  /medicineChests -> Create a new medicineChest.
     */
    @RequestMapping(value = "/medicineChests",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineChestDTO> createMedicineChest(@Valid @RequestBody MedicineChestDTO medicineChestDTO) throws URISyntaxException {
        log.debug("REST request to save MedicineChest : {}", medicineChestDTO);
        if (medicineChestDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("medicineChest", "idexists", "A new medicineChest cannot already have an ID")).body(null);
        }
        MedicineChestDTO result = medicineChestService.save(medicineChestDTO);
        return ResponseEntity.created(new URI("/api/medicineChests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("medicineChest", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medicineChests -> Updates an existing medicineChest.
     */
    @RequestMapping(value = "/medicineChests",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineChestDTO> updateMedicineChest(@Valid @RequestBody MedicineChestDTO medicineChestDTO) throws URISyntaxException {
        log.debug("REST request to update MedicineChest : {}", medicineChestDTO);
        if (medicineChestDTO.getId() == null) {
            return createMedicineChest(medicineChestDTO);
        }
        MedicineChestDTO result = medicineChestService.save(medicineChestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("medicineChest", medicineChestDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medicineChests -> get all the medicineChests.
     */
    @RequestMapping(value = "/medicineChests",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<MedicineChestDTO> getAllMedicineChests() {
        log.debug("REST request to get all MedicineChests");
        return medicineChestService.findAll();
            }

    /**
     * GET  /medicineChests/:id -> get the "id" medicineChest.
     */
    @RequestMapping(value = "/medicineChests/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MedicineChestDTO> getMedicineChest(@PathVariable Long id) {
        log.debug("REST request to get MedicineChest : {}", id);
        MedicineChestDTO medicineChestDTO = medicineChestService.findOne(id);
        return Optional.ofNullable(medicineChestDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /medicineChests/:id -> delete the "id" medicineChest.
     */
    @RequestMapping(value = "/medicineChests/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMedicineChest(@PathVariable Long id) {
        log.debug("REST request to delete MedicineChest : {}", id);
        medicineChestService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("medicineChest", id.toString())).build();
    }
}
