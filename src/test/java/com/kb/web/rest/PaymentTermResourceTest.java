package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.PaymentTerm;
import com.kb.repository.PaymentTermRepository;
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
 * Test class for the PaymentTermResource REST controller.
 *
 * @see PaymentTermResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PaymentTermResourceTest {

    private static final String DEFAULT_TERM_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_TERM_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    private static final Integer DEFAULT_NUM_OF_DAYS = 0;
    private static final Integer UPDATED_NUM_OF_DAYS = 1;

    @Inject
    private PaymentTermRepository paymentTermRepository;

    private MockMvc restPaymentTermMockMvc;

    private PaymentTerm paymentTerm;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PaymentTermResource paymentTermResource = new PaymentTermResource();
        ReflectionTestUtils.setField(paymentTermResource, "paymentTermRepository", paymentTermRepository);
        this.restPaymentTermMockMvc = MockMvcBuilders.standaloneSetup(paymentTermResource).build();
    }

    @Before
    public void initTest() {
        paymentTerm = new PaymentTerm();
        paymentTerm.setTermName(DEFAULT_TERM_NAME);
        paymentTerm.setDescription(DEFAULT_DESCRIPTION);
        paymentTerm.setNumOfDays(DEFAULT_NUM_OF_DAYS);
    }

    @Test
    @Transactional
    public void createPaymentTerm() throws Exception {
        int databaseSizeBeforeCreate = paymentTermRepository.findAll().size();

        // Create the PaymentTerm
        restPaymentTermMockMvc.perform(post("/api/paymentTerms")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paymentTerm)))
                .andExpect(status().isCreated());

        // Validate the PaymentTerm in the database
        List<PaymentTerm> paymentTerms = paymentTermRepository.findAll();
        assertThat(paymentTerms).hasSize(databaseSizeBeforeCreate + 1);
        PaymentTerm testPaymentTerm = paymentTerms.get(paymentTerms.size() - 1);
        assertThat(testPaymentTerm.getTermName()).isEqualTo(DEFAULT_TERM_NAME);
        assertThat(testPaymentTerm.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPaymentTerm.getNumOfDays()).isEqualTo(DEFAULT_NUM_OF_DAYS);
    }

    @Test
    @Transactional
    public void getAllPaymentTerms() throws Exception {
        // Initialize the database
        paymentTermRepository.saveAndFlush(paymentTerm);

        // Get all the paymentTerms
        restPaymentTermMockMvc.perform(get("/api/paymentTerms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(paymentTerm.getId().intValue())))
                .andExpect(jsonPath("$.[*].termName").value(hasItem(DEFAULT_TERM_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].numOfDays").value(hasItem(DEFAULT_NUM_OF_DAYS)));
    }

    @Test
    @Transactional
    public void getPaymentTerm() throws Exception {
        // Initialize the database
        paymentTermRepository.saveAndFlush(paymentTerm);

        // Get the paymentTerm
        restPaymentTermMockMvc.perform(get("/api/paymentTerms/{id}", paymentTerm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(paymentTerm.getId().intValue()))
            .andExpect(jsonPath("$.termName").value(DEFAULT_TERM_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.numOfDays").value(DEFAULT_NUM_OF_DAYS));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentTerm() throws Exception {
        // Get the paymentTerm
        restPaymentTermMockMvc.perform(get("/api/paymentTerms/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentTerm() throws Exception {
        // Initialize the database
        paymentTermRepository.saveAndFlush(paymentTerm);

        int databaseSizeBeforeUpdate = paymentTermRepository.findAll().size();

        // Update the paymentTerm
        paymentTerm.setTermName(UPDATED_TERM_NAME);
        paymentTerm.setDescription(UPDATED_DESCRIPTION);
        paymentTerm.setNumOfDays(UPDATED_NUM_OF_DAYS);
        restPaymentTermMockMvc.perform(put("/api/paymentTerms")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paymentTerm)))
                .andExpect(status().isOk());

        // Validate the PaymentTerm in the database
        List<PaymentTerm> paymentTerms = paymentTermRepository.findAll();
        assertThat(paymentTerms).hasSize(databaseSizeBeforeUpdate);
        PaymentTerm testPaymentTerm = paymentTerms.get(paymentTerms.size() - 1);
        assertThat(testPaymentTerm.getTermName()).isEqualTo(UPDATED_TERM_NAME);
        assertThat(testPaymentTerm.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPaymentTerm.getNumOfDays()).isEqualTo(UPDATED_NUM_OF_DAYS);
    }

    @Test
    @Transactional
    public void deletePaymentTerm() throws Exception {
        // Initialize the database
        paymentTermRepository.saveAndFlush(paymentTerm);

        int databaseSizeBeforeDelete = paymentTermRepository.findAll().size();

        // Get the paymentTerm
        restPaymentTermMockMvc.perform(delete("/api/paymentTerms/{id}", paymentTerm.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PaymentTerm> paymentTerms = paymentTermRepository.findAll();
        assertThat(paymentTerms).hasSize(databaseSizeBeforeDelete - 1);
    }
}
