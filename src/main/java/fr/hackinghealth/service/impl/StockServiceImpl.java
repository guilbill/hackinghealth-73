package fr.hackinghealth.service.impl;

import fr.hackinghealth.service.StockService;
import fr.hackinghealth.domain.Stock;
import fr.hackinghealth.repository.StockRepository;
import fr.hackinghealth.web.rest.dto.StockDTO;
import fr.hackinghealth.web.rest.mapper.StockMapper;
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
 * Service Implementation for managing Stock.
 */
@Service
@Transactional
public class StockServiceImpl implements StockService{

    private final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
    
    @Inject
    private StockRepository stockRepository;
    
    @Inject
    private StockMapper stockMapper;
    
    /**
     * Save a stock.
     * @return the persisted entity
     */
    public StockDTO save(StockDTO stockDTO) {
        log.debug("Request to save Stock : {}", stockDTO);
        Stock stock = stockMapper.stockDTOToStock(stockDTO);
        stock = stockRepository.save(stock);
        StockDTO result = stockMapper.stockToStockDTO(stock);
        return result;
    }

    /**
     *  get all the stocks.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<StockDTO> findAll() {
        log.debug("Request to get all Stocks");
        List<StockDTO> result = stockRepository.findAll().stream()
            .map(stockMapper::stockToStockDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one stock by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public StockDTO findOne(Long id) {
        log.debug("Request to get Stock : {}", id);
        Stock stock = stockRepository.findOne(id);
        StockDTO stockDTO = stockMapper.stockToStockDTO(stock);
        return stockDTO;
    }

    /**
     *  delete the  stock by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Stock : {}", id);
        stockRepository.delete(id);
    }
}
