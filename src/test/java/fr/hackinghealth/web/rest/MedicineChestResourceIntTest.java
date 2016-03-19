package fr.hackinghealth.web.rest;

import fr.hackinghealth.Application;
import fr.hackinghealth.domain.MedicineChest;
import fr.hackinghealth.repository.MedicineChestRepository;
import fr.hackinghealth.service.MedicineChestService;
import fr.hackinghealth.web.rest.dto.MedicineChestDTO;
import fr.hackinghealth.web.rest.mapper.MedicineChestMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the MedicineChestResource REST controller.
 *
 * @see MedicineChestResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MedicineChestResourceIntTest {


    private static final Boolean DEFAULT_OPEN = false;
    private static final Boolean UPDATED_OPEN = true;

    @Inject
    private MedicineChestRepository medicineChestRepository;

    @Inject
    private MedicineChestMapper medicineChestMapper;

    @Inject
    private MedicineChestService medicineChestService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMedicineChestMockMvc;

    private MedicineChest medicineChest;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MedicineChestResource medicineChestResource = new MedicineChestResource();
        ReflectionTestUtils.setField(medicineChestResource, "medicineChestService", medicineChestService);
        ReflectionTestUtils.setField(medicineChestResource, "medicineChestMapper", medicineChestMapper);
        this.restMedicineChestMockMvc = MockMvcBuilders.standaloneSetup(medicineChestResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        medicineChest = new MedicineChest();
        medicineChest.setOpen(DEFAULT_OPEN);
    }

    @Test
    @Transactional
    public void createMedicineChest() throws Exception {
        int databaseSizeBeforeCreate = medicineChestRepository.findAll().size();

        // Create the MedicineChest
        MedicineChestDTO medicineChestDTO = medicineChestMapper.medicineChestToMedicineChestDTO(medicineChest);

        restMedicineChestMockMvc.perform(post("/api/medicineChests")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineChestDTO)))
                .andExpect(status().isCreated());

        // Validate the MedicineChest in the database
        List<MedicineChest> medicineChests = medicineChestRepository.findAll();
        assertThat(medicineChests).hasSize(databaseSizeBeforeCreate + 1);
        MedicineChest testMedicineChest = medicineChests.get(medicineChests.size() - 1);
        assertThat(testMedicineChest.getOpen()).isEqualTo(DEFAULT_OPEN);
    }

    @Test
    @Transactional
    public void checkOpenIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicineChestRepository.findAll().size();
        // set the field null
        medicineChest.setOpen(null);

        // Create the MedicineChest, which fails.
        MedicineChestDTO medicineChestDTO = medicineChestMapper.medicineChestToMedicineChestDTO(medicineChest);

        restMedicineChestMockMvc.perform(post("/api/medicineChests")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineChestDTO)))
                .andExpect(status().isBadRequest());

        List<MedicineChest> medicineChests = medicineChestRepository.findAll();
        assertThat(medicineChests).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedicineChests() throws Exception {
        // Initialize the database
        medicineChestRepository.saveAndFlush(medicineChest);

        // Get all the medicineChests
        restMedicineChestMockMvc.perform(get("/api/medicineChests?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(medicineChest.getId().intValue())))
                .andExpect(jsonPath("$.[*].open").value(hasItem(DEFAULT_OPEN.booleanValue())));
    }

    @Test
    @Transactional
    public void getMedicineChest() throws Exception {
        // Initialize the database
        medicineChestRepository.saveAndFlush(medicineChest);

        // Get the medicineChest
        restMedicineChestMockMvc.perform(get("/api/medicineChests/{id}", medicineChest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(medicineChest.getId().intValue()))
            .andExpect(jsonPath("$.open").value(DEFAULT_OPEN.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMedicineChest() throws Exception {
        // Get the medicineChest
        restMedicineChestMockMvc.perform(get("/api/medicineChests/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicineChest() throws Exception {
        // Initialize the database
        medicineChestRepository.saveAndFlush(medicineChest);

		int databaseSizeBeforeUpdate = medicineChestRepository.findAll().size();

        // Update the medicineChest
        medicineChest.setOpen(UPDATED_OPEN);
        MedicineChestDTO medicineChestDTO = medicineChestMapper.medicineChestToMedicineChestDTO(medicineChest);

        restMedicineChestMockMvc.perform(put("/api/medicineChests")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineChestDTO)))
                .andExpect(status().isOk());

        // Validate the MedicineChest in the database
        List<MedicineChest> medicineChests = medicineChestRepository.findAll();
        assertThat(medicineChests).hasSize(databaseSizeBeforeUpdate);
        MedicineChest testMedicineChest = medicineChests.get(medicineChests.size() - 1);
        assertThat(testMedicineChest.getOpen()).isEqualTo(UPDATED_OPEN);
    }

    @Test
    @Transactional
    public void deleteMedicineChest() throws Exception {
        // Initialize the database
        medicineChestRepository.saveAndFlush(medicineChest);

		int databaseSizeBeforeDelete = medicineChestRepository.findAll().size();

        // Get the medicineChest
        restMedicineChestMockMvc.perform(delete("/api/medicineChests/{id}", medicineChest.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MedicineChest> medicineChests = medicineChestRepository.findAll();
        assertThat(medicineChests).hasSize(databaseSizeBeforeDelete - 1);
    }
}
