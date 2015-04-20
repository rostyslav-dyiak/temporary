package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.Eatery;
import com.kb.repository.EateryRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EateryResource REST controller.
 *
 * @see EateryResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EateryResourceTest {

    private static final String DEFAULT_COMPANY_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_COMPANY_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_BUS_REGISTRATION_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_BUS_REGISTRATION_NUMBER = "UPDATED_TEXT";
    private static final String DEFAULT_BILLING_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_BILLING_NUMBER = "UPDATED_TEXT";
    private static final String DEFAULT_POSTAL_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_POSTAL_CODE = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";
    private static final String DEFAULT_COMPANY_CONTACT_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_COMPANY_CONTACT_NUMBER = "UPDATED_TEXT";
    private static final String DEFAULT_COMPANY_FAX_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_COMPANY_FAX_NUMBER = "UPDATED_TEXT";

    @Inject
    private EateryRepository eateryRepository;

    private MockMvc restEateryMockMvc;

    private Eatery eatery;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EateryResource eateryResource = new EateryResource();
        ReflectionTestUtils.setField(eateryResource, "eateryRepository", eateryRepository);
        this.restEateryMockMvc = MockMvcBuilders.standaloneSetup(eateryResource).build();
    }

    @Before
    public void initTest() {
        eatery = new Eatery();
        eatery.setCompany_name(DEFAULT_COMPANY_NAME);
        eatery.setBus_registration_number(DEFAULT_BUS_REGISTRATION_NUMBER);
        eatery.setBilling_number(DEFAULT_BILLING_NUMBER);
        eatery.setPostal_code(DEFAULT_POSTAL_CODE);
        eatery.setEmail(DEFAULT_EMAIL);
        eatery.setCompany_contact_number(DEFAULT_COMPANY_CONTACT_NUMBER);
        eatery.setCompany_fax_number(DEFAULT_COMPANY_FAX_NUMBER);
    }

    @Test
    @Transactional
    public void createEatery() throws Exception {
        int databaseSizeBeforeCreate = eateryRepository.findAll().size();

        // Create the Eatery
        restEateryMockMvc.perform(post("/api/eaterys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eatery)))
                .andExpect(status().isCreated());

        // Validate the Eatery in the database
        List<Eatery> eaterys = eateryRepository.findAll();
        assertThat(eaterys).hasSize(databaseSizeBeforeCreate + 1);
        Eatery testEatery = eaterys.get(eaterys.size() - 1);
        assertThat(testEatery.getCompany_name()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testEatery.getBus_registration_number()).isEqualTo(DEFAULT_BUS_REGISTRATION_NUMBER);
        assertThat(testEatery.getBilling_number()).isEqualTo(DEFAULT_BILLING_NUMBER);
        assertThat(testEatery.getPostal_code()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testEatery.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEatery.getCompany_contact_number()).isEqualTo(DEFAULT_COMPANY_CONTACT_NUMBER);
        assertThat(testEatery.getCompany_fax_number()).isEqualTo(DEFAULT_COMPANY_FAX_NUMBER);
    }

    @Test
    @Transactional
    public void getAllEaterys() throws Exception {
        // Initialize the database
        eateryRepository.saveAndFlush(eatery);

        // Get all the eaterys
        restEateryMockMvc.perform(get("/api/eaterys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(eatery.getId().intValue())))
                .andExpect(jsonPath("$.[*].company_name").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
                .andExpect(jsonPath("$.[*].bus_registration_number").value(hasItem(DEFAULT_BUS_REGISTRATION_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].billing_number").value(hasItem(DEFAULT_BILLING_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].postal_code").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].company_contact_number").value(hasItem(DEFAULT_COMPANY_CONTACT_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].company_fax_number").value(hasItem(DEFAULT_COMPANY_FAX_NUMBER.toString())));
    }

    @Test
    @Transactional
    public void getEatery() throws Exception {
        // Initialize the database
        eateryRepository.saveAndFlush(eatery);

        // Get the eatery
        restEateryMockMvc.perform(get("/api/eaterys/{id}", eatery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(eatery.getId().intValue()))
            .andExpect(jsonPath("$.company_name").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.bus_registration_number").value(DEFAULT_BUS_REGISTRATION_NUMBER.toString()))
            .andExpect(jsonPath("$.billing_number").value(DEFAULT_BILLING_NUMBER.toString()))
            .andExpect(jsonPath("$.postal_code").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.company_contact_number").value(DEFAULT_COMPANY_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.company_fax_number").value(DEFAULT_COMPANY_FAX_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEatery() throws Exception {
        // Get the eatery
        restEateryMockMvc.perform(get("/api/eaterys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEatery() throws Exception {
        // Initialize the database
        eateryRepository.saveAndFlush(eatery);
		
		int databaseSizeBeforeUpdate = eateryRepository.findAll().size();

        // Update the eatery
        eatery.setCompany_name(UPDATED_COMPANY_NAME);
        eatery.setBus_registration_number(UPDATED_BUS_REGISTRATION_NUMBER);
        eatery.setBilling_number(UPDATED_BILLING_NUMBER);
        eatery.setPostal_code(UPDATED_POSTAL_CODE);
        eatery.setEmail(UPDATED_EMAIL);
        eatery.setCompany_contact_number(UPDATED_COMPANY_CONTACT_NUMBER);
        eatery.setCompany_fax_number(UPDATED_COMPANY_FAX_NUMBER);
        restEateryMockMvc.perform(put("/api/eaterys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eatery)))
                .andExpect(status().isOk());

        // Validate the Eatery in the database
        List<Eatery> eaterys = eateryRepository.findAll();
        assertThat(eaterys).hasSize(databaseSizeBeforeUpdate);
        Eatery testEatery = eaterys.get(eaterys.size() - 1);
        assertThat(testEatery.getCompany_name()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testEatery.getBus_registration_number()).isEqualTo(UPDATED_BUS_REGISTRATION_NUMBER);
        assertThat(testEatery.getBilling_number()).isEqualTo(UPDATED_BILLING_NUMBER);
        assertThat(testEatery.getPostal_code()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testEatery.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEatery.getCompany_contact_number()).isEqualTo(UPDATED_COMPANY_CONTACT_NUMBER);
        assertThat(testEatery.getCompany_fax_number()).isEqualTo(UPDATED_COMPANY_FAX_NUMBER);
    }

    @Test
    @Transactional
    public void deleteEatery() throws Exception {
        // Initialize the database
        eateryRepository.saveAndFlush(eatery);
		
		int databaseSizeBeforeDelete = eateryRepository.findAll().size();

        // Get the eatery
        restEateryMockMvc.perform(delete("/api/eaterys/{id}", eatery.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Eatery> eaterys = eateryRepository.findAll();
        assertThat(eaterys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
