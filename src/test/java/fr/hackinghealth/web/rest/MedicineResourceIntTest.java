package fr.hackinghealth.web.rest;

import fr.hackinghealth.Application;
import fr.hackinghealth.domain.Medicine;
import fr.hackinghealth.repository.MedicineRepository;
import fr.hackinghealth.service.MedicineService;
import fr.hackinghealth.web.rest.dto.MedicineDTO;
import fr.hackinghealth.web.rest.mapper.MedicineMapper;

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
 * Test class for the MedicineResource REST controller.
 *
 * @see MedicineResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MedicineResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBB";
    private static final String DEFAULT_CIP = "AAAAA";
    private static final String UPDATED_CIP = "BBBBB";

    private static final Long DEFAULT_VIDAL_ID = 1L;
    private static final Long UPDATED_VIDAL_ID = 2L;

    private static final Integer DEFAULT_NUMBER_OF_PILLS = 1;
    private static final Integer UPDATED_NUMBER_OF_PILLS = 2;

    @Inject
    private MedicineRepository medicineRepository;

    @Inject
    private MedicineMapper medicineMapper;

    @Inject
    private MedicineService medicineService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMedicineMockMvc;

    private Medicine medicine;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MedicineResource medicineResource = new MedicineResource();
        ReflectionTestUtils.setField(medicineResource, "medicineService", medicineService);
        ReflectionTestUtils.setField(medicineResource, "medicineMapper", medicineMapper);
        this.restMedicineMockMvc = MockMvcBuilders.standaloneSetup(medicineResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        medicine = new Medicine();
        medicine.setDesignation(DEFAULT_DESIGNATION);
        medicine.setCip(DEFAULT_CIP);
        medicine.setVidalId(DEFAULT_VIDAL_ID);
        medicine.setNumberOfPills(DEFAULT_NUMBER_OF_PILLS);
    }

    @Test
    @Transactional
    public void createMedicine() throws Exception {
        int databaseSizeBeforeCreate = medicineRepository.findAll().size();

        // Create the Medicine
        MedicineDTO medicineDTO = medicineMapper.medicineToMedicineDTO(medicine);

        restMedicineMockMvc.perform(post("/api/medicines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineDTO)))
                .andExpect(status().isCreated());

        // Validate the Medicine in the database
        List<Medicine> medicines = medicineRepository.findAll();
        assertThat(medicines).hasSize(databaseSizeBeforeCreate + 1);
        Medicine testMedicine = medicines.get(medicines.size() - 1);
        assertThat(testMedicine.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testMedicine.getCip()).isEqualTo(DEFAULT_CIP);
        assertThat(testMedicine.getVidalId()).isEqualTo(DEFAULT_VIDAL_ID);
        assertThat(testMedicine.getNumberOfPills()).isEqualTo(DEFAULT_NUMBER_OF_PILLS);
    }

    @Test
    @Transactional
    public void checkDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicineRepository.findAll().size();
        // set the field null
        medicine.setDesignation(null);

        // Create the Medicine, which fails.
        MedicineDTO medicineDTO = medicineMapper.medicineToMedicineDTO(medicine);

        restMedicineMockMvc.perform(post("/api/medicines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineDTO)))
                .andExpect(status().isBadRequest());

        List<Medicine> medicines = medicineRepository.findAll();
        assertThat(medicines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCipIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicineRepository.findAll().size();
        // set the field null
        medicine.setCip(null);

        // Create the Medicine, which fails.
        MedicineDTO medicineDTO = medicineMapper.medicineToMedicineDTO(medicine);

        restMedicineMockMvc.perform(post("/api/medicines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineDTO)))
                .andExpect(status().isBadRequest());

        List<Medicine> medicines = medicineRepository.findAll();
        assertThat(medicines).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedicines() throws Exception {
        // Initialize the database
        medicineRepository.saveAndFlush(medicine);

        // Get all the medicines
        restMedicineMockMvc.perform(get("/api/medicines?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(medicine.getId().intValue())))
                .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
                .andExpect(jsonPath("$.[*].cip").value(hasItem(DEFAULT_CIP.toString())))
                .andExpect(jsonPath("$.[*].vidalId").value(hasItem(DEFAULT_VIDAL_ID.intValue())))
                .andExpect(jsonPath("$.[*].numberOfPills").value(hasItem(DEFAULT_NUMBER_OF_PILLS)));
    }

    @Test
    @Transactional
    public void getMedicine() throws Exception {
        // Initialize the database
        medicineRepository.saveAndFlush(medicine);

        // Get the medicine
        restMedicineMockMvc.perform(get("/api/medicines/{id}", medicine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(medicine.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.cip").value(DEFAULT_CIP.toString()))
            .andExpect(jsonPath("$.vidalId").value(DEFAULT_VIDAL_ID.intValue()))
            .andExpect(jsonPath("$.numberOfPills").value(DEFAULT_NUMBER_OF_PILLS));
    }

    @Test
    @Transactional
    public void getNonExistingMedicine() throws Exception {
        // Get the medicine
        restMedicineMockMvc.perform(get("/api/medicines/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicine() throws Exception {
        // Initialize the database
        medicineRepository.saveAndFlush(medicine);

		int databaseSizeBeforeUpdate = medicineRepository.findAll().size();

        // Update the medicine
        medicine.setDesignation(UPDATED_DESIGNATION);
        medicine.setCip(UPDATED_CIP);
        medicine.setVidalId(UPDATED_VIDAL_ID);
        medicine.setNumberOfPills(UPDATED_NUMBER_OF_PILLS);
        MedicineDTO medicineDTO = medicineMapper.medicineToMedicineDTO(medicine);

        restMedicineMockMvc.perform(put("/api/medicines")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineDTO)))
                .andExpect(status().isOk());

        // Validate the Medicine in the database
        List<Medicine> medicines = medicineRepository.findAll();
        assertThat(medicines).hasSize(databaseSizeBeforeUpdate);
        Medicine testMedicine = medicines.get(medicines.size() - 1);
        assertThat(testMedicine.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testMedicine.getCip()).isEqualTo(UPDATED_CIP);
        assertThat(testMedicine.getVidalId()).isEqualTo(UPDATED_VIDAL_ID);
        assertThat(testMedicine.getNumberOfPills()).isEqualTo(UPDATED_NUMBER_OF_PILLS);
    }

    @Test
    @Transactional
    public void deleteMedicine() throws Exception {
        // Initialize the database
        medicineRepository.saveAndFlush(medicine);

		int databaseSizeBeforeDelete = medicineRepository.findAll().size();

        // Get the medicine
        restMedicineMockMvc.perform(delete("/api/medicines/{id}", medicine.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Medicine> medicines = medicineRepository.findAll();
        assertThat(medicines).hasSize(databaseSizeBeforeDelete - 1);
    }
}
