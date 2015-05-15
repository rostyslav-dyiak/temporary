package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.BusinessType;
import com.kb.repository.BusinessTypeRepository;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BusinessTypeResource REST controller.
 *
 * @see BusinessTypeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BusinessTypeResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    @Inject
    private BusinessTypeRepository businessTypeRepository;

    private MockMvc restBusinessTypeMockMvc;

    private BusinessType businessType;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BusinessTypeResource businessTypeResource = new BusinessTypeResource();
        ReflectionTestUtils.setField(businessTypeResource, "businessTypeRepository", businessTypeRepository);
        this.restBusinessTypeMockMvc = MockMvcBuilders.standaloneSetup(businessTypeResource).build();
    }

    @Before
    public void initTest() {
        businessType = new BusinessType();
        businessType.setName(DEFAULT_NAME);
        businessType.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBusinessType() throws Exception {
        int databaseSizeBeforeCreate = businessTypeRepository.findAll().size();

        // Create the BusinessType
        restBusinessTypeMockMvc.perform(post("/api/businessTypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(businessType)))
                .andExpect(status().isCreated());

        // Validate the BusinessType in the database
        List<BusinessType> businessTypes = businessTypeRepository.findAll();
        assertThat(businessTypes).hasSize(databaseSizeBeforeCreate + 1);
        BusinessType testBusinessType = businessTypes.get(businessTypes.size() - 1);
        assertThat(testBusinessType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBusinessType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllBusinessTypes() throws Exception {
        // Initialize the database
        businessTypeRepository.saveAndFlush(businessType);

        // Get all the businessTypes
        restBusinessTypeMockMvc.perform(get("/api/businessTypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(businessType.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getBusinessType() throws Exception {
        // Initialize the database
        businessTypeRepository.saveAndFlush(businessType);

        // Get the businessType
        restBusinessTypeMockMvc.perform(get("/api/businessTypes/{id}", businessType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(businessType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBusinessType() throws Exception {
        // Get the businessType
        restBusinessTypeMockMvc.perform(get("/api/businessTypes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessType() throws Exception {
        // Initialize the database
        businessTypeRepository.saveAndFlush(businessType);

        int databaseSizeBeforeUpdate = businessTypeRepository.findAll().size();

        // Update the businessType
        businessType.setName(UPDATED_NAME);
        businessType.setDescription(UPDATED_DESCRIPTION);
        restBusinessTypeMockMvc.perform(put("/api/businessTypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(businessType)))
                .andExpect(status().isOk());

        // Validate the BusinessType in the database
        List<BusinessType> businessTypes = businessTypeRepository.findAll();
        assertThat(businessTypes).hasSize(databaseSizeBeforeUpdate);
        BusinessType testBusinessType = businessTypes.get(businessTypes.size() - 1);
        assertThat(testBusinessType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBusinessType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteBusinessType() throws Exception {
        // Initialize the database
        businessTypeRepository.saveAndFlush(businessType);

        int databaseSizeBeforeDelete = businessTypeRepository.findAll().size();

        // Get the businessType
        restBusinessTypeMockMvc.perform(delete("/api/businessTypes/{id}", businessType.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BusinessType> businessTypes = businessTypeRepository.findAll();
        assertThat(businessTypes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
