package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.PricingGroup;
import com.kb.repository.PricingGroupRepository;
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
 * Test class for the PricingGroupResource REST controller.
 *
 * @see PricingGroupResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PricingGroupResourceTest {

    private static final String DEFAULT_GROUP_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_GROUP_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    @Inject
    private PricingGroupRepository pricingGroupRepository;

    private MockMvc restPricingGroupMockMvc;

    private PricingGroup pricingGroup;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PricingGroupResource pricingGroupResource = new PricingGroupResource();
        ReflectionTestUtils.setField(pricingGroupResource, "pricingGroupRepository", pricingGroupRepository);
        this.restPricingGroupMockMvc = MockMvcBuilders.standaloneSetup(pricingGroupResource).build();
    }

    @Before
    public void initTest() {
        pricingGroup = new PricingGroup();
        pricingGroup.setGroupName(DEFAULT_GROUP_NAME);
        pricingGroup.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createPricingGroup() throws Exception {
        int databaseSizeBeforeCreate = pricingGroupRepository.findAll().size();

        // Create the PricingGroup
        restPricingGroupMockMvc.perform(post("/api/pricingGroups")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pricingGroup)))
                .andExpect(status().isCreated());

        // Validate the PricingGroup in the database
        List<PricingGroup> pricingGroups = pricingGroupRepository.findAll();
        assertThat(pricingGroups).hasSize(databaseSizeBeforeCreate + 1);
        PricingGroup testPricingGroup = pricingGroups.get(pricingGroups.size() - 1);
        assertThat(testPricingGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testPricingGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPricingGroups() throws Exception {
        // Initialize the database
        pricingGroupRepository.saveAndFlush(pricingGroup);

        // Get all the pricingGroups
        restPricingGroupMockMvc.perform(get("/api/pricingGroups"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(pricingGroup.getId().intValue())))
                .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getPricingGroup() throws Exception {
        // Initialize the database
        pricingGroupRepository.saveAndFlush(pricingGroup);

        // Get the pricingGroup
        restPricingGroupMockMvc.perform(get("/api/pricingGroups/{id}", pricingGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(pricingGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPricingGroup() throws Exception {
        // Get the pricingGroup
        restPricingGroupMockMvc.perform(get("/api/pricingGroups/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePricingGroup() throws Exception {
        // Initialize the database
        pricingGroupRepository.saveAndFlush(pricingGroup);
		
		int databaseSizeBeforeUpdate = pricingGroupRepository.findAll().size();

        // Update the pricingGroup
        pricingGroup.setGroupName(UPDATED_GROUP_NAME);
        pricingGroup.setDescription(UPDATED_DESCRIPTION);
        restPricingGroupMockMvc.perform(put("/api/pricingGroups")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pricingGroup)))
                .andExpect(status().isOk());

        // Validate the PricingGroup in the database
        List<PricingGroup> pricingGroups = pricingGroupRepository.findAll();
        assertThat(pricingGroups).hasSize(databaseSizeBeforeUpdate);
        PricingGroup testPricingGroup = pricingGroups.get(pricingGroups.size() - 1);
        assertThat(testPricingGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testPricingGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deletePricingGroup() throws Exception {
        // Initialize the database
        pricingGroupRepository.saveAndFlush(pricingGroup);
		
		int databaseSizeBeforeDelete = pricingGroupRepository.findAll().size();

        // Get the pricingGroup
        restPricingGroupMockMvc.perform(delete("/api/pricingGroups/{id}", pricingGroup.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PricingGroup> pricingGroups = pricingGroupRepository.findAll();
        assertThat(pricingGroups).hasSize(databaseSizeBeforeDelete - 1);
    }
}
