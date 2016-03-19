package fr.hackinghealth.web.rest;

import fr.hackinghealth.Application;
import fr.hackinghealth.domain.Medication;
import fr.hackinghealth.repository.MedicationRepository;

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
 * Test class for the MedicationResource REST controller.
 *
 * @see MedicationResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MedicationResourceIntTest {

    private static final String DEFAULT_CIP = "AAAAA";
    private static final String UPDATED_CIP = "BBBBB";
    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_LOT_NUMBER = "AAAAA";
    private static final String UPDATED_LOT_NUMBER = "BBBBB";

    private static final Integer DEFAULT_MIN_STOCK = 1;
    private static final Integer UPDATED_MIN_STOCK = 2;

    @Inject
    private MedicationRepository medicationRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMedicationMockMvc;

    private Medication medication;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MedicationResource medicationResource = new MedicationResource();
        ReflectionTestUtils.setField(medicationResource, "medicationRepository", medicationRepository);
        this.restMedicationMockMvc = MockMvcBuilders.standaloneSetup(medicationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        medication = new Medication();
        medication.setCip(DEFAULT_CIP);
        medication.setName(DEFAULT_NAME);
        medication.setLotNumber(DEFAULT_LOT_NUMBER);
        medication.setMinStock(DEFAULT_MIN_STOCK);
    }

    @Test
    @Transactional
    public void createMedication() throws Exception {
        int databaseSizeBeforeCreate = medicationRepository.findAll().size();

        // Create the Medication

        restMedicationMockMvc.perform(post("/api/medications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medication)))
                .andExpect(status().isCreated());

        // Validate the Medication in the database
        List<Medication> medications = medicationRepository.findAll();
        assertThat(medications).hasSize(databaseSizeBeforeCreate + 1);
        Medication testMedication = medications.get(medications.size() - 1);
        assertThat(testMedication.getCip()).isEqualTo(DEFAULT_CIP);
        assertThat(testMedication.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMedication.getLotNumber()).isEqualTo(DEFAULT_LOT_NUMBER);
        assertThat(testMedication.getMinStock()).isEqualTo(DEFAULT_MIN_STOCK);
    }

    @Test
    @Transactional
    public void checkCipIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicationRepository.findAll().size();
        // set the field null
        medication.setCip(null);

        // Create the Medication, which fails.

        restMedicationMockMvc.perform(post("/api/medications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medication)))
                .andExpect(status().isBadRequest());

        List<Medication> medications = medicationRepository.findAll();
        assertThat(medications).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicationRepository.findAll().size();
        // set the field null
        medication.setName(null);

        // Create the Medication, which fails.

        restMedicationMockMvc.perform(post("/api/medications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medication)))
                .andExpect(status().isBadRequest());

        List<Medication> medications = medicationRepository.findAll();
        assertThat(medications).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLotNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicationRepository.findAll().size();
        // set the field null
        medication.setLotNumber(null);

        // Create the Medication, which fails.

        restMedicationMockMvc.perform(post("/api/medications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medication)))
                .andExpect(status().isBadRequest());

        List<Medication> medications = medicationRepository.findAll();
        assertThat(medications).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedications() throws Exception {
        // Initialize the database
        medicationRepository.saveAndFlush(medication);

        // Get all the medications
        restMedicationMockMvc.perform(get("/api/medications?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(medication.getId().intValue())))
                .andExpect(jsonPath("$.[*].cip").value(hasItem(DEFAULT_CIP.toString())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].lotNumber").value(hasItem(DEFAULT_LOT_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].minStock").value(hasItem(DEFAULT_MIN_STOCK)));
    }

    @Test
    @Transactional
    public void getMedication() throws Exception {
        // Initialize the database
        medicationRepository.saveAndFlush(medication);

        // Get the medication
        restMedicationMockMvc.perform(get("/api/medications/{id}", medication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(medication.getId().intValue()))
            .andExpect(jsonPath("$.cip").value(DEFAULT_CIP.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.lotNumber").value(DEFAULT_LOT_NUMBER.toString()))
            .andExpect(jsonPath("$.minStock").value(DEFAULT_MIN_STOCK));
    }

    @Test
    @Transactional
    public void getNonExistingMedication() throws Exception {
        // Get the medication
        restMedicationMockMvc.perform(get("/api/medications/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedication() throws Exception {
        // Initialize the database
        medicationRepository.saveAndFlush(medication);

		int databaseSizeBeforeUpdate = medicationRepository.findAll().size();

        // Update the medication
        medication.setCip(UPDATED_CIP);
        medication.setName(UPDATED_NAME);
        medication.setLotNumber(UPDATED_LOT_NUMBER);
        medication.setMinStock(UPDATED_MIN_STOCK);

        restMedicationMockMvc.perform(put("/api/medications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medication)))
                .andExpect(status().isOk());

        // Validate the Medication in the database
        List<Medication> medications = medicationRepository.findAll();
        assertThat(medications).hasSize(databaseSizeBeforeUpdate);
        Medication testMedication = medications.get(medications.size() - 1);
        assertThat(testMedication.getCip()).isEqualTo(UPDATED_CIP);
        assertThat(testMedication.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMedication.getLotNumber()).isEqualTo(UPDATED_LOT_NUMBER);
        assertThat(testMedication.getMinStock()).isEqualTo(UPDATED_MIN_STOCK);
    }

    @Test
    @Transactional
    public void deleteMedication() throws Exception {
        // Initialize the database
        medicationRepository.saveAndFlush(medication);

		int databaseSizeBeforeDelete = medicationRepository.findAll().size();

        // Get the medication
        restMedicationMockMvc.perform(delete("/api/medications/{id}", medication.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Medication> medications = medicationRepository.findAll();
        assertThat(medications).hasSize(databaseSizeBeforeDelete - 1);
    }
}
