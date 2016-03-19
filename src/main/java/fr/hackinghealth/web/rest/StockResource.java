package fr.hackinghealth.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.hackinghealth.domain.Stock;
import fr.hackinghealth.service.StockService;
import fr.hackinghealth.web.rest.util.HeaderUtil;
import fr.hackinghealth.web.rest.dto.StockDTO;
import fr.hackinghealth.web.rest.mapper.StockMapper;
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
 * REST controller for managing Stock.
 */
@RestController
@RequestMapping("/api")
public class StockResource {

    private final Logger log = LoggerFactory.getLogger(StockResource.class);
        
    @Inject
    private StockService stockService;
    
    @Inject
    private StockMapper stockMapper;
    
    /**
     * POST  /stocks -> Create a new stock.
     */
    @RequestMapping(value = "/stocks",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockDTO stockDTO) throws URISyntaxException {
        log.debug("REST request to save Stock : {}", stockDTO);
        if (stockDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("stock", "idexists", "A new stock cannot already have an ID")).body(null);
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.created(new URI("/api/stocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("stock", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stocks -> Updates an existing stock.
     */
    @RequestMapping(value = "/stocks",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StockDTO> updateStock(@Valid @RequestBody StockDTO stockDTO) throws URISyntaxException {
        log.debug("REST request to update Stock : {}", stockDTO);
        if (stockDTO.getId() == null) {
            return createStock(stockDTO);
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("stock", stockDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stocks -> get all the stocks.
     */
    @RequestMapping(value = "/stocks",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<StockDTO> getAllStocks() {
        log.debug("REST request to get all Stocks");
        return stockService.findAll();
            }

    /**
     * GET  /stocks/:id -> get the "id" stock.
     */
    @RequestMapping(value = "/stocks/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StockDTO> getStock(@PathVariable Long id) {
        log.debug("REST request to get Stock : {}", id);
        StockDTO stockDTO = stockService.findOne(id);
        return Optional.ofNullable(stockDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /stocks/:id -> delete the "id" stock.
     */
    @RequestMapping(value = "/stocks/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        log.debug("REST request to delete Stock : {}", id);
        stockService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("stock", id.toString())).build();
    }
}
