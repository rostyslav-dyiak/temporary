package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.ProductAlias;
import com.kb.repository.ProductAliasRepository;
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
 * Test class for the ProductAliasResource REST controller.
 *
 * @see ProductAliasResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProductAliasResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";

    @Inject
    private ProductAliasRepository productAliasRepository;

    private MockMvc restProductAliasMockMvc;

    private ProductAlias productAlias;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductAliasResource productAliasResource = new ProductAliasResource();
        ReflectionTestUtils.setField(productAliasResource, "productAliasRepository", productAliasRepository);
        this.restProductAliasMockMvc = MockMvcBuilders.standaloneSetup(productAliasResource).build();
    }

    @Before
    public void initTest() {
        productAlias = new ProductAlias();
        productAlias.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createProductAlias() throws Exception {
        int databaseSizeBeforeCreate = productAliasRepository.findAll().size();

        // Create the ProductAlias
        restProductAliasMockMvc.perform(post("/api/productAliass")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(productAlias)))
                .andExpect(status().isCreated());

        // Validate the ProductAlias in the database
        List<ProductAlias> productAliass = productAliasRepository.findAll();
        assertThat(productAliass).hasSize(databaseSizeBeforeCreate + 1);
        ProductAlias testProductAlias = productAliass.get(productAliass.size() - 1);
        assertThat(testProductAlias.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void getAllProductAliass() throws Exception {
        // Initialize the database
        productAliasRepository.saveAndFlush(productAlias);

        // Get all the productAliass
        restProductAliasMockMvc.perform(get("/api/productAliass"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(productAlias.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getProductAlias() throws Exception {
        // Initialize the database
        productAliasRepository.saveAndFlush(productAlias);

        // Get the productAlias
        restProductAliasMockMvc.perform(get("/api/productAliass/{id}", productAlias.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(productAlias.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProductAlias() throws Exception {
        // Get the productAlias
        restProductAliasMockMvc.perform(get("/api/productAliass/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductAlias() throws Exception {
        // Initialize the database
        productAliasRepository.saveAndFlush(productAlias);

		int databaseSizeBeforeUpdate = productAliasRepository.findAll().size();

        // Update the productAlias
        productAlias.setName(UPDATED_NAME);
        restProductAliasMockMvc.perform(put("/api/productAliass")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(productAlias)))
                .andExpect(status().isOk());

        // Validate the ProductAlias in the database
        List<ProductAlias> productAliass = productAliasRepository.findAll();
        assertThat(productAliass).hasSize(databaseSizeBeforeUpdate);
        ProductAlias testProductAlias = productAliass.get(productAliass.size() - 1);
        assertThat(testProductAlias.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteProductAlias() throws Exception {
        // Initialize the database
        productAliasRepository.saveAndFlush(productAlias);

		int databaseSizeBeforeDelete = productAliasRepository.findAll().size();

        // Get the productAlias
        restProductAliasMockMvc.perform(delete("/api/productAliass/{id}", productAlias.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductAlias> productAliass = productAliasRepository.findAll();
        assertThat(productAliass).hasSize(databaseSizeBeforeDelete - 1);
    }
}
