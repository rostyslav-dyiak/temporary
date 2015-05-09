package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.CalculationType;
import com.kb.domain.TaxType;
import com.kb.repository.TaxTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TaxTypeResource REST controller.
 *
 * @see TaxTypeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TaxTypeResourceTest {

    private static final String DEFAULT_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_CODE = "UPDATED_TEXT";
    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";

    private static final Boolean DEFAULT_AVALIABLE = false;
    private static final Boolean UPDATED_AVALIABLE = true;
    private static final CalculationType DEFAULT_CALCULATION_TYPE = CalculationType.PERCENTAGE_OF_GRAND_TOTAL;
    private static final CalculationType UPDATED_CALCULATION_TYPE = CalculationType.PERCENTAGE_OF_SUBTOTAL;

    private static final BigDecimal DEFAULT_PERCENTAGE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_PERCENTAGE = BigDecimal.ONE;

    private static final Boolean DEFAULT_DEFAULT = false;
    private static final Boolean UPDATED_DEFAULT = true;

    @Inject
    private TaxTypeRepository taxTypeRepository;

    private MockMvc restTaxTypeMockMvc;

    private TaxType taxType;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TaxTypeResource taxTypeResource = new TaxTypeResource();
        ReflectionTestUtils.setField(taxTypeResource, "taxTypeRepository", taxTypeRepository);
        this.restTaxTypeMockMvc = MockMvcBuilders.standaloneSetup(taxTypeResource).build();
    }

    @Before
    public void initTest() {
        taxType = new TaxType();
        taxType.setCode(DEFAULT_CODE);
        taxType.setName(DEFAULT_NAME);
        taxType.setAvailable(DEFAULT_AVALIABLE);
        taxType.setCalculationType(DEFAULT_CALCULATION_TYPE);
        taxType.setPercentage(DEFAULT_PERCENTAGE);
        taxType.setDefault(DEFAULT_DEFAULT);
    }

    @Test
    @Transactional
    public void createTaxType() throws Exception {
        int databaseSizeBeforeCreate = taxTypeRepository.findAll().size();

        // Create the TaxType
        restTaxTypeMockMvc.perform(post("/api/taxTypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxType)))
            .andExpect(status().isCreated());

        // Validate the TaxType in the database
        List<TaxType> taxTypes = taxTypeRepository.findAll();
        assertThat(taxTypes).hasSize(databaseSizeBeforeCreate + 1);
        TaxType testTaxType = taxTypes.get(taxTypes.size() - 1);
        assertThat(testTaxType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTaxType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTaxType.getAvailable()).isEqualTo(DEFAULT_AVALIABLE);
        assertThat(testTaxType.getCalculationType()).isEqualTo(DEFAULT_CALCULATION_TYPE);
        assertThat(testTaxType.getPercentage()).isEqualTo(DEFAULT_PERCENTAGE);
        assertThat(testTaxType.getDefault()).isEqualTo(DEFAULT_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllTaxTypes() throws Exception {
        // Initialize the database
        taxTypeRepository.saveAndFlush(taxType);

        // Get all the taxTypes
        restTaxTypeMockMvc.perform(get("/api/taxTypes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taxType.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].avaliable").value(hasItem(DEFAULT_AVALIABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].calculationType").value(hasItem(DEFAULT_CALCULATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(DEFAULT_PERCENTAGE.intValue())))
            .andExpect(jsonPath("$.[*].default").value(hasItem(DEFAULT_DEFAULT.booleanValue())));
    }

    @Test
    @Transactional
    public void getTaxType() throws Exception {
        // Initialize the database
        taxTypeRepository.saveAndFlush(taxType);

        // Get the taxType
        restTaxTypeMockMvc.perform(get("/api/taxTypes/{id}", taxType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(taxType.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.avaliable").value(DEFAULT_AVALIABLE.booleanValue()))
            .andExpect(jsonPath("$.calculationType").value(DEFAULT_CALCULATION_TYPE.toString()))
            .andExpect(jsonPath("$.percentage").value(DEFAULT_PERCENTAGE.intValue()))
            .andExpect(jsonPath("$.default").value(DEFAULT_DEFAULT.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTaxType() throws Exception {
        // Get the taxType
        restTaxTypeMockMvc.perform(get("/api/taxTypes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaxType() throws Exception {
        // Initialize the database
        taxTypeRepository.saveAndFlush(taxType);

        int databaseSizeBeforeUpdate = taxTypeRepository.findAll().size();

        // Update the taxType
        taxType.setCode(UPDATED_CODE);
        taxType.setName(UPDATED_NAME);
        taxType.setAvailable(UPDATED_AVALIABLE);
        taxType.setCalculationType(UPDATED_CALCULATION_TYPE);
        taxType.setPercentage(UPDATED_PERCENTAGE);
        taxType.setDefault(UPDATED_DEFAULT);
        restTaxTypeMockMvc.perform(put("/api/taxTypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxType)))
            .andExpect(status().isOk());

        // Validate the TaxType in the database
        List<TaxType> taxTypes = taxTypeRepository.findAll();
        assertThat(taxTypes).hasSize(databaseSizeBeforeUpdate);
        TaxType testTaxType = taxTypes.get(taxTypes.size() - 1);
        assertThat(testTaxType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTaxType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTaxType.getAvailable()).isEqualTo(UPDATED_AVALIABLE);
        assertThat(testTaxType.getCalculationType()).isEqualTo(UPDATED_CALCULATION_TYPE);
        assertThat(testTaxType.getPercentage()).isEqualTo(UPDATED_PERCENTAGE);
        assertThat(testTaxType.getDefault()).isEqualTo(UPDATED_DEFAULT);
    }

    @Test
    @Transactional
    public void deleteTaxType() throws Exception {
        // Initialize the database
        taxTypeRepository.saveAndFlush(taxType);

        int databaseSizeBeforeDelete = taxTypeRepository.findAll().size();

        // Get the taxType
        restTaxTypeMockMvc.perform(delete("/api/taxTypes/{id}", taxType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TaxType> taxTypes = taxTypeRepository.findAll();
        assertThat(taxTypes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
