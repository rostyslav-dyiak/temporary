package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.Currency;
import com.kb.repository.CurrencyRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CurrencyResource REST controller.
 *
 * @see CurrencyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CurrencyResourceTest {

    private static final String DEFAULT_SYMBOL = "SAMPLE_TEXT";
    private static final String UPDATED_SYMBOL = "UPDATED_TEXT";
    private static final String DEFAULT_CURRENCY_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_CURRENCY_NAME = "UPDATED_TEXT";

    private static final Boolean DEFAULT_AVAILABLE = false;
    private static final Boolean UPDATED_AVAILABLE = true;

    private static final BigDecimal DEFAULT_RATE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_RATE = BigDecimal.ONE;

    @Inject
    private CurrencyRepository currencyRepository;

    private MockMvc restCurrencyMockMvc;

    private Currency currency;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CurrencyResource currencyResource = new CurrencyResource();
        ReflectionTestUtils.setField(currencyResource, "currencyRepository", currencyRepository);
        this.restCurrencyMockMvc = MockMvcBuilders.standaloneSetup(currencyResource).build();
    }

    @Before
    public void initTest() {
        currency = new Currency();
        currency.setSymbol(DEFAULT_SYMBOL);
        currency.setCurrencyName(DEFAULT_CURRENCY_NAME);
        currency.setAvailable(DEFAULT_AVAILABLE);
        currency.setRate(DEFAULT_RATE);
    }

    @Test
    @Transactional
    public void createCurrency() throws Exception {
        int databaseSizeBeforeCreate = currencyRepository.findAll().size();

        // Create the Currency
        restCurrencyMockMvc.perform(post("/api/currencys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(currency)))
                .andExpect(status().isCreated());

        // Validate the Currency in the database
        List<Currency> currencys = currencyRepository.findAll();
        assertThat(currencys).hasSize(databaseSizeBeforeCreate + 1);
        Currency testCurrency = currencys.get(currencys.size() - 1);
        assertThat(testCurrency.getSymbol()).isEqualTo(DEFAULT_SYMBOL);
        assertThat(testCurrency.getCurrencyName()).isEqualTo(DEFAULT_CURRENCY_NAME);
        assertThat(testCurrency.getAvailable()).isEqualTo(DEFAULT_AVAILABLE);
        assertThat(testCurrency.getRate()).isEqualTo(DEFAULT_RATE);
    }

    @Test
    @Transactional
    public void getAllCurrencys() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

        // Get all the currencys
        restCurrencyMockMvc.perform(get("/api/currencys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(currency.getId().intValue())))
                .andExpect(jsonPath("$.[*].symbol").value(hasItem(DEFAULT_SYMBOL.toString())))
                .andExpect(jsonPath("$.[*].currency_name").value(hasItem(DEFAULT_CURRENCY_NAME.toString())))
                .andExpect(jsonPath("$.[*].available").value(hasItem(DEFAULT_AVAILABLE.booleanValue())))
                .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE.intValue())));
    }

    @Test
    @Transactional
    public void getCurrency() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

        // Get the currency
        restCurrencyMockMvc.perform(get("/api/currencys/{id}", currency.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(currency.getId().intValue()))
            .andExpect(jsonPath("$.symbol").value(DEFAULT_SYMBOL.toString()))
            .andExpect(jsonPath("$.currency_name").value(DEFAULT_CURRENCY_NAME.toString()))
            .andExpect(jsonPath("$.available").value(DEFAULT_AVAILABLE.booleanValue()))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCurrency() throws Exception {
        // Get the currency
        restCurrencyMockMvc.perform(get("/api/currencys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurrency() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

		int databaseSizeBeforeUpdate = currencyRepository.findAll().size();

        // Update the currency
        currency.setSymbol(UPDATED_SYMBOL);
        currency.setCurrencyName(UPDATED_CURRENCY_NAME);
        currency.setAvailable(UPDATED_AVAILABLE);
        currency.setRate(UPDATED_RATE);
        restCurrencyMockMvc.perform(put("/api/currencys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(currency)))
                .andExpect(status().isOk());

        // Validate the Currency in the database
        List<Currency> currencys = currencyRepository.findAll();
        assertThat(currencys).hasSize(databaseSizeBeforeUpdate);
        Currency testCurrency = currencys.get(currencys.size() - 1);
        assertThat(testCurrency.getSymbol()).isEqualTo(UPDATED_SYMBOL);
        assertThat(testCurrency.getCurrencyName()).isEqualTo(UPDATED_CURRENCY_NAME);
        assertThat(testCurrency.getAvailable()).isEqualTo(UPDATED_AVAILABLE);
        assertThat(testCurrency.getRate()).isEqualTo(UPDATED_RATE);
    }

    @Test
    @Transactional
    public void deleteCurrency() throws Exception {
        // Initialize the database
        currencyRepository.saveAndFlush(currency);

		int databaseSizeBeforeDelete = currencyRepository.findAll().size();

        // Get the currency
        restCurrencyMockMvc.perform(delete("/api/currencys/{id}", currency.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Currency> currencys = currencyRepository.findAll();
        assertThat(currencys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
