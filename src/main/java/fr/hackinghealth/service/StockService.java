package fr.hackinghealth.service;

import fr.hackinghealth.domain.Stock;
import fr.hackinghealth.web.rest.dto.StockDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Stock.
 */
public interface StockService {

    /**
     * Save a stock.
     * @return the persisted entity
     */
    public StockDTO save(StockDTO stockDTO);

    /**
     *  get all the stocks.
     *  @return the list of entities
     */
    public List<StockDTO> findAll();

    /**
     *  get the "id" stock.
     *  @return the entity
     */
    public StockDTO findOne(Long id);

    /**
     *  delete the "id" stock.
     */
    public void delete(Long id);
}
