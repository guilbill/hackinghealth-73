package fr.hackinghealth.web.rest;

import fr.hackinghealth.Application;
import fr.hackinghealth.domain.MedicineChestLog;
import fr.hackinghealth.repository.MedicineChestLogRepository;
import fr.hackinghealth.service.MedicineChestLogService;

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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the MedicineChestLogResource REST controller.
 *
 * @see MedicineChestLogResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MedicineChestLogResourceIntTest {


    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.now();
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now();

    private static final Boolean DEFAULT_OPEN = false;
    private static final Boolean UPDATED_OPEN = true;

    @Inject
    private MedicineChestLogRepository medicineChestLogRepository;

    @Inject
    private MedicineChestLogService medicineChestLogService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMedicineChestLogMockMvc;

    private MedicineChestLog medicineChestLog;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MedicineChestLogResource medicineChestLogResource = new MedicineChestLogResource();
        ReflectionTestUtils.setField(medicineChestLogResource, "medicineChestLogService", medicineChestLogService);
        this.restMedicineChestLogMockMvc = MockMvcBuilders.standaloneSetup(medicineChestLogResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        medicineChestLog = new MedicineChestLog();
        medicineChestLog.setDate(DEFAULT_DATE);
        medicineChestLog.setOpen(DEFAULT_OPEN);
    }

    @Test
    @Transactional
    public void createMedicineChestLog() throws Exception {
        int databaseSizeBeforeCreate = medicineChestLogRepository.findAll().size();

        // Create the MedicineChestLog

        restMedicineChestLogMockMvc.perform(post("/api/medicineChestLogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineChestLog)))
                .andExpect(status().isCreated());

        // Validate the MedicineChestLog in the database
        List<MedicineChestLog> medicineChestLogs = medicineChestLogRepository.findAll();
        assertThat(medicineChestLogs).hasSize(databaseSizeBeforeCreate + 1);
        MedicineChestLog testMedicineChestLog = medicineChestLogs.get(medicineChestLogs.size() - 1);
        assertThat(testMedicineChestLog.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testMedicineChestLog.getOpen()).isEqualTo(DEFAULT_OPEN);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicineChestLogRepository.findAll().size();
        // set the field null
        medicineChestLog.setDate(null);

        // Create the MedicineChestLog, which fails.

        restMedicineChestLogMockMvc.perform(post("/api/medicineChestLogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineChestLog)))
                .andExpect(status().isBadRequest());

        List<MedicineChestLog> medicineChestLogs = medicineChestLogRepository.findAll();
        assertThat(medicineChestLogs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOpenIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicineChestLogRepository.findAll().size();
        // set the field null
        medicineChestLog.setOpen(null);

        // Create the MedicineChestLog, which fails.

        restMedicineChestLogMockMvc.perform(post("/api/medicineChestLogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineChestLog)))
                .andExpect(status().isBadRequest());

        List<MedicineChestLog> medicineChestLogs = medicineChestLogRepository.findAll();
        assertThat(medicineChestLogs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getNonExistingMedicineChestLog() throws Exception {
        // Get the medicineChestLog
        restMedicineChestLogMockMvc.perform(get("/api/medicineChestLogs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicineChestLog() throws Exception {
        // Initialize the database
        medicineChestLogRepository.saveAndFlush(medicineChestLog);

		int databaseSizeBeforeUpdate = medicineChestLogRepository.findAll().size();

        // Update the medicineChestLog
        medicineChestLog.setDate(UPDATED_DATE);
        medicineChestLog.setOpen(UPDATED_OPEN);

        restMedicineChestLogMockMvc.perform(put("/api/medicineChestLogs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(medicineChestLog)))
                .andExpect(status().isOk());

        // Validate the MedicineChestLog in the database
        List<MedicineChestLog> medicineChestLogs = medicineChestLogRepository.findAll();
        assertThat(medicineChestLogs).hasSize(databaseSizeBeforeUpdate);
        MedicineChestLog testMedicineChestLog = medicineChestLogs.get(medicineChestLogs.size() - 1);
        assertThat(testMedicineChestLog.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testMedicineChestLog.getOpen()).isEqualTo(UPDATED_OPEN);
    }

    @Test
    @Transactional
    public void deleteMedicineChestLog() throws Exception {
        // Initialize the database
        medicineChestLogRepository.saveAndFlush(medicineChestLog);

		int databaseSizeBeforeDelete = medicineChestLogRepository.findAll().size();

        // Get the medicineChestLog
        restMedicineChestLogMockMvc.perform(delete("/api/medicineChestLogs/{id}", medicineChestLog.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MedicineChestLog> medicineChestLogs = medicineChestLogRepository.findAll();
        assertThat(medicineChestLogs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
