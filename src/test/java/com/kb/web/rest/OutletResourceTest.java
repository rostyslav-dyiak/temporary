package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.Outlet;
import com.kb.repository.OutletRepository;

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
 * Test class for the OutletResource REST controller.
 *
 * @see OutletResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OutletResourceTest {

    private static final String DEFAULT_TITLE = "SAMPLE_TEXT";
    private static final String UPDATED_TITLE = "UPDATED_TEXT";
    private static final String DEFAULT_DELIVERY_ADDRESS = "SAMPLE_TEXT";
    private static final String UPDATED_DELIVERY_ADDRESS = "UPDATED_TEXT";
    private static final String DEFAULT_CONTACT_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_CONTACT_NUMBER = "UPDATED_TEXT";
    private static final String DEFAULT_POSTAL_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_POSTAL_CODE = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";

    @Inject
    private OutletRepository outletRepository;

    private MockMvc restOutletMockMvc;

    private Outlet outlet;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OutletResource outletResource = new OutletResource();
        ReflectionTestUtils.setField(outletResource, "outletRepository", outletRepository);
        this.restOutletMockMvc = MockMvcBuilders.standaloneSetup(outletResource).build();
    }

    @Before
    public void initTest() {
        outlet = new Outlet();
        outlet.setTitle(DEFAULT_TITLE);
        outlet.setDeliveryAddress(DEFAULT_DELIVERY_ADDRESS);
        outlet.setContactNumber(DEFAULT_CONTACT_NUMBER);
        outlet.setPostalCode(DEFAULT_POSTAL_CODE);
        outlet.setEmail(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createOutlet() throws Exception {
        int databaseSizeBeforeCreate = outletRepository.findAll().size();

        // Create the Outlet
        restOutletMockMvc.perform(post("/api/outlets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(outlet)))
                .andExpect(status().isCreated());

        // Validate the Outlet in the database
        List<Outlet> outlets = outletRepository.findAll();
        assertThat(outlets).hasSize(databaseSizeBeforeCreate + 1);
        Outlet testOutlet = outlets.get(outlets.size() - 1);
        assertThat(testOutlet.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testOutlet.getDeliveryAddress()).isEqualTo(DEFAULT_DELIVERY_ADDRESS);
        assertThat(testOutlet.getContactNumber()).isEqualTo(DEFAULT_CONTACT_NUMBER);
        assertThat(testOutlet.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testOutlet.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOutlets() throws Exception {
        // Initialize the database
        outletRepository.saveAndFlush(outlet);

        // Get all the outlets
        restOutletMockMvc.perform(get("/api/outlets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(outlet.getId().intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
                .andExpect(jsonPath("$.[*].delivery_address").value(hasItem(DEFAULT_DELIVERY_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].contact_number").value(hasItem(DEFAULT_CONTACT_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].postal_code").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }

    @Test
    @Transactional
    public void getOutlet() throws Exception {
        // Initialize the database
        outletRepository.saveAndFlush(outlet);

        // Get the outlet
        restOutletMockMvc.perform(get("/api/outlets/{id}", outlet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(outlet.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.delivery_address").value(DEFAULT_DELIVERY_ADDRESS.toString()))
            .andExpect(jsonPath("$.contact_number").value(DEFAULT_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.postal_code").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOutlet() throws Exception {
        // Get the outlet
        restOutletMockMvc.perform(get("/api/outlets/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOutlet() throws Exception {
        // Initialize the database
        outletRepository.saveAndFlush(outlet);

		int databaseSizeBeforeUpdate = outletRepository.findAll().size();

        // Update the outlet
        outlet.setTitle(UPDATED_TITLE);
        outlet.setDeliveryAddress(UPDATED_DELIVERY_ADDRESS);
        outlet.setContactNumber(UPDATED_CONTACT_NUMBER);
        outlet.setPostalCode(UPDATED_POSTAL_CODE);
        outlet.setEmail(UPDATED_EMAIL);
        restOutletMockMvc.perform(put("/api/outlets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(outlet)))
                .andExpect(status().isOk());

        // Validate the Outlet in the database
        List<Outlet> outlets = outletRepository.findAll();
        assertThat(outlets).hasSize(databaseSizeBeforeUpdate);
        Outlet testOutlet = outlets.get(outlets.size() - 1);
        assertThat(testOutlet.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testOutlet.getDeliveryAddress()).isEqualTo(UPDATED_DELIVERY_ADDRESS);
        assertThat(testOutlet.getContactNumber()).isEqualTo(UPDATED_CONTACT_NUMBER);
        assertThat(testOutlet.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testOutlet.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void deleteOutlet() throws Exception {
        // Initialize the database
        outletRepository.saveAndFlush(outlet);

		int databaseSizeBeforeDelete = outletRepository.findAll().size();

        // Get the outlet
        restOutletMockMvc.perform(delete("/api/outlets/{id}", outlet.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Outlet> outlets = outletRepository.findAll();
        assertThat(outlets).hasSize(databaseSizeBeforeDelete - 1);
    }
}
