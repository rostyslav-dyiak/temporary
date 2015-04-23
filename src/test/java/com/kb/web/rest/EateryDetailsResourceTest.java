package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.EateryDetails;
import com.kb.repository.EateryDetailsRepository;

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
 * Test class for the EateryDetailsResource REST controller.
 *
 * @see EateryDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EateryDetailsResourceTest {

    private static final String DEFAULT_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_CODE = "UPDATED_TEXT";
    private static final String DEFAULT_BUS_REG_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_BUS_REG_NUMBER = "UPDATED_TEXT";
    private static final String DEFAULT_BILLING_ADDRESS = "SAMPLE_TEXT";
    private static final String UPDATED_BILLING_ADDRESS = "UPDATED_TEXT";
    private static final String DEFAULT_POSTAL_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_POSTAL_CODE = "UPDATED_TEXT";
    private static final String DEFAULT_FAX_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_FAX_NUMBER = "UPDATED_TEXT";

    private static final Long DEFAULT_LOGO_ID = 0L;
    private static final Long UPDATED_LOGO_ID = 1L;

    private static final Long DEFAULT_TOP_RIGHT_PICTURE_ID = 0L;
    private static final Long UPDATED_TOP_RIGHT_PICTURE_ID = 1L;

    private static final Long DEFAULT_CONTACT_PERSON_ID = 0L;
    private static final Long UPDATED_CONTACT_PERSON_ID = 1L;

    @Inject
    private EateryDetailsRepository eateryDetailsRepository;

    private MockMvc restEateryDetailsMockMvc;

    private EateryDetails eateryDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EateryDetailsResource eateryDetailsResource = new EateryDetailsResource();
        ReflectionTestUtils.setField(eateryDetailsResource, "eateryDetailsRepository", eateryDetailsRepository);
        this.restEateryDetailsMockMvc = MockMvcBuilders.standaloneSetup(eateryDetailsResource).build();
    }

    @Before
    public void initTest() {
        eateryDetails = new EateryDetails();
        eateryDetails.setCode(DEFAULT_CODE);
        eateryDetails.setBusRegNumber(DEFAULT_BUS_REG_NUMBER);
        eateryDetails.setBillingAddress(DEFAULT_BILLING_ADDRESS);
        eateryDetails.setPostalCode(DEFAULT_POSTAL_CODE);
        eateryDetails.setFaxNumber(DEFAULT_FAX_NUMBER);
        eateryDetails.setLogoId(DEFAULT_LOGO_ID);
        eateryDetails.setTopRightPictureId(DEFAULT_TOP_RIGHT_PICTURE_ID);
        eateryDetails.setContactPersonId(DEFAULT_CONTACT_PERSON_ID);
    }

    @Test
    @Transactional
    public void createEateryDetails() throws Exception {
        int databaseSizeBeforeCreate = eateryDetailsRepository.findAll().size();

        // Create the EateryDetails
        restEateryDetailsMockMvc.perform(post("/api/eateryDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eateryDetails)))
                .andExpect(status().isCreated());

        // Validate the EateryDetails in the database
        List<EateryDetails> eateryDetailss = eateryDetailsRepository.findAll();
        assertThat(eateryDetailss).hasSize(databaseSizeBeforeCreate + 1);
        EateryDetails testEateryDetails = eateryDetailss.get(eateryDetailss.size() - 1);
        assertThat(testEateryDetails.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testEateryDetails.getBusRegNumber()).isEqualTo(DEFAULT_BUS_REG_NUMBER);
        assertThat(testEateryDetails.getBillingAddress()).isEqualTo(DEFAULT_BILLING_ADDRESS);
        assertThat(testEateryDetails.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testEateryDetails.getFaxNumber()).isEqualTo(DEFAULT_FAX_NUMBER);
        assertThat(testEateryDetails.getLogoId()).isEqualTo(DEFAULT_LOGO_ID);
        assertThat(testEateryDetails.getTopRightPictureId()).isEqualTo(DEFAULT_TOP_RIGHT_PICTURE_ID);
        assertThat(testEateryDetails.getContactPersonId()).isEqualTo(DEFAULT_CONTACT_PERSON_ID);
    }

    @Test
    @Transactional
    public void getAllEateryDetailss() throws Exception {
        // Initialize the database
        eateryDetailsRepository.saveAndFlush(eateryDetails);

        // Get all the eateryDetailss
        restEateryDetailsMockMvc.perform(get("/api/eateryDetailss"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(eateryDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
                .andExpect(jsonPath("$.[*].BusRegNumber").value(hasItem(DEFAULT_BUS_REG_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].BillingAddress").value(hasItem(DEFAULT_BILLING_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].PostalCode").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
                .andExpect(jsonPath("$.[*].FaxNumber").value(hasItem(DEFAULT_FAX_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].logoId").value(hasItem(DEFAULT_LOGO_ID.intValue())))
                .andExpect(jsonPath("$.[*].topRightPictureId").value(hasItem(DEFAULT_TOP_RIGHT_PICTURE_ID.intValue())))
                .andExpect(jsonPath("$.[*].contactPersonId").value(hasItem(DEFAULT_CONTACT_PERSON_ID.intValue())));
    }

    @Test
    @Transactional
    public void getEateryDetails() throws Exception {
        // Initialize the database
        eateryDetailsRepository.saveAndFlush(eateryDetails);

        // Get the eateryDetails
        restEateryDetailsMockMvc.perform(get("/api/eateryDetailss/{id}", eateryDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(eateryDetails.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.BusRegNumber").value(DEFAULT_BUS_REG_NUMBER.toString()))
            .andExpect(jsonPath("$.BillingAddress").value(DEFAULT_BILLING_ADDRESS.toString()))
            .andExpect(jsonPath("$.PostalCode").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.FaxNumber").value(DEFAULT_FAX_NUMBER.toString()))
            .andExpect(jsonPath("$.logoId").value(DEFAULT_LOGO_ID.intValue()))
            .andExpect(jsonPath("$.topRightPictureId").value(DEFAULT_TOP_RIGHT_PICTURE_ID.intValue()))
            .andExpect(jsonPath("$.contactPersonId").value(DEFAULT_CONTACT_PERSON_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEateryDetails() throws Exception {
        // Get the eateryDetails
        restEateryDetailsMockMvc.perform(get("/api/eateryDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEateryDetails() throws Exception {
        // Initialize the database
        eateryDetailsRepository.saveAndFlush(eateryDetails);
		
		int databaseSizeBeforeUpdate = eateryDetailsRepository.findAll().size();

        // Update the eateryDetails
        eateryDetails.setCode(UPDATED_CODE);
        eateryDetails.setBusRegNumber(UPDATED_BUS_REG_NUMBER);
        eateryDetails.setBillingAddress(UPDATED_BILLING_ADDRESS);
        eateryDetails.setPostalCode(UPDATED_POSTAL_CODE);
        eateryDetails.setFaxNumber(UPDATED_FAX_NUMBER);
        eateryDetails.setLogoId(UPDATED_LOGO_ID);
        eateryDetails.setTopRightPictureId(UPDATED_TOP_RIGHT_PICTURE_ID);
        eateryDetails.setContactPersonId(UPDATED_CONTACT_PERSON_ID);
        restEateryDetailsMockMvc.perform(put("/api/eateryDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eateryDetails)))
                .andExpect(status().isOk());

        // Validate the EateryDetails in the database
        List<EateryDetails> eateryDetailss = eateryDetailsRepository.findAll();
        assertThat(eateryDetailss).hasSize(databaseSizeBeforeUpdate);
        EateryDetails testEateryDetails = eateryDetailss.get(eateryDetailss.size() - 1);
        assertThat(testEateryDetails.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testEateryDetails.getBusRegNumber()).isEqualTo(UPDATED_BUS_REG_NUMBER);
        assertThat(testEateryDetails.getBillingAddress()).isEqualTo(UPDATED_BILLING_ADDRESS);
        assertThat(testEateryDetails.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testEateryDetails.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
        assertThat(testEateryDetails.getLogoId()).isEqualTo(UPDATED_LOGO_ID);
        assertThat(testEateryDetails.getTopRightPictureId()).isEqualTo(UPDATED_TOP_RIGHT_PICTURE_ID);
        assertThat(testEateryDetails.getContactPersonId()).isEqualTo(UPDATED_CONTACT_PERSON_ID);
    }

    @Test
    @Transactional
    public void deleteEateryDetails() throws Exception {
        // Initialize the database
        eateryDetailsRepository.saveAndFlush(eateryDetails);
		
		int databaseSizeBeforeDelete = eateryDetailsRepository.findAll().size();

        // Get the eateryDetails
        restEateryDetailsMockMvc.perform(delete("/api/eateryDetailss/{id}", eateryDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<EateryDetails> eateryDetailss = eateryDetailsRepository.findAll();
        assertThat(eateryDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
