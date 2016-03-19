package fr.hackinghealth.web.rest;

import fr.hackinghealth.Application;
import fr.hackinghealth.domain.Stock;
import fr.hackinghealth.repository.StockRepository;
import fr.hackinghealth.service.StockService;
import fr.hackinghealth.web.rest.dto.StockDTO;
import fr.hackinghealth.web.rest.mapper.StockMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the StockResource REST controller.
 *
 * @see StockResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StockResourceIntTest {

    private static final String DEFAULT_BATCH_NUMBER = "AAAAA";
    private static final String UPDATED_BATCH_NUMBER = "BBBBB";

    private static final Double DEFAULT_NUMBER_OF_PILLS = 1D;
    private static final Double UPDATED_NUMBER_OF_PILLS = 2D;

    private static final LocalDate DEFAULT_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private StockRepository stockRepository;

    @Inject
    private StockMapper stockMapper;

    @Inject
    private StockService stockService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restStockMockMvc;

    private Stock stock;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StockResource stockResource = new StockResource();
        ReflectionTestUtils.setField(stockResource, "stockService", stockService);
        ReflectionTestUtils.setField(stockResource, "stockMapper", stockMapper);
        this.restStockMockMvc = MockMvcBuilders.standaloneSetup(stockResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        stock = new Stock();
        stock.setBatchNumber(DEFAULT_BATCH_NUMBER);
        stock.setNumberOfPills(DEFAULT_NUMBER_OF_PILLS);
        stock.setExpirationDate(DEFAULT_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void createStock() throws Exception {
        int databaseSizeBeforeCreate = stockRepository.findAll().size();

        // Create the Stock
        StockDTO stockDTO = stockMapper.stockToStockDTO(stock);

        restStockMockMvc.perform(post("/api/stocks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockDTO)))
                .andExpect(status().isCreated());

        // Validate the Stock in the database
        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(databaseSizeBeforeCreate + 1);
        Stock testStock = stocks.get(stocks.size() - 1);
        assertThat(testStock.getBatchNumber()).isEqualTo(DEFAULT_BATCH_NUMBER);
        assertThat(testStock.getNumberOfPills()).isEqualTo(DEFAULT_NUMBER_OF_PILLS);
        assertThat(testStock.getExpirationDate()).isEqualTo(DEFAULT_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void checkBatchNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockRepository.findAll().size();
        // set the field null
        stock.setBatchNumber(null);

        // Create the Stock, which fails.
        StockDTO stockDTO = stockMapper.stockToStockDTO(stock);

        restStockMockMvc.perform(post("/api/stocks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockDTO)))
                .andExpect(status().isBadRequest());

        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberOfPillsIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockRepository.findAll().size();
        // set the field null
        stock.setNumberOfPills(null);

        // Create the Stock, which fails.
        StockDTO stockDTO = stockMapper.stockToStockDTO(stock);

        restStockMockMvc.perform(post("/api/stocks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockDTO)))
                .andExpect(status().isBadRequest());

        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpirationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockRepository.findAll().size();
        // set the field null
        stock.setExpirationDate(null);

        // Create the Stock, which fails.
        StockDTO stockDTO = stockMapper.stockToStockDTO(stock);

        restStockMockMvc.perform(post("/api/stocks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockDTO)))
                .andExpect(status().isBadRequest());

        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStocks() throws Exception {
        // Initialize the database
        stockRepository.saveAndFlush(stock);

        // Get all the stocks
        restStockMockMvc.perform(get("/api/stocks?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(stock.getId().intValue())))
                .andExpect(jsonPath("$.[*].batchNumber").value(hasItem(DEFAULT_BATCH_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].numberOfPills").value(hasItem(DEFAULT_NUMBER_OF_PILLS.doubleValue())))
                .andExpect(jsonPath("$.[*].expirationDate").value(hasItem(DEFAULT_EXPIRATION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getStock() throws Exception {
        // Initialize the database
        stockRepository.saveAndFlush(stock);

        // Get the stock
        restStockMockMvc.perform(get("/api/stocks/{id}", stock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(stock.getId().intValue()))
            .andExpect(jsonPath("$.batchNumber").value(DEFAULT_BATCH_NUMBER.toString()))
            .andExpect(jsonPath("$.numberOfPills").value(DEFAULT_NUMBER_OF_PILLS.doubleValue()))
            .andExpect(jsonPath("$.expirationDate").value(DEFAULT_EXPIRATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStock() throws Exception {
        // Get the stock
        restStockMockMvc.perform(get("/api/stocks/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStock() throws Exception {
        // Initialize the database
        stockRepository.saveAndFlush(stock);

		int databaseSizeBeforeUpdate = stockRepository.findAll().size();

        // Update the stock
        stock.setBatchNumber(UPDATED_BATCH_NUMBER);
        stock.setNumberOfPills(UPDATED_NUMBER_OF_PILLS);
        stock.setExpirationDate(UPDATED_EXPIRATION_DATE);
        StockDTO stockDTO = stockMapper.stockToStockDTO(stock);

        restStockMockMvc.perform(put("/api/stocks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockDTO)))
                .andExpect(status().isOk());

        // Validate the Stock in the database
        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(databaseSizeBeforeUpdate);
        Stock testStock = stocks.get(stocks.size() - 1);
        assertThat(testStock.getBatchNumber()).isEqualTo(UPDATED_BATCH_NUMBER);
        assertThat(testStock.getNumberOfPills()).isEqualTo(UPDATED_NUMBER_OF_PILLS);
        assertThat(testStock.getExpirationDate()).isEqualTo(UPDATED_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void deleteStock() throws Exception {
        // Initialize the database
        stockRepository.saveAndFlush(stock);

		int databaseSizeBeforeDelete = stockRepository.findAll().size();

        // Get the stock
        restStockMockMvc.perform(delete("/api/stocks/{id}", stock.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(databaseSizeBeforeDelete - 1);
    }
}
