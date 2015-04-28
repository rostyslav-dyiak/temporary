package com.kb.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

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

import com.kb.Application;
import com.kb.domain.SupplierDetails;
import com.kb.repository.SupplierDetailsRepository;

/**
 * Test class for the SupplierDetailsResource REST controller.
 *
 * @see SupplierDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SupplierDetailsResourceTest {

    private static final String DEFAULT_CODE = "SAMPLE_TEXT";
    private static final String DEFAULT_BUS_REG_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_BUS_REG_NUMBER = "UPDATED_TEXT";
    private static final String DEFAULT_BUS_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_BUS_DESCRIPTION = "UPDATED_TEXT";
    private static final String DEFAULT_ADDRESS = "SAMPLE_TEXT";
    private static final String UPDATED_ADDRESS = "UPDATED_TEXT";
    private static final String DEFAULT_FAX_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_FAX_NUMBER = "UPDATED_TEXT";

    private static final Boolean DEFAULT_GST_REGISTERED = false;
    private static final Boolean UPDATED_GST_REGISTERED = true;
    private static final String DEFAULT_GST_REGISTRATION_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_GST_REGISTRATION_NUMBER = "UPDATED_TEXT";

    private static final Long DEFAULT_LOGO_ID = 0L;

    private static final Boolean DEFAULT_PUBLIC_PRICING_VISIBLE = false;
    private static final Boolean UPDATED_PUBLIC_PRICING_VISIBLE = true;

    private static final Long DEFAULT_MAIN_PICTURE_ID = 0L;

    @Inject
    private SupplierDetailsRepository supplierDetailsRepository;

    private MockMvc restSupplierDetailsMockMvc;

    private SupplierDetails supplierDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SupplierDetailsResource supplierDetailsResource = new SupplierDetailsResource();
        ReflectionTestUtils.setField(supplierDetailsResource, "supplierDetailsRepository", supplierDetailsRepository);
        this.restSupplierDetailsMockMvc = MockMvcBuilders.standaloneSetup(supplierDetailsResource).build();
    }

    @Before
    public void initTest() {
        supplierDetails = new SupplierDetails();
        supplierDetails.setBusRegNumber(DEFAULT_BUS_REG_NUMBER);
        supplierDetails.setBusDescription(DEFAULT_BUS_DESCRIPTION);
        supplierDetails.setAddress(DEFAULT_ADDRESS);
        supplierDetails.setFaxNumber(DEFAULT_FAX_NUMBER);
        supplierDetails.setGstRegistered(DEFAULT_GST_REGISTERED);
        supplierDetails.setGstRegistrationNumber(DEFAULT_GST_REGISTRATION_NUMBER);
        supplierDetails.setPublicPricingVisible(DEFAULT_PUBLIC_PRICING_VISIBLE);
    }

    @Test
    @Transactional
    public void createSupplierDetails() throws Exception {
        int databaseSizeBeforeCreate = supplierDetailsRepository.findAll().size();

        // Create the SupplierDetails
        restSupplierDetailsMockMvc.perform(post("/api/supplierDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(supplierDetails)))
                .andExpect(status().isCreated());

        // Validate the SupplierDetails in the database
        List<SupplierDetails> supplierDetailss = supplierDetailsRepository.findAll();
        assertThat(supplierDetailss).hasSize(databaseSizeBeforeCreate + 1);
        SupplierDetails testSupplierDetails = supplierDetailss.get(supplierDetailss.size() - 1);
        assertThat(testSupplierDetails.getBusRegNumber()).isEqualTo(DEFAULT_BUS_REG_NUMBER);
        assertThat(testSupplierDetails.getBusDescription()).isEqualTo(DEFAULT_BUS_DESCRIPTION);
        assertThat(testSupplierDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSupplierDetails.getFaxNumber()).isEqualTo(DEFAULT_FAX_NUMBER);
        assertThat(testSupplierDetails.getGstRegistered()).isEqualTo(DEFAULT_GST_REGISTERED);
        assertThat(testSupplierDetails.getGstRegistrationNumber()).isEqualTo(DEFAULT_GST_REGISTRATION_NUMBER);
        assertThat(testSupplierDetails.getPublicPricingVisible()).isEqualTo(DEFAULT_PUBLIC_PRICING_VISIBLE);
    }

    @Test
    @Transactional
    public void getAllSupplierDetailss() throws Exception {
        // Initialize the database
        supplierDetailsRepository.saveAndFlush(supplierDetails);

        // Get all the supplierDetailss
        restSupplierDetailsMockMvc.perform(get("/api/supplierDetailss"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(supplierDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
                .andExpect(jsonPath("$.[*].busRegNumber").value(hasItem(DEFAULT_BUS_REG_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].busDescription").value(hasItem(DEFAULT_BUS_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].faxNumber").value(hasItem(DEFAULT_FAX_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].gstRegistered").value(hasItem(DEFAULT_GST_REGISTERED.booleanValue())))
                .andExpect(jsonPath("$.[*].gstRegistrationNumber").value(hasItem(DEFAULT_GST_REGISTRATION_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].logoId").value(hasItem(DEFAULT_LOGO_ID.intValue())))
                .andExpect(jsonPath("$.[*].publicPricingVisible").value(hasItem(DEFAULT_PUBLIC_PRICING_VISIBLE.booleanValue())))
                .andExpect(jsonPath("$.[*].mainPictureId").value(hasItem(DEFAULT_MAIN_PICTURE_ID.intValue())));
    }

    @Test
    @Transactional
    public void getSupplierDetails() throws Exception {
        // Initialize the database
        supplierDetailsRepository.saveAndFlush(supplierDetails);

        // Get the supplierDetails
        restSupplierDetailsMockMvc.perform(get("/api/supplierDetailss/{id}", supplierDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(supplierDetails.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.busRegNumber").value(DEFAULT_BUS_REG_NUMBER.toString()))
            .andExpect(jsonPath("$.busDescription").value(DEFAULT_BUS_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.faxNumber").value(DEFAULT_FAX_NUMBER.toString()))
            .andExpect(jsonPath("$.gstRegistered").value(DEFAULT_GST_REGISTERED.booleanValue()))
            .andExpect(jsonPath("$.gstRegistrationNumber").value(DEFAULT_GST_REGISTRATION_NUMBER.toString()))
            .andExpect(jsonPath("$.logoId").value(DEFAULT_LOGO_ID.intValue()))
            .andExpect(jsonPath("$.publicPricingVisible").value(DEFAULT_PUBLIC_PRICING_VISIBLE.booleanValue()))
            .andExpect(jsonPath("$.mainPictureId").value(DEFAULT_MAIN_PICTURE_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSupplierDetails() throws Exception {
        // Get the supplierDetails
        restSupplierDetailsMockMvc.perform(get("/api/supplierDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupplierDetails() throws Exception {
        // Initialize the database
        supplierDetailsRepository.saveAndFlush(supplierDetails);

		int databaseSizeBeforeUpdate = supplierDetailsRepository.findAll().size();

        // Update the supplierDetails
        supplierDetails.setBusRegNumber(UPDATED_BUS_REG_NUMBER);
        supplierDetails.setBusDescription(UPDATED_BUS_DESCRIPTION);
        supplierDetails.setAddress(UPDATED_ADDRESS);
        supplierDetails.setFaxNumber(UPDATED_FAX_NUMBER);
        supplierDetails.setGstRegistered(UPDATED_GST_REGISTERED);
        supplierDetails.setGstRegistrationNumber(UPDATED_GST_REGISTRATION_NUMBER);
        supplierDetails.setPublicPricingVisible(UPDATED_PUBLIC_PRICING_VISIBLE);
        restSupplierDetailsMockMvc.perform(put("/api/supplierDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(supplierDetails)))
                .andExpect(status().isOk());

        // Validate the SupplierDetails in the database
        List<SupplierDetails> supplierDetailss = supplierDetailsRepository.findAll();
        assertThat(supplierDetailss).hasSize(databaseSizeBeforeUpdate);
        SupplierDetails testSupplierDetails = supplierDetailss.get(supplierDetailss.size() - 1);
        assertThat(testSupplierDetails.getBusRegNumber()).isEqualTo(UPDATED_BUS_REG_NUMBER);
        assertThat(testSupplierDetails.getBusDescription()).isEqualTo(UPDATED_BUS_DESCRIPTION);
        assertThat(testSupplierDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSupplierDetails.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
        assertThat(testSupplierDetails.getGstRegistered()).isEqualTo(UPDATED_GST_REGISTERED);
        assertThat(testSupplierDetails.getGstRegistrationNumber()).isEqualTo(UPDATED_GST_REGISTRATION_NUMBER);
        assertThat(testSupplierDetails.getPublicPricingVisible()).isEqualTo(UPDATED_PUBLIC_PRICING_VISIBLE);
    }

    @Test
    @Transactional
    public void deleteSupplierDetails() throws Exception {
        // Initialize the database
        supplierDetailsRepository.saveAndFlush(supplierDetails);

		int databaseSizeBeforeDelete = supplierDetailsRepository.findAll().size();

        // Get the supplierDetails
        restSupplierDetailsMockMvc.perform(delete("/api/supplierDetailss/{id}", supplierDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SupplierDetails> supplierDetailss = supplierDetailsRepository.findAll();
        assertThat(supplierDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
